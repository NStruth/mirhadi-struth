/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A class containing static fields and methods
 * for storing and formatting statistics.
 */
package com.uni.main;

public class Statistics {

	public static int TRANSACTION_TOTAL = 0;
	public static int CUSTOMERS_SERVED = 0;
	
	public static int ACCOUNTS_OPENED = 0;
	public static int ACCOUNTS_CLOSED = 0;
	public static int ACCOUNT_WITHDRAW = 0;
	public static int ACCOUNT_DEPOSIT = 0;
	
	public static int TOTALS_WITHDRAW = 0;
	public static int TOTALS_DEPOSTIT = 0;
	
	public static long SIMULATION_TIME = 1000000;
	
	public static boolean CLOSED = false;

	/**
	 * @param value the value to display
	 * @return the value as a string in the format £##.##
	 */
	public static String toPoundsAndPence(int value){
		
		//divide by 100 to get number of pounds
		int div = value/100;
		//char pound = "\£";
		//get the pence
		int remainder = value -(div * 100);
		//build the string
		String rem = "";
		if(remainder<10){
			rem = "0" + remainder;
		}else{
			rem = "" + remainder;
		}
		//return the value as a formatted string
		return "£" + div + "." + rem;
	}
	
}
