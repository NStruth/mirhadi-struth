/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * This class allows static calls from any class to write to the
 * log file. As it is static there is no constructor.
 */
package com.uni.Logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Log {

	/**
	 * Clear the log file
	 */
	public static void clearLog()
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
	public static void writeMessage(String message){
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
