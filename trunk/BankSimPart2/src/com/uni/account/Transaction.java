/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A class to store a transaction.
 * Transaction possibilities are selected from an enumerator.
 * Multiple constructors are available for different transaction
 * types.  Two aux variables are present to store 
 * varying transaction information.
 */
package com.uni.account;

import com.uni.customer.Customer;
import com.uni.main.Statistics;

public class Transaction {
	/*
	 * The transaction choices.
	 */
	public enum Choices {
		OPEN,		//new account
		CLOSE,		//close account
		DEPOSIT,	//add funds to account
		WITHDRAW	//withdraw funds from account
	}
	private Choices choice; //the choice
	private Object primaryAux; //the first aux value
	private Object secondaryAux; //the second aux value
	/**
	 * Constructor for withdrawing money
	 * @param c the choice
	 * @param aux the value to be withdrawn
	 * @param ac the account number
	 */
	public Transaction(Choices c, int aux, int ac){
		this.choice = c;
		this.primaryAux = aux;
		this.secondaryAux = ac;
	}
	/**
 	 * Constructor for closing an account
 	 * 
	 * @param c the choice
	 * @param ac the account to close
	 */
	public Transaction(Choices c, int ac)
	{
		this.choice = c;
		this.primaryAux = ac;
	}
	/**
	 * Constructor to be used when opening an account
	 * @param c The choice
	 */
	public Transaction(Choices c){
		this.choice = c;
	}
	/**
	 *N.B this is a concept and not implemented
	 *
	 * Constructor to be used when opening a joint account
	 * @param c the choice
	 * @param first the first customer
	 * @param second the second customer
	 */
	public Transaction(Choices c, Customer first, Customer second)
	{
		this.choice = c;
		this.primaryAux = first;
		this.primaryAux = second;
	}
	/**
	 * Get the transaction type
	 * @return the transaction type
	 */
	public Transaction.Choices getChoice(){
		return choice;
	}
	/**
	 * Get the primary aux value
	 * @return the primary aux value
	 */
	public Object getPrimaryAux() {
		return primaryAux;
	}
	/**
	 * Get the secondary aux value
	 * @return the secondary aux value
	 */
	public Object getSecondaryAux() {
		return secondaryAux;
	}
	
	public String toString(){
		String retV = "   Type: " + choice.toString();
		if(primaryAux != null){
			if(choice == Transaction.Choices.WITHDRAW || choice == Transaction.Choices.DEPOSIT)
				retV += "::" + Statistics.toPoundsAndPence((Integer)primaryAux);
			else
				retV += "::" + primaryAux.toString();
		}
		
		if(secondaryAux != null){
			retV += "::" + secondaryAux.toString();
		}
		return retV;
	}
}
