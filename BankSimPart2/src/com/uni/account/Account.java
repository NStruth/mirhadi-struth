/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A class to manage accounts.  
 * Each account has a unique identifier and a balance.
 * Users can withdraw and deposit money. Overdrafts
 * are not allowed.
 */
package com.uni.account;

import com.uni.Logging.Log;
import com.uni.main.Statistics;

public class Account {

	//the master account number
	public static int masterNumber = 60000;
	
	private int accountNumber; //individual identifier
	private int balance; //in pence
	
	private Log l = Log.getInstance();
		
	/**
	 * Constructor for when setting pre defined accounts
	 * @param balance the balance
	 * @param id the account number
	 */
	public Account(int balance, int id){
		l.writeMessage("Creating account : "+id);
		this.balance = balance;
		this.accountNumber = id;
		masterNumber++;
	}
	
	/**
	 * Constructor used when teller creates an account
	 * default customer is given £100 to start
	 */
	public Account()
	{
		this.balance = 10000;
		masterNumber++;
		this.accountNumber = masterNumber;
	}
	
	/**
	 * Withdraw money from the account if possible
	 * 
	 * @param value the value to be withdrawn
	 * @return true if valid transaction else false
	 */
	public boolean withDraw(int value){
		if(value > this.balance || value > 20000 || value < 0){
			return false;
		}else{
			this.balance = this.balance - value;
			return true;
		}
	}
	/**
	 * Deposit money into the account
	 * @param value the value to be deposited
	 */
	public boolean deposit(int value){
		if(value >= 0)
		{
			this.balance += value;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Write the balance to the log
	 */
	public void displayBalance(){
		l.writeMessage("Balance: " + this.balance);
	}
	/**
	 * Get the balance
	 * @return the balance
	 */
	public int getBalance()
	{
		return this.balance;
	}
	/**
	 * Get the account number
	 * @return the account number
	 */
	public int getAccountNumber(){
		return accountNumber;
	}
	/**
	 * to string method for this class.
	 * Print account number and balance.
	 */
	public String toString()
	{
		return this.accountNumber + " : " + Statistics.toPoundsAndPence(getBalance());
	}
}
