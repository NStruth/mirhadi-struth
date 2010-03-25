/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A queue item consists of a customer and a transaction, as well as a
 * queue number.
 */
package com.uni.queue;

import com.uni.account.Transaction;
import com.uni.account.TransactionList;
import com.uni.customer.Customer;

public class QueueItem {
	//static holder for queue number
	public static int masterNumber = 1000;
	private Customer c; //the customer
	private Transaction d; //the transaction
	private int custId; //the customer id
	
	private TransactionList tList; //the list of transactions
	
	/**
	 * Constructor for this queue item
	 * @param c the customer
	 * @param tList the list of transactions
	 */
	public QueueItem(Customer c, TransactionList tList){
		this.c = c;
		this.tList = tList;
		masterNumber++;
		this.custId = masterNumber;
	}
	/**
	 * Get the transactions from this queue item
	 * @return the list of transactions
	 */
	public TransactionList getTransactions(){
		return this.tList;
	}
	/**
	 * Get the customer from this queue item
	 * @return the customer
	 */
	public Customer getCustomer(){
		 return this.c;
	}
	/**
	 * Get the customer number from this queue item.
	 * @return
	 */
	public int getCustNo(){
		return this.custId;
	}
	
	public String toString(){
		String v = "";
		for(Transaction t: this.tList){
			v+= t.toString() + "\n";
		}
		return this.c.getFullName() + " QueueNo:" + this.custId + "\n" + v;
	}
}
