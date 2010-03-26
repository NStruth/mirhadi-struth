/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A customer queue represents a list of customers in the queue
 * at the bank, each with one or more transactions.
 */

package com.uni.queue;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import com.uni.customer.Customer;

public class CustomerQueue extends ArrayList<QueueItem>{
		
	/**
	 * Constructor for the customer queue
	 */
	public CustomerQueue(){
		super();
	}

	@Override
	public boolean add(QueueItem arg0) {
		
		return super.add(arg0);
	}
	
	@Override
	public String toString(){
		String retV = "";
		for(QueueItem qi:this){
			retV += qi.toString();
		}
		return retV;
	}
	
	public boolean customerInQueue(Customer c){
		boolean retV = false;
		for(QueueItem i: this){
			if(i.getCustomer() == c)
				return true;
		}
		return retV;
	}
	/**
	 * get the first queue item in the list
	 * @return the first queue item
	 * 
	 * @throws Exception if no one in the queue
	 */
	public QueueItem getFirst() throws Exception{
		
		if(!this.isEmpty()){
			QueueItem q = this.remove(0);
			return q;
		}
		throw new Exception();
	}	
}
