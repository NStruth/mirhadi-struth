/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Extends ArrayList to store a list of customers
 */
package com.uni.customer;

import java.util.ArrayList;

import com.uni.Logging.Log;

public class CustomerList extends ArrayList<Customer> {

	@Override
	public boolean add(Customer e) {
		// TODO Auto-generated method stub
		return super.add(e);
	}

	/**
	 * Print the list of customer to the log
	 */
	public void print()
	{
		for(Customer c: this){
			Log l = Log.getInstance();
			l.writeMessage(c.toString()); 
		}
	}
	
}
