/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * Extends ArrayList to store a list of transactions 
 * (as a customer may have more than one request).
 */
package com.uni.account;

import java.util.ArrayList;

public class TransactionList extends ArrayList<Transaction> {
	
	public TransactionList(){
		super();
	}

	@Override
	public boolean add(Transaction t) {
		return super.add(t);
	}
	
	/**
	 * Returns true if there is a close transaction
	 * already in the user list
	 * (Useful for the generator to make sensible 
	 * transactions)
	 * @return
	 */
	public boolean containsClose(){
		for(Transaction t: this){
			if(t.getChoice() == Transaction.Choices.CLOSE)
				return true;
		}
		return false;
	}
	
	public boolean containsOpen(){
		for(Transaction t: this){
			if(t.getChoice() == Transaction.Choices.OPEN)
				return true;
		}
		return false;
	}

	/**
	 * Check to see if there are multiple account closes
	 * in the transaction list (i.e. both account will
	 * be close). Would be more dynamic to return a 
	 * number.  Again useful for the generator.
	 * @return
	 */
	public boolean containsMultipleClose(){
		int counter = 0;
		for(Transaction t: this){
			if(t.getChoice() == Transaction.Choices.CLOSE){
				counter++;
			}
		}
		if(counter > 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean containsOnlyOpenClose()
	{
		boolean valid = true;
		for(Transaction t: this)
		{
			if(t.getChoice() == Transaction.Choices.CLOSE || t.getChoice() == Transaction.Choices.OPEN){}
			else
			{
				valid = false;
			}
		}
		return valid;
	}
	

}
