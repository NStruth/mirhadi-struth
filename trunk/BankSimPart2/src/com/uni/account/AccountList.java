/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Extends ArrayList to store a list of accounts with
 * account specific methods e.g. open/close account
 */
package com.uni.account;

import java.util.ArrayList;

import com.uni.Exceptions.NonExistantAccountException;

public class AccountList extends ArrayList<Account>{
	/**
	 * Opens a new account by placing it in this list
	 * @param ac the account to be added
	 */
	public void openAccount(Account ac){
		this.add(ac.getAccountNumber(), ac);
	}

	/**
	 * Remove a parrticular accpimt
	 * @param ac
	 */
	public void closeAccount(Account ac){
		this.remove(ac);
	}
	/**
	 * To string method to print the list of accounts
	 */
	public String toString(){
		String retV = "\nPrinting account list\n";
		for(Account a: this){
			retV = retV + a.toString() + "\n";
		}
		return retV;
	}
	/**
	 * Get an account at a particular account number
	 * @param index the account number
	 * @return the account
	 * @throws NonExistantAccountException if the account does not exist
	 */
	public Account getAccountAtIndex(int index) throws NonExistantAccountException{
		for(Account a: this){
			if(a.getAccountNumber() == index){	
				return a;
			}
		}
		throw new NonExistantAccountException();
	}
	/**
	 * Remove account with a particular account 
	 * number
	 * 
	 * @param acNo
	 * @return
	 */
	public boolean removeAccountNo(int acNo){
		for(Account a: this){
			if(a.getAccountNumber() == acNo){
				this.remove(a);
				return true;
			}
		}
		return false;
	}	
}
