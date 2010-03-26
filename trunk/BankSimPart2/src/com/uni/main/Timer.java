package com.uni.main;

import com.uni.Logging.Log;

public class Timer extends Thread {
	
	public void run()
	{
		Log.writeMessage("SIMULATION STARTED");
		try {
			Thread.sleep(Statistics.SIMULATION_TIME);
			Statistics.CLOSED = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
