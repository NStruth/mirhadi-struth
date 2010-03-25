/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A class that represents a customer at the bank.
 * A customer has a first name, a last name, an address
 *  and a list of accounts. A customer may have at most 2 accounts.
 */
package com.uni.customer;

import java.util.ArrayList;

public class Customer {

	private String fName, lName;
	private String address;
	private ArrayList<Integer> accList;
	
	//Status indicates whether customer is in the queue or not
	//0 = not 1 = in queue
	//initially a customer is not in the queue
	private int status = 0;
	
	
	/**
	 * @param fName the customers first name
	 * @param lName the customers last name
	 * @param address the customers address
	 */
	public Customer(String fName, String lName, String address){
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.accList = new ArrayList<Integer>();
	}
	

	/**
	 * Add an account only if the use does not
	 * already have 2 accounts. Redundant check.
	 * 
	 * @param c the accound number to add
	 * @return true if it can be added
	 */
	public boolean addAccount(int c){
		if(this.accList.size() == 2){
			return false;
		}else{
			return this.accList.add(c);		
		}
	}
	
	/**
	 * Get the customer queue status
	 * 
	 * @return the queue status
	 */
	public int getStatus(){
		return this.status;
	}
	/**
	 * Set the queue status
	 * @param s the new status
	 */
	public void setStatus(int s){
		this.status = s;
	}
	/**
	 * Get the customers full name
	 * @return the customers full name
	 */
	public String getFullName(){
		return fName + " " + lName;
	}
	/**
	 * Get the number of accounts
	 * @return the number of accounts
	 */
	public int getNumOfAccounts(){
		return this.accList.size();
	}
	/**
	 * Get the account number at a particular index
	 * @param acId the index of the account
	 * @return the account number
	 */
	public int getAccountNo(int acId){
		int acNo = accList.get(acId);
		return acNo;
	}
		
	/**
	 * Remove an account at an id
	 * @param id the index of the account
	 */
	public boolean removeAccount(int id){
		if(this.getNumOfAccounts() >= id){
			this.accList.remove(id);	
			return true;
		}
		else
			return false;	
	}
	/**
	 * To string method for this class.
	 */
	public String toString()
	{
		return this.fName + " " + this.lName + " " + this.address;
	}
}