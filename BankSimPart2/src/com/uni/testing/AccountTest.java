/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * JUnit testing class for Accounts.
 */
package com.uni.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uni.account.Account;


public class AccountTest {
	
	@Test public void testCreateAccount()
	{
		//Test default
		
		Account acc1 = new Account();
		
		String expected1 = "60001 : £100.00";
		String actual1 = acc1.toString();
		String message1 = "Create failed for default account";
		
		assertEquals(message1, expected1, actual1);
		
		//Test Conceptual Upper Bound - id 69999 balance 100000.00
		
		Account acc2 = new Account(10000000, 69999);
		
		String expected2 = "69999 : £100000.00";
		String actual2 = acc2.toString();
		String message2 = "Create failed for id - 69999 balance 100000.0";
		
		assertEquals(message2, expected2, actual2);
		
		//Test Conceptual Lower Bound - id 60000 balance 0.0
		
		Account acc3 = new Account(0, 60000);
		
		String expected3 = "60000 : £0.00";
		String actual3 = acc3.toString();
		String message3 = "Create failed for id - 60000 balance 0.0";
		
		assertEquals(message3, expected3, actual3);
		
	}
	
	@Test public void testToString()
	{	
		//Test 1 - Middle
		Account acc1 = new Account(12312, 60012);
		
		String expected1 = "60012 : £123.12";
		String actual1 = acc1.toString();
		String message1 = "Failed for id 60012, balance 123.12";
		
		assertEquals(message1, expected1, actual1);
		
		//Test 2 - Lower Bound
		Account acc2 = new Account(00 , 60000);
		
		String expected2 = "60000 : £0.00";
		String actual2 = acc2.toString();
		String message2 = "Failed for id 60000, balance 0.0";
		
		assertEquals(message2, expected2, actual2);
		
		//Test 3 - Upper Bound
		
		Account acc3 = new Account (1000000, 69999);
		
		String expected3 = "69999 : £10000.00";
		String actual3 = acc3.toString();
		String message3 = "Failed for id 69999, balance 100000.0";
		
		assertEquals(message3, expected3, actual3);
		
		
	}
	
	@Test public void testGetBalance()
	{
		Account acc1 = new Account();	
		int expected1 = 10000;
		int actual1 = acc1.getBalance();
		String message1 = "Failed for default account";
		assertEquals(message1, expected1, actual1);
		
		Account acc2 = new Account(0, 62345);
		int expected2 = 0;
		int actual2 = acc2.getBalance();
		String message2 = "Failed for default account";
		assertEquals(message2, expected2, actual2);
	}
	
	@Test public void testGetID()
	{
		Account acc = new Account(500, 60001);	
		int expected = 60001;
		int actual = acc.getAccountNumber();
		String message = "Failed for account balance = 50.0, id = 60001";
		assertEquals(message, expected, actual);
	}
	
	@Test public void testDeposit()
	{
		Account acc1 = new Account();
		acc1.deposit(5000);
		int expected1 = 15000;
		int actual1 = acc1.getBalance();
		String message1 = "Failed for default account - deposit £50";
		assertEquals(message1, expected1, actual1);	
		
		Account acc2 = new Account();
		acc2.deposit(-1);
		int expected2 = 10000;
		int actual2 = acc2.getBalance();
		String message2 = "Failed for default account - deposit -1p";
		assertEquals(message2, expected2, actual2);
		
		Account acc3 = new Account();
		acc3.deposit(100100);
		int expected3 = 10000;
		int actual3 = acc3.getBalance();
		String message3 = "Failed for default account - deposit £1001";
		assertEquals(message3, expected3, actual3);
	}
	
	
	@Test public void testWithdraw()
	{
		Account acc1 = new Account();
		acc1.withDraw(5000);
		int expected1 = 5000;
		int actual1 = acc1.getBalance();
		String message1 = "Failed for default account - withdraw £50";
		assertEquals(message1, expected1, actual1);
		
		Account acc2 = new Account();
		acc2.withDraw(-1);
		int expected2 = 10000;
		int actual2 = acc2.getBalance();
		String message2 = "Failed for default account - withdraw -1p";	
		assertEquals(message2, expected2, actual2);
		
		Account acc3 = new Account();
		acc3.withDraw(30000);		
		int expected3 = 10000;
		int actual3 = acc3.getBalance();
		String message3 = "Failed for default account - withdraw £300";		
		assertEquals(message3, expected3, actual3);
		
		Account acc4 = new Account();
		acc4.withDraw(20000);		
		int expected4 = 10000;
		int actual4 = acc4.getBalance();
		String message4 = "Failed for default account - withdraw £200";	
		assertEquals(message4, expected4, actual4);
	}

}