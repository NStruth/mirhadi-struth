/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * This is the class to control the teller.
 * The teller can fetch the queue item from the head of the queue and
 * process the transactions.
 * 
 */
package com.uni.Teller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Observation.Observer;
import Observation.Subject;

import com.uni.Exceptions.NonExistantAccountException;
import com.uni.Logging.Language;
import com.uni.Logging.Log;
import com.uni.account.Account;
import com.uni.account.AccountList;
import com.uni.account.Transaction;
import com.uni.account.TransactionList;
import com.uni.customer.Customer;
import com.uni.main.Statistics;
import com.uni.queue.CustomerQueue;
import com.uni.queue.QueueItem;

public class Teller extends Thread implements Subject{
	
	private boolean open = true;
	
	//list of accounts so the teller has access
	private AccountList al;
	private String message;
	
	private CustomerQueue q;
	private Customer currentCustomer;
	private int custNumber;
	private String currentStatus;

	private String transactionType;
	
	/**
	 * Constructor for the teller.
	 * @param al the list of accounts
	 */
	public Teller(AccountList al, CustomerQueue q){
		this.al = al;
		this.q = q;
	}
	
	
	public void setOpen(boolean value){
		open = value;
	}
	
	public boolean getOpen(){
		return open;
	}
	
	/**
	 * Process a queue item.
	 * For each transaction perform the required task
	 * @param q the queue item
	 */
	public void processQueueItem(QueueItem q){
		System.out.println("Processing");
		//get the transactions from the queue item
		TransactionList tList = q.getTransactions();
		//get the customer from the queue item
		Customer cust = q.getCustomer();
		currentCustomer = cust;
		System.out.println(cust.getFullName());
		custNumber = q.getCustNo();
		transactionType = " --- ";
		notifyObservers();
		//process each transaction
		for(Transaction t: tList){
			//form a message to write to the log
			Statistics.TRANSACTION_TOTAL += 1;
			message = "";
			
			int acNo;
			Account ac;
			int value; 


			switch(t.getChoice()){
			case WITHDRAW:
				transactionType = "Withdraw";
				notifyObservers();
				//the account number will be the secondary aux value
				acNo = (Integer)t.getSecondaryAux();
				
				//some message stuff
				message += Language.WITHDRAW_START;
				message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
				
				//the withdraw value will be the primary aux
				value = (Integer)t.getPrimaryAux();
				
				//int acNumber = cust.getAccountNo(acNo);
				message += doWithdraw(acNo, value);
				message += Language.WITHDRAW_END;
				break;
			case DEPOSIT:
				transactionType = "Deposit";
				notifyObservers();
				message += Language.DEPOSIT_START;
				//account number is secondary aux
				acNo = (Integer)t.getSecondaryAux();
				message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
				try{
					Statistics.ACCOUNT_DEPOSIT++; //update stats
					ac = this.al.getAccountAtIndex(acNo); //get the account
					value = (Integer)t.getPrimaryAux(); //get the value
					
					ac.deposit(value);
						//deposit the value
					Statistics.TOTALS_DEPOSTIT += value; //update stats
					message += Language.DepositInfo(value, ac.getBalance()); 
					
					
					
				}catch(NonExistantAccountException e){
					message += Language.ERROR_NONEXISTANT_ACCOUNT;
				}
				message += Language.DEPOSIT_END;
				break;
			case OPEN:
				transactionType = "Open";
				notifyObservers();
				Statistics.ACCOUNTS_OPENED++; //udate stats
				message += Language.OPEN_START;
				//try associate account with customer
				if(cust.getNumOfAccounts() >= 2)
				{
					message += Language.ERROR_TOO_MANY_ACCOUNTS;
					message += Language.OPEN_END;
					break;
				}
				Account acc = new Account(); //create the account
				al.add(acc); //add
				cust.addAccount(acc.getAccountNumber());
				message += Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acc.getAccountNumber()+"");
				message += Language.OPEN_END;
				break;
			case CLOSE:
				transactionType = "Close";
				notifyObservers();
				//update statistics
				Statistics.ACCOUNTS_CLOSED++;
				//some boring log stuff
				message += Language.CLOSE_START;
				//get the id of the account (0 or 1; from customer)
				int acId = (Integer)t.getPrimaryAux();
				if(cust.getNumOfAccounts() == 0)
				{
					message += Language.ERROR_NO_ACCOUNTS_HELD;
					message += Language.CLOSE_END;
					break;
				}
				//get the associated account number
				acNo = cust.getAccountNo(acId);
				message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
				//remove from account list and customers accounts
				message += "\t" + Language.WITHDRAW_START;
				try{
					int bal = al.getAccountAtIndex(acNo).getBalance();
					message += doWithdraw(acNo, bal);	
					Statistics.TRANSACTION_TOTAL += 1; //counts as seperate
					message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");	
					if(cust.removeAccount(acId)){
						al.removeAccountNo(acNo);
						message += "Successfully removed";
					}else{
						message += "Error removing from customer";
					}
				}catch(NonExistantAccountException e){
					message += Language.ERROR_NONEXISTANT_ACCOUNT;
				}
				message += "\t" + Language.WITHDRAW_END;
				//message
				message += Language.CLOSE_END;
				break;
			}
			//write the message
			Log.writeMessage(message);
		}
		//another customer served
		Statistics.CUSTOMERS_SERVED++;
		
	}
	
	public String getMessage(){
		return currentStatus;
	}
	
	public int getCustNumber(){
		return custNumber;
	}
	
	public String getTranType(){
		return transactionType;
	}
	
	private String doWithdraw(int acNo, int value){
		Account ac;
		String message;
		try{
			//get the account
			ac = this.al.getAccountAtIndex(acNo);
			//the amount to be withdrawn
			//if the withdraw is successfull report
			if(ac.withDraw(value)){
				Statistics.TOTALS_WITHDRAW += value;
				message = Language.WithdrawInfo(value, ac.getBalance());
			}else{
				message = Language.ERROR_INSUFFICIENT_FUNDS;

			}
			Statistics.ACCOUNT_WITHDRAW++;
			
		}catch(NonExistantAccountException e){
			return Language.ERROR_NONEXISTANT_ACCOUNT;
		}
		return message;
	}

	@Override
	public void run() {
		while(true){
			try{
				System.out.println(q.size());
				if(q.size() > 0)
					this.processQueueItem(q.getFirst());
					
				notifyObservers();
				Random r = new Random();
				
				Thread.sleep(r.nextInt(5000));
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String getCustomerName(){
		return currentCustomer.getFullName();
	}

////////////////////////////////////////////////////////
	//OBSERVER PATTERN
	//SUBJECT must be able to register, remove and notify observers
	//list to hold any observers
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	//methods to register, remove and notify observers
	public void registerObserver( Observer obs)
	{
		registeredObservers.add( obs);
	}
	
	public void removeObserver( Observer obs)
	{
		registeredObservers.remove( obs);
	}
	
	public void notifyObservers()
	{
		for( Observer obs : registeredObservers)
			obs.update();
	}
	//////////////////////////////////////////////////////// 	
	
	
}
