package com.uni.main;

import com.uni.Logging.Log;

public class Timer extends Thread {

	
	public void updateTime()
	{
		if(Statistics.CURRENT_HOUR == 23)
			Statistics.CURRENT_HOUR = 0;
		else
			Statistics.CURRENT_HOUR++;
		
		System.out.println("Current Time = "+Statistics.CURRENT_HOUR);
	}
	
	public void run()
	{
		Log.writeMessage("SIMULATION STARTED");
		while(true)
		{
			try {
				Thread.sleep(Statistics.HOUR_VAL);
				updateTime();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
