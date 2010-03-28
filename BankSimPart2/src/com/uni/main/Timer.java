package com.uni.main;

import java.util.LinkedList;
import java.util.List;

import Observation.Observer;
import Observation.Subject;

import com.uni.Logging.Log;

public class Timer extends Thread implements Subject {

	boolean stopThread = false;
	
	public void done()
	{
		stopThread = true;
	}
	
	public void updateTime()
	{
		
		Statistics.CURRENT_MIN = (Statistics.CURRENT_MIN+1) % 60;
		if(Statistics.CURRENT_MIN == 0){
			Statistics.CURRENT_HOUR = (Statistics.CURRENT_HOUR+1) % 24;
		}
		
		
		
		notifyObservers();
		System.out.println("Current Time = "+Statistics.CURRENT_HOUR);
	}
	
	public void run()
	{
		Log.writeMessage("SIMULATION STARTED");
		while(!stopThread)
		{
			try {
				Thread.sleep(Statistics.CLOCK_SPEED);
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

}
