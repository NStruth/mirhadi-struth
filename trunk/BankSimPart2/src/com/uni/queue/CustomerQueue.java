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
import java.util.LinkedList;
import java.util.List;

import Observation.Observer;
import Observation.Subject;

import com.uni.customer.Customer;

public class CustomerQueue extends ArrayList<QueueItem> implements Subject{
	
	int next;
	int last;
	
	/**
	 * Constructor for the customer queue
	 */
	public CustomerQueue(){
		super();
	}

	@Override
	public boolean add(QueueItem arg0) {
		notifyObservers();
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
	
	public int getNextNumber(){
		return last;
	}
	
	/**
	 * get the first queue item in the list
	 * @return the first queue item
	 * 
	 * @throws Exception if no one in the queue
	 */
	public synchronized QueueItem getFirst() throws Exception{
		
		if(!this.isEmpty()){
			QueueItem q = this.remove(0);
			//last = q.getCustNo();
			//next = last + 1;
			notifyObservers();
			return q;
		}
		throw new Exception();
	}	
	
////////////////////////////////////////////////////////
	//OBSERVER PATTERN
	//SUBJECT must be able to register, remove and notify observers
	//list to hold any observers
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	//methods to register, remove and notify observers
	public void registerObserver( Observer obs)
	{
		registeredObservers.add( obs);
	}
	
	public void removeObserver( Observer obs)
	{
		registeredObservers.remove( obs);
	}
	
	public void notifyObservers()
	{
		for( Observer obs : registeredObservers)
			obs.update();
	}
	//////////////////////////////////////////////////////// 	
}
