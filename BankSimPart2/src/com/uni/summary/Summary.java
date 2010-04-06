/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * A summary object is essentially a transaction
 * that has been processed.  It allows the data to 
 * be extrapolated and written to a summary file at the end.
 * 
 * 
 */

package com.uni.summary;

import com.uni.Teller.Teller;
import com.uni.account.Transaction;
import com.uni.account.Transaction.Choices;
import com.uni.customer.Customer;

public class Summary {
	private Customer c; //the customer
	private Teller t; //the teller
	private Transaction.Choices type; //the transaction type
	private Object amount; //the amount
	
		
	/**
	 * Constructor for this summary object
	 * 
	 * @param c the customer
	 * @param t the teller
	 * @param type the type
	 * @param amount the amount if any
	 */
	public Summary(Customer c, Teller t, Choices type, Object amount) {
		this.c = c;
		this.t = t;
		this.type = type;
		this.amount = amount;
	}


	/**
	 * Return the customer
	 * 
	 * @return the customer from this summary stat
	 */
	public Customer getC() {
		return c;
	}

	/**
	 * Set the customer
	 * 
	 * @param c the customer
	 */
	public void setC(Customer c) {
		this.c = c;
	}

	/**
	 * Get the teller 
	 * @return the teller
	 */
	public Teller getT() {
		return t;
	}

	/**
	 * Set the teller
	 * 
	 * @param t the teller
	 */
	public void setT(Teller t) {
		this.t = t;
	}

	/**
	 * Get the transaction type
	 * @return the transaction type
	 */
	public Transaction.Choices getType() {
		return type;
	}

	/**
	 * Set the transaction type
	 * 
	 * @param type the transaction type
	 */
	public void setType(Transaction.Choices type) {
		this.type = type;
	}

	/**
	 * Get the amount
	 * @return the amount
	 */
	public Integer getAmount() {
		return (Integer)amount;
	}

	/**
	 * Set the amount
	 * @param amount the amount to set
	 */
	public void setAmount(Object amount) {
		this.amount = amount;
	}
}
