/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * This class will randomly generate a queue from a list of customers.
 * 
 * Some rules are employed to ensure sensible(ish) transactions
 */

package com.uni.main;

import java.text.DecimalFormat;
import java.util.Random;

import com.uni.Exceptions.NonExistantAccountException;
import com.uni.Logging.Log;
import com.uni.account.Account;
import com.uni.account.AccountList;
import com.uni.account.Transaction;
import com.uni.account.TransactionList;
import com.uni.customer.Customer;
import com.uni.customer.CustomerList;
import com.uni.queue.CustomerQueue;
import com.uni.queue.QueueItem;

public class Generator {
	private CustomerList clist; //the list of customers
	private AccountList aList; //the list of accounts
	
	/**
	 * Constructor for the random generator
	 * @param clist the list of customers
	 * @param alist the list of accounts
	 */
	public Generator(CustomerList clist, AccountList alist)
	{
		this.clist = clist;
		this.aList = alist;
	}
	/**
	 * Generate a single item
	 * @return the queue item
	 */
	private QueueItem generateItem()
	{
		//create a random generator
		Random rGen = new Random();
		//select a random customer
		int cNo = rGen.nextInt(clist.size());
		Customer c = clist.get(cNo);
		//only process if the customer is not in the queue
		if(c.getStatus() == 0)
		{
			//change queue status
			c.setStatus(1);
			
			//1,2,3 transactions with weighted selection
			int numTransWeighted = rGen.nextInt(10);
			//make a list to store the transactions
			TransactionList tList = new TransactionList();
			//generate between 1 and 3 transactions - inclusive
			int numTrans;
			if(numTransWeighted >= 0 &&  numTransWeighted < 5){
				numTrans = 0;
			}else if(numTransWeighted >= 5 && numTransWeighted < 8){
				numTrans = 1;
			}else{
				numTrans = 2;
			}
			
			//generate each transaction
			for(int i=0; i<= numTrans; i++){
				//if they are closing there only account they wont want a new one
				if(tList.containsClose() && c.getNumOfAccounts() == 1){
					break;
				//if they are closing all there accounts the are leaving
				}else if(tList.containsMultipleClose()){
					break;
				//if they are closing 1 account but have 2 then treat transaction as single account
				}else if(tList.containsClose() && c.getNumOfAccounts() == 2){
					tList.add(getTransaction(c, -1));
				//otherwise get the transaction
				}else{
					tList.add(getTransaction(c,0));		
				}
			}
			QueueItem q = new QueueItem(c, tList);
			return q;	
		}else{
			QueueItem q = generateItem();
			return q;
		}
	}
	
	/**
	 * Get a transaction for a queue item based on customer.
	 * Transaction choices based on number of accounts the customer
	 * has.
	 * Account offset determines is used if a customer is
	 * already closing 1 or both accounts so the next transaction
	 * will not attempt to interact with said accounts.
	 * @param c
	 * @param accountOffset
	 * @return
	 */
	private Transaction getTransaction(Customer c, int accountOffset)
	{
		Random rGen = new Random();
		int cWeight, oWeight, dWeight = 0;
		int totalWeight = 100;
		int tType = rGen.nextInt(100) + 1;
		//how many accounts will they have after previous
		//transactions are processed
		int accounts = c.getNumOfAccounts() + accountOffset;
		switch(accounts)
		{
			//if they have no accounts
			case 0:
			{
				//the can only open an account
				Transaction t = new Transaction(Transaction.Choices.OPEN);
				return t;
			}
			//if they have one account
			case 1:
			{
				//do some weighting
				cWeight = 5;
				oWeight = 5;
				dWeight = 45;
				//possibly close, deposit, or withdraw from account or open a new one
				if(tType > 0 && tType <= cWeight)
				{
					return generateClose(0);
				}
				else if(tType > cWeight && tType <= oWeight + cWeight){
					return new Transaction(Transaction.Choices.OPEN);
				}else if(tType > oWeight + cWeight && tType <= dWeight + oWeight + cWeight){
					return generateDeposit(c,rGen, c.getAccountNo(0));
				}else if(tType > dWeight + oWeight + cWeight && tType <= totalWeight){
					return generateWithdraw(c, c.getAccountNo(0));
				}
				break;
			}
			//if they have 2 accounts only close, withdraw, deposit
			case 2:
			{
				cWeight = 10;
				dWeight = 45;
				if(tType > 0 && tType <= cWeight){
					int accId = rGen.nextInt(2);
					return generateClose(accId);
				}else if(tType > cWeight && tType <= dWeight + cWeight){
					int accId = rGen.nextInt(2);
					return generateDeposit(c,rGen, c.getAccountNo(accId));
				}else if(tType > dWeight + cWeight && tType <= totalWeight){
					int accId = rGen.nextInt(2);
					return generateWithdraw(c, c.getAccountNo(accId));
				}else{
					return null;
				}	
			}
			default:
			{
				cWeight = -1;
				oWeight = -1;
				dWeight = -1;
				return null;
			}
		}
		return null;
		
	}
	

	/**
	 * Get the amount that the customer wants to withdraw.
	 * Note that there is a £200 withdraw limit.
	 * @param c the customer 
	 * @param acc the account number
	 * @return the amount to be withdrawn
	 */
	private int getWithdrawAmount(Customer c, int acc)
	{
		try{
			Account a = aList.getAccountAtIndex(acc);
			a.toString();
			Random rGen = new Random();
		
			//if the customer has less than 200 withdraw up to their balance
			if(a.getBalance() < 20000){
				int amount = rGen.nextInt(a.getBalance());
				return amount;
			}
			//otherwise withdraw anything up to limit
			else{
				int amount = rGen.nextInt(20001);
				return amount;
			}
		}catch(NonExistantAccountException e){
			return 0;
		}
		
	}
	/**
	 * generate an entire customer queue
	 * @return the queue of customers with transactions
	 */
	public CustomerQueue generate()
	{
		CustomerQueue q = new CustomerQueue();
		Random randGen = new Random();
		int length = randGen.nextInt(this.clist.size()) + 1;
		
		//Make sure we have enough customers in the queue
		//Minimum length 5
		if(length < 5)
		{
			length = length + 5;
		}
		for(int i = 0; i < length; i++)
		{
			q.add(generateItem());
		}
		return q;
	}
	
	/**
	 * Generate a deposit transaction
	 * @param c the customer
	 * @param rGen the random generator
	 * @param accNo the account id (in the customers account list)
	 * @return the deposit transaction
	 */
	private Transaction generateDeposit(Customer c, Random rGen, int accId){
		return  new Transaction(Transaction.Choices.DEPOSIT, rGen.nextInt(100000), accId);
	}
	/**
	 * Generate a close transaction
	 * @param accId the account id (in the customers account list)
	 * @return the close transaction
	 */
	private Transaction generateClose(int accId){
		return new Transaction(Transaction.Choices.CLOSE, accId);
	}

	
	private Transaction generateWithdraw(Customer c, int accId ){
		return new Transaction(Transaction.Choices.WITHDRAW, getWithdrawAmount(c, accId), accId);
	}

}