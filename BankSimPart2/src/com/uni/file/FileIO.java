/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * A class to handle File input and output for accounts
 * and customers.
 */
package com.uni.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javax.annotation.Resource;

import com.uni.account.Account;
import com.uni.account.AccountList;
import com.uni.customer.Customer;
import com.uni.customer.CustomerList;

public class FileIO {
	
	private String afilename = "data/accounts.txt"; //account file name
	private String cfilename = "data/customers.txt"; //customer file name
	private static FileIO instance;
	
	/**
	 * Constructor
	 * @param afilename the account file name
	 * @param cfilename the customer file name
	 */
	public FileIO()
	{}
	
	/**
	 * Reads the account file line by line
	 * and returns a list of accounts
	 * @return the list of accounts
	 */
	public AccountList readAccountLines()
	{
		try
		{
			File file = new File(afilename); // Non Jar
			Scanner scanner = new Scanner(file); //Non Jar
			//User below for JAR setup
			//InputStream in = getClass().getResourceAsStream
			  //(afilename);
			  //Scanner scanner = new Scanner(in);
			AccountList list = new AccountList();
			//new line delimiter
			scanner.useDelimiter("\r\n");//look into
			//so long as there are more lines read them
			while(scanner.hasNext())
			{
				list.add(parseAccountLine(scanner.next()));
			}
			scanner.close();
			return list;
		}catch(Exception e){
			return null;
		}
		//catch(FileNotFoundException e){return null;}
	}
	/**
	 * Parse a single line to produce an account
	 * @param line the line to parse
	 * @return the account
	 */
	private Account parseAccountLine(String line)
	{
			int id;
			int balance;
			Scanner lScanner = new Scanner(line);
			//delim between attributes
			lScanner.useDelimiter("::");
			id = Integer.parseInt(lScanner.next());
			balance = lScanner.nextInt();
			Account a = new Account(balance, id);
			return a;
	}
	/**
	 * Reads the customer file to produce a list of customers
	 * @return the list of customer
	 */
	public CustomerList readCustomerLines()
	{
		try{
			File file = new File(cfilename);//REMOVE
			Scanner scanner = new Scanner(file);//REMOVE
			/*InputStream in = getClass().getResourceAsStream
			  (cfilename);
			  Scanner scanner = new Scanner(in);*/
			CustomerList cList = new CustomerList();
			scanner.useDelimiter("\n");
			while(scanner.hasNext())
			{
				cList.add(parseCustomerLine(scanner.next()));
			}
			return cList;
		}
		catch(Exception e){return null;}
	}
	/**
	 * Parse an individual line to get a customer		
	 * @param line the line to be read
	 * @return the customer that was read
	 */
	private Customer parseCustomerLine(String line){
		String fName, lName, address;
		Scanner lScanner = new Scanner(line);
		Customer cust;
		
		lScanner.useDelimiter("::");
		fName = lScanner.next();
		lName = lScanner.next();
		address = lScanner.next();
		cust = new Customer(fName,lName, address);
		
		if(lScanner.hasNext()){
			int ac1 = lScanner.nextInt();
			cust.addAccount(ac1);
		}
		
		if(lScanner.hasNext()){
			int ac2 = lScanner.nextInt();
			cust.addAccount(ac2);
		}
		
		return cust;	
	}	
		
}
	
