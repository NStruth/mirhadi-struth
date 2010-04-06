/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * This is the timer class which extends thread.
 * It runs at a user specified speed incrementing time
 * by 1 unit (a minute) each time.
 * It is a subject so the Gui can observe it's changes
 * 
 */

package com.uni.main;

import java.util.LinkedList;
import java.util.List;

import Observation.Observer;
import Observation.Subject;

import com.uni.Logging.Log;

public class Timer extends Thread implements Subject {
	
	boolean stopThread = false;
	boolean pleaseWait = false;
	
	/**
	 * Stop the thread loop
	 */
	public void done()
	{
		stopThread = true;
	}
	
	/**
	 * Update the time by 1 minute
	 */
	public void updateTime()
	{
		
		Statistics.CURRENT_MIN = (Statistics.CURRENT_MIN+1) % 60;
		if(Statistics.CURRENT_MIN == 0){
			Statistics.CURRENT_HOUR = (Statistics.CURRENT_HOUR+1) % 24;
		}	
		notifyObservers();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		
		Log l = Log.getInstance();
		l.writeMessage("SIMULATION STARTED");
		while(!stopThread)
		{
			
			/* Please see comment in BankSimulator.java in method pause */
			//Check if should wait 
			synchronized (this) {
				while (pleaseWait) { 
					try { wait(); } catch (Exception e) { } 
					} 
				} 
			
				try {
					Thread.sleep(Statistics.CLOCK_SPEED * Statistics.SIMULATION_SPEED_FACTOR);
					updateTime();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
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

	synchronized void pause() {
		// TODO Auto-generated method stub
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
