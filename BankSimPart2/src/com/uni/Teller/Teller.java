/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * This is the class to control the teller.
 * The teller can fetch the queue item from the head of the queue and
 * process the transactions.
 * Runs until simulation stopped.
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
import com.uni.summary.Summary;
import com.uni.summary.SummaryList;

public class Teller extends Thread implements Subject{
	
	private boolean open = true; //flag if teller is open or closed
	private static int master = 2000;//teller ID
	private int id;
	
	//list of accounts so the teller has access
	private AccountList al;
	private String message;
	
	private CustomerQueue q;
	private CustomerQueue ocq;
	private Customer currentCustomer;
	private int custNumber;
	private String currentStatus;

	private String transactionType;
	private boolean stopThread = false;

	public boolean pleaseWait = false;

	public boolean serving = false ;
	
	SummaryList sl = SummaryList.getInstance();
	
	/**
	 * Constructor for the teller.
	 * @param al the list of accounts
	 */
	public Teller(AccountList al, CustomerQueue q, CustomerQueue ocq){
		this.al = al;
		this.q = q;
		this.ocq = ocq;
		master++;
		this.id = master;
	}
	
	public int getIdent()
	{
		return this.id;
	}
	
	public void done()
	{
		stopThread = true;
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
		serving = true;
		System.out.println("Processing");
		//get the transactions from the queue item
		TransactionList tList = q.getTransactions();
		//get the customer from the queue item
		Customer cust = q.getCustomer();
		currentCustomer = cust;
		System.out.println(cust.getFullName());
		custNumber = q.getCustNo();
		Statistics.last_customer = custNumber;
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

			try{
				switch(t.getChoice()){
				case WITHDRAW:
					transactionType = "Withdraw";
					notifyObservers();
					//the account number will be the secondary aux value
					acNo = (Integer)t.getSecondaryAux();
					
					updateCurrentStatusAndWait( "Account No: " + acNo);
					
					//some message stuff
					message += Language.WITHDRAW_START;
					message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
					
					//the withdraw value will be the primary aux
					value = (Integer)t.getPrimaryAux();
					
					updateCurrentStatusAndWait("Amount: " + 
							Statistics.toPoundsAndPence(value)); 

					
					
					//int acNumber = cust.getAccountNo(acNo);
					message += doWithdraw(acNo, value, false);
					message += Language.WITHDRAW_END;
					sl.add(new Summary(cust,this,t.getChoice(),t.getPrimaryAux()));
					
					break;
				case DEPOSIT:
					
					transactionType = "Deposit";
					notifyObservers();
					
					message += Language.DEPOSIT_START;
					
					//account number is secondary aux
					acNo = (Integer)t.getSecondaryAux();
					updateCurrentStatusAndWait( "Account No: " + acNo);
					
					message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
					try{
						Statistics.ACCOUNT_DEPOSIT++; //update stats
						ac = this.al.getAccountAtIndex(acNo); //get the account
						value = (Integer)t.getPrimaryAux(); //get the value
						updateCurrentStatusAndWait( "Amount: " + Statistics.toPoundsAndPence(value));
						
						
						ac.deposit(value);
							//deposit the value
						Statistics.TOTALS_DEPOSTIT += value; //update stats
						message += Language.DepositInfo(value, ac.getBalance()); 
						sl.add(new Summary(cust,this,t.getChoice(),t.getPrimaryAux()));
						
						
						
					}catch(NonExistantAccountException e){
						message += Language.ERROR_NONEXISTANT_ACCOUNT;
					}
					message += Language.DEPOSIT_END;
					break;
				case OPEN:
					transactionType = "Open";
					updateCurrentStatusAndWait("Collecting details");
					
					Statistics.ACCOUNTS_OPENED++; //udate stats
					
					message += Language.OPEN_START;
					//try associate account with customer
					if(cust.getNumOfAccounts() >= 2)
					{
						message += Language.ERROR_TOO_MANY_ACCOUNTS;
						message += Language.OPEN_END;
						updateCurrentStatusAndWait("Max account limit!");
						break;
					}
					Account acc = new Account(); //create the account
					al.add(acc); //add
					cust.addAccount(acc.getAccountNumber());
					message += Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acc.getAccountNumber()+"");
					message += Language.OPEN_END;
					sl.add(new Summary(cust,this,t.getChoice(),t.getPrimaryAux()));
					
					break;
				case CLOSE:
					transactionType = "Close";
					notifyObservers();
					updateCurrentStatusAndWait("fetching");
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
						updateCurrentStatusAndWait("No accounts");
						break;
					}
					//get the associated account number
					acNo = cust.getAccountNo(acId);
					updateCurrentStatusAndWait("Account:" + acNo);
					message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");
					//remove from account list and customers accounts
					message += "\t" + Language.WITHDRAW_START;
					updateCurrentStatusAndWait("Removing funds");
					try{
						int bal = al.getAccountAtIndex(acNo).getBalance();
						message += doWithdraw(acNo, bal, true);	
						sl.add(new Summary(cust,this,Transaction.Choices.WITHDRAW,bal));
						Statistics.TRANSACTION_TOTAL += 1; //counts as seperate
						message +=Language.CustomerInfo(cust.getFullName(), q.getCustNo() +"", acNo+"");	
						if(cust.removeAccount(acId)){
							al.removeAccountNo(acNo);
							message += "Successfully removed";
							updateCurrentStatusAndWait("Success");
						}else{
							message += "Error removing from customer";
							updateCurrentStatusAndWait("Error");
						}
					}catch(NonExistantAccountException e){
						message += Language.ERROR_NONEXISTANT_ACCOUNT;
						updateCurrentStatusAndWait("Invalid");
					}
					message += "\t" + Language.WITHDRAW_END;
					//message
					message += Language.CLOSE_END;
					sl.add(new Summary(cust,this,t.getChoice(),t.getPrimaryAux()));
					break;
				}
				//write the message
				Log l = Log.getInstance();
				l.writeMessage(message);
			}catch(Exception e){
				
			}

			
		}
		resetLabels();
		serving = false ;

		//another customer served
		Statistics.CUSTOMERS_SERVED++;
		
	}
	
	public void pauseTransaction(){
		try {
			//System.out.println("Sleeping for:" + Statistics.TELLER_SPEED + " * " +  Statistics.SIMULATION_SPEED_FACTOR);
			Thread.sleep(Statistics.TELLER_SPEED * Statistics.SIMULATION_SPEED_FACTOR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void resetLabels(){
		currentStatus = "Calling next customer... ";
		transactionType = " --- ";
		currentCustomer = null;
		notifyObservers();
		pauseTransaction();
	}
	
	public String getCustomerName(){
		if(currentCustomer != null)
			return currentCustomer.getFullName();
		else
			return "Waiting for next";
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
	
	private String doWithdraw(int acNo, int value, boolean clear){
		Account ac;
		String message;
		try{
			//get the account
			ac = this.al.getAccountAtIndex(acNo);
			updateCurrentStatusAndWait("Account:" + ac);
			//the amount to be withdrawn
			if(clear)
			{
				ac.clearBalance(value);
				Statistics.TOTALS_WITHDRAW += value;
				message = Language.WithdrawInfo(value, ac.getBalance());
			}
			else
			//if the withdraw is successful report
				if(ac.withDraw(value)){
					Statistics.TOTALS_WITHDRAW += value;
					message = Language.WithdrawInfo(value, ac.getBalance());
				}else{
				updateCurrentStatusAndWait(Language.ERROR_INSUFFICIENT_FUNDS);
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
		while(!stopThread){
			
//			 Check if should wait 
			synchronized (this) {
				while (pleaseWait) { 
					try { wait(); } 
					catch (Exception e) { } 
					} 
				} 
			
				if(this.open){
					try{
						if(ocq.size() > 0)
							this.processQueueItem(ocq.getFirst());
						else
							if(q.size() > 0)
								this.processQueueItem(q.getFirst());
							
						notifyObservers();
						Random r = new Random();
						
						Thread.sleep(100);
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//}
		}
		
	}
	
	
	public void updateCurrentStatusAndWait(String status){
		currentStatus = status;
		notifyObservers();
		pauseTransaction();
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
