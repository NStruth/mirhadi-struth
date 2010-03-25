/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * This is the main class for the BankSimulator.
 * This is based on requirements set out by Monica Farrow for
 * the Advanced Software Engineering.
 * Version 1 represents stage 1.
 * Version 2 represents stage 2.
 * 
 * This simulated a bank with accounts and a pool of customers,
 * some with accounts and some without.  A random queue is 
 * generated with each customer containing transactions that 
 * are then processed by a teller.
 * Summary results are then displayed in a basic gui.
 */
package com.uni.main;

import com.uni.Logging.Log;
import com.uni.Teller.Teller;
import com.uni.account.AccountList;
import com.uni.account.Transaction;
import com.uni.account.TransactionList;
import com.uni.customer.CustomerList;
import com.uni.file.FileIO;
import com.uni.gui.GuiDisplay;
import com.uni.queue.CustomerQueue;
import com.uni.queue.QueueItem;


public class BankSimulator {
	
	/**
	 * The main class for this application
	 * @param args
	 */
	public static void main(String[] args) {
		//clear the log file				
		Log.clearLog();
		
		//read in list of accounts and customers
		FileIO filehandle = new FileIO("/data/accounts.txt","/data/customers.txt");
		AccountList al = filehandle.readAccountLines();
		CustomerList cl = filehandle.readCustomerLines();
		
		/* TEST DATA - Left in for convenience
		//TODO move this test data somewhere nicer
		QueueItem[] testArray = new QueueItem[6];
		
		TransactionList tList0 = new TransactionList();
		tList0.add((new Transaction(Transaction.Choices.DEPOSIT, 10000, cl.get(3).getAccountNo(0))));
		testArray[0] = new QueueItem(cl.get(3), tList0);
		
		TransactionList tList1 = new TransactionList();
		tList1.add(new Transaction(Transaction.Choices.DEPOSIT, 10000, cl.get(0).getAccountNo(0)));
		tList1.add(new Transaction(Transaction.Choices.DEPOSIT, 10000, cl.get(0).getAccountNo(1)));
		testArray[1] = new QueueItem(cl.get(0), tList1);
		
		TransactionList tList2 = new TransactionList();
		tList2.add(new Transaction(Transaction.Choices.WITHDRAW, 50000, cl.get(2).getAccountNo(0)));
		tList2.add(new Transaction(Transaction.Choices.WITHDRAW, 50000, cl.get(2).getAccountNo(0)));
		testArray[2] = new QueueItem(cl.get(2), tList2);
		
		TransactionList tList3 = new TransactionList();
		tList3.add((new Transaction(Transaction.Choices.CLOSE,  0)));
		tList3.add((new Transaction(Transaction.Choices.CLOSE,  0)));
		tList3.add((new Transaction(Transaction.Choices.CLOSE,  0)));
		testArray[3] = new QueueItem(cl.get(5), tList3);
		
		TransactionList tList4 = new TransactionList();
		tList4.add(new Transaction(Transaction.Choices.DEPOSIT, 10000, cl.get(5).getAccountNo(0)));
		testArray[4] = new QueueItem(cl.get(5), tList4);
		
		
		TransactionList tList5 = new TransactionList();
		tList5.add(new Transaction(Transaction.Choices.OPEN));
		tList5.add(new Transaction(Transaction.Choices.OPEN));
		tList5.add(new Transaction(Transaction.Choices.OPEN));
		testArray[5] = new QueueItem(cl.get(4), tList5);
		/* END TEST DATA */
		
		/* Generate a random queue */
		
		Generator g = new Generator(cl, al);
		Log.writeMessage(al.toString());
		Log.writeMessage("DISPLAYING CUSTOMER LIST");
		cl.print();
					
		CustomerQueue cq = g.generate();
		Log.writeMessage("\n\n" + cq.toString());
		
		/* Set up the teller */
		Teller teller = new Teller(al);
		
		/* Uncomment for testing */
		/*for(QueueItem qi: testArray){
			cq.add(qi);
		}*/
		
		/* Process the queue */
		int size = cq.size();
		for(int i=0; i<size;i++){
			teller.processQueueItem(cq.get(i));
		}
		
		Log.writeMessage("State AFTER transactions");
		Log.writeMessage(al.toString());
		
		//display summary results
		GuiDisplay gd = new GuiDisplay();
		
	}
}
