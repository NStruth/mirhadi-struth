/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * This is a language class to allow easy modification of the words
 * used in the log file.
 * 
 * Contains some static methods for money formatting
 * 
 * No changes for version 2.  Static formatting methods are used for GUI 
 * extension however language is hard coded for the GUI.
 */
package com.uni.Logging;

import com.uni.main.Statistics;

public class Language {

	
	public static String WITHDRAW_START = "\n\n *** Processing withdraw transaction *** \n\n";
	public static String DEPOSIT_START = "\n\n *** Processing deposit transaction *** \n\n";
	public static String OPEN_START = "\n\n *** Processing open transaction *** \n\n";
	public static String CLOSE_START = "\n\n *** Processing close transaction ** \n\n";
	
	public static String TRANSACTION_END = "\n *** End of transaction *** \n";
	public static String OPEN_END = "\n *** End of open transaction *** \n";
	public static String CLOSE_END = "\n *** End of close transaction *** \n";
	public static String WITHDRAW_END = "\n *** End of withdraw transaction *** \n";
	public static String DEPOSIT_END = "\n *** End of deposit transaction *** \n";
	
	public static String ERROR_NONEXISTANT_ACCOUNT = "Sorry that account doesn't exist";
	public static String ERROR_INSUFFICIENT_FUNDS = "Insufficient Funds";
	public static String ERROR_NO_ACCOUNTS_HELD = "No Accounts Held";
	public static String ERROR_TOO_MANY_ACCOUNTS = "Maximum Number of Accounts is 2";
	
	/**
	 * Static method to get customer info as a string
	 * @param name the customers name
	 * @param custNo the customer queue number
	 * @param acNo the customers account number
	 * @return
	 */
	public static String CustomerInfo(String name, String custNo, String acNo){
		return "Customer: " + name + " No: " + custNo + " AccountNo:" + acNo + "\n";
	}
	/**
	 * Static method to format a withdraw transaction
	 * @param value the value withdrawn
	 * @param newBal the new balance
	 * @return the formatted string
	 */
	public static String WithdrawInfo(int value, int newBal){
		return "Withdraw: " + Statistics.toPoundsAndPence(value) + " New Balance: " + Statistics.toPoundsAndPence(newBal) + "\n";
	}
	/**
	 * Static method to format deposit transaction
	 * @param value the deposited value
	 * @param newBal the new balance
	 * @return the formatted string
	 */
	public static String DepositInfo(int value, int newBal){
		return "Deposit: " + Statistics.toPoundsAndPence(value) + " New Balance: " + Statistics.toPoundsAndPence(newBal) + "\n";
	}
}
