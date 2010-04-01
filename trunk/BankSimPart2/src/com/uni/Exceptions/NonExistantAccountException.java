/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 *
 * Custom exception for when an account does not exist
 */
package com.uni.Exceptions;

public class NonExistantAccountException extends Exception{

	public NonExistantAccountException(){
		super("Account does not exist");
	}
	
}
