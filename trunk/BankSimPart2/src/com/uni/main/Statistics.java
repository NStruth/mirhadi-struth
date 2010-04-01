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
	
	public static int last_customer = 1001;
	
	//
	public static int OPEN_TIME = 9;
	public static int CLOSE_TIME = 17;
	//
	
	public static boolean MANUAL_CLOSE_OVERRIDE = true; //0 for false
	
	
	public static long MINUTE_VAL = 10;
	public static long HOUR_VAL = 60 * MINUTE_VAL;
	
	public static int CURRENT_HOUR = 9;
	public static int CURRENT_MIN = 0;
	
	public static int SIMULATION_SPEED_FACTOR = 10;
	
	public static int SIMULATION_SPEED = 1000;
	public static int GENERATOR_SPEED = 100;
	public static int TELLER_SPEED = 100;
	public static int CLOCK_SPEED = 25;
	
	public static boolean CLOSED = false;
	public static boolean STOP = false;
	public static boolean FIRST_RUN = false;

	
	public static void reset(){
		TRANSACTION_TOTAL = 0;
		CUSTOMERS_SERVED = 0;
		
		ACCOUNTS_OPENED = 0;
		ACCOUNTS_CLOSED = 0;
		ACCOUNT_WITHDRAW = 0;
		ACCOUNT_DEPOSIT = 0;
		
		TOTALS_WITHDRAW = 0;
		TOTALS_DEPOSTIT = 0;
		
		last_customer = 1001;
		
		//
		OPEN_TIME = 9;
		CLOSE_TIME = 17;
		//
		
		MANUAL_CLOSE_OVERRIDE = true; //0 for false
		
		
		MINUTE_VAL = 10;
		HOUR_VAL = 60 * MINUTE_VAL;
		
		CURRENT_HOUR = 9;
		CURRENT_MIN = 0;
		
		SIMULATION_SPEED_FACTOR = 10;
		
		SIMULATION_SPEED = 1000;
		GENERATOR_SPEED = 100;
		TELLER_SPEED = 100;
		CLOCK_SPEED = 25;
		
		CLOSED = false;
		STOP = false;
	}
	
	
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
