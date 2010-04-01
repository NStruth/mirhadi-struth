/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * Object which implements the Singleton pattern as we only
 * wish one instance to update the log file.
 *
 * Can be accessed through the static method getInstance()
 */
package com.uni.Logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Log {
	
	private static Log instance = new Log();
	

	//Private constructor
	private Log()
	{}
	
	/**
	 * Obtain the instance currently in use in the 
	 * system.
	 * If there is no instance in use it creates a new one.
	 * @return instance, the instance of the Log object. 
	 */
	public static Log getInstance()
	{
		if(instance == null)
		{
			instance = new Log();
		}
		return instance;
	}
	
	/**
	 * Clear the log file
	 */
	public void clearLog()
	{
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"));
			writer.write("");
			writer.close();
			}
		catch(IOException e)
		{
			System.out.println("Error Clearing Log : " + e.getMessage());
		}
	}
	/**
	 * Write a message to the log file
	 * @param message the message to be written to the log file
	 */
	public void writeMessage(String message){
		//System.out.println(message);
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
			writer.write(message + '\n');
			writer.close();
			}
		catch (IOException e)
		{
			System.out.println("IO ERROR : " + e.getMessage());
		}
		
	}
}
