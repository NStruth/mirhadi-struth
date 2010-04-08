/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
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
 * 
 * 
 * Version 2 expands the initial system by utilising a GUI
 * giving the user some basic controls and real time data.
 * The simulation is now threaded so can run for an infinite 
 * time, contains multiple tellers, summary statistics and 
 * much more. 
 */
package com.uni.main;

import com.uni.Logging.Log;
import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;
import com.uni.account.AccountList;
import com.uni.account.Transaction;
import com.uni.account.TransactionList;
import com.uni.customer.Customer;
import com.uni.customer.CustomerList;
import com.uni.file.FileIO;
import com.uni.gui.GuiMain;
import com.uni.queue.CustomerQueue;
import com.uni.queue.QueueItem;
import com.uni.summary.SummaryList;


public class BankSimulator {
	
	//The number of tellers
	private static final int NUM_OF_TELLERS = 3;
	//a place to store the teller
	public TellerList tellerList;
	public Teller teller;
	
	private CustomerQueue cq; //customer queue
	private CustomerQueue ocq; //open/close queue
	//the queue generator
	private Generator g;
	//Timer to manage time
	private Timer time;
	//a list of customers
	private CustomerList cl;
	//a list of accounts
	private AccountList al;
	//the gui 
	private GuiMain gm;
	/*
	 * this variable is required when to 
	 * distinguish between starting or resuming
	 * the simulation
	 */
	private boolean runOnce = false;
	//the object to write to a log file
	private Log l;
	
	/**
	 * The main method for this application
	 * @param args
	 * @throws InterruptedException 
	 */
	public void run() throws InterruptedException {
		//clear the log file
		l = Log.getInstance();
		l.clearLog();
		
		//read in list of accounts and customers
		FileIO filehandle = new FileIO();
		al = filehandle.readAccountLines();
		cl = filehandle.readCustomerLines();
		
		/* TEST DATA - Left in for convenience */
		//TODO move this test data somewhere nicer
		QueueItem[] testArray = new QueueItem[16];
		
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
		
//new data
		
		TransactionList tList6 = new TransactionList();
		tList6.add(new Transaction(Transaction.Choices.WITHDRAW, 800000, cl.get(20).getAccountNo(0)));
		testArray[6] = new QueueItem(cl.get(20), tList6);
		
		TransactionList tList7 = new TransactionList();
		tList7.add(new Transaction(Transaction.Choices.WITHDRAW, 100000, cl.get(21).getAccountNo(0)));
		testArray[7] = new QueueItem(cl.get(21), tList7);
		
		TransactionList tList8 = new TransactionList();
		tList8.add(new Transaction(Transaction.Choices.WITHDRAW, 10000, cl.get(20).getAccountNo(0)));
		testArray[8] = new QueueItem(cl.get(20), tList8);
		
		TransactionList tList9 = new TransactionList();
		tList9.add(new Transaction(Transaction.Choices.WITHDRAW, 10000, cl.get(21).getAccountNo(0)));
		testArray[9] = new QueueItem(cl.get(21), tList9);
		
		TransactionList tList10 = new TransactionList();
		tList10.add(new Transaction(Transaction.Choices.DEPOSIT, 10000, cl.get(20).getAccountNo(0)));
		testArray[10] = new QueueItem(cl.get(20), tList10);
		
		TransactionList tList11 = new TransactionList();
		tList11.add(new Transaction(Transaction.Choices.WITHDRAW, 10000, cl.get(21).getAccountNo(0)));
		testArray[11] = new QueueItem(cl.get(21), tList11);
		
		TransactionList tList12 = new TransactionList();
		tList11.add(new Transaction(Transaction.Choices.CLOSE,0));
		testArray[12] = new QueueItem(cl.get(20), tList12);
		
		TransactionList tList13 = new TransactionList();
		tList11.add(new Transaction(Transaction.Choices.WITHDRAW, 10000, cl.get(21).getAccountNo(0)));
		testArray[13] = new QueueItem(cl.get(21), tList13);
		
		TransactionList tList14 = new TransactionList();
		tList11.add(new Transaction(Transaction.Choices.OPEN));
		testArray[14] = new QueueItem(cl.get(21), tList14);
		
		TransactionList tList15 = new TransactionList();
		tList11.add(new Transaction(Transaction.Choices.OPEN));
		testArray[15] = new QueueItem(cl.get(21), tList15);
			
		
		/* END TEST DATA */
		
		/* Write customers to log file */	
		l.writeMessage(al.toString());
		l.writeMessage("DISPLAYING CUSTOMER LIST");
		cl.print();
				
		//Create main queue and the open/close account queue
		
		cq = new CustomerQueue();
		ocq = new CustomerQueue();
		
		
		/* Uncomment following block for testing.
		 * Uses the test queue instead of 
		 * randomly generating it.
		 */
		cq = new CustomerQueue();
		for(QueueItem qi: testArray){
			cq.add(qi);
		}
		/*END UNCOMMENT SECTION */
		
		
		//The clock thread for the simulation
		time = new Timer();
		//set up the generator
		g = new Generator(cl, al, cq, ocq);
		//TODO don't need next line
		l.writeMessage("\n\n" + cq.toString());
		
		/* Set up the tellers */
		tellerList = new TellerList();
		for(int i = 0; i < NUM_OF_TELLERS; i++){
			teller = new Teller(al, cq, ocq);
			tellerList.add(teller);
			
		}
		
		
		//TODO version 1 clean up 
		/* Process the queue 
		int size = cq.size();
		for(int i=0; i<size;i++){
			teller.processQueueItem(cq.get(i));
		}*/
		
		
		//Display the Graphical User Interface
		gm = new GuiMain(this);
	}
	
	/**
	 * Get next customer from queue
	 * Checks to see if there are any customers in the open/close queue
	 * if not get the next customer number from the main queue.
	 * @return customer number
	 */
	//TODO could make this throw an exception instead of -1
	 public int getNext(){
		 if(ocq.size() > 0)
		 return ocq.get(0).getCustNo();
		 else if(cq.size() > 0)
		 return cq.get(0).getCustNo();
		 else 
		 return -1;
	 }
	 
	/**
	 * Starts the threads required for the simulation
	 */
	public void startSimulation(){
		//if it's already been initialised restart
		//otherwise it has now run once!
		if(runOnce){
			restart();
		}else{
			runOnce = true;
		}
		//start the tellers
		for(Teller t: tellerList){
			t.start();
		}
		//g.start(); //start generator
		time.start(); //start the timer
		//make sure the bank is open even on resume
		Statistics.MANUAL_CLOSE_OVERRIDE = false;
	}
	
	
	/**
	 * A method to pause the simulation.
	 * Teller will not pause until transaction is finished
	 */
	public void pause(){
		// -----START BLOCK -----
		//it appears the following "Block" is not enough.
		//seems best to kill the threads and try restart 
		//in previous state
		//do for all tellers
		for(Teller t: tellerList){
			//change the thread state
			if(t.pleaseWait){
				synchronized (t) { t.pleaseWait = false; t.notify(); } 
			}else{
				synchronized (t) { t.pleaseWait = true; } 
				
			}
		}
		//same for generator
		if(g.pleaseWait){
			synchronized (g) { g.pleaseWait = false; g.notify(); } 
		}else{
			synchronized (g) { g.pleaseWait = true; } 
			
		}
		//and finally time		
		if(time.pleaseWait){
			synchronized (time) { time.pleaseWait = false; time.notify(); } 
		}else{
			synchronized (time) { time.pleaseWait = true; } 
			
		}
		// -------END BLOCK ------
		
		//seems they need to be marked as "done" instead
		g.done();
		for(Teller t: tellerList)
			t.done();
		time.done();
		
		///////////////////////////
		
		
	}
	
	/**
	 * Restarts the simualtion
	 */
	private void restart(){
		//recreate the timer
		time = new Timer();
		//recreate generator
		g = new Generator(cl, al, cq, ocq);
				
		/* Set up the tellers */
		tellerList = new TellerList();
		for(int i = 0; i < NUM_OF_TELLERS; i++){
			teller = new Teller(al, cq, ocq);
			tellerList.add(teller);
			
		}
		//reset the gui
		gm.resetComponents();
		Statistics.RUN_ONCE = true;
	}
	
	/**
	 * Reset the simulation
	 * This restores the default state of the 
	 * program and restarts
	 */
	public void reset(){
		Statistics.reset();
		this.restart();
	}
	
	/**
	 * Write the collected summary stats to a file
	 */
	public void writeSummary(){
		SummaryList s = SummaryList.getInstance();
		//the teller stats
		String summary = "Teller Summary:\n\n";
		for(Teller t: tellerList){
			summary += s.getTellerStats(t);
		}
		//customer stats
		summary += "Customer Summary: \n\n";
		for(Customer c: cl)
			summary += s.getCustomerStats(c);
		//write it
		l.writeSummary(summary);
	}
	
	/**
	 * Provides access to the private property Queue
	 * @return the Main Queue
	 */
	public CustomerQueue getQueue(){
		return cq;
	}
	
	/**
	 * Provides access to the private property OCQueue
	 * @return the Open/Close Queue
	 */
	public CustomerQueue getOCQueue()
	{
		return ocq;
	}
	
	/**
	 * Provides access to the private property Generator
	 * @return the reference to the Generator object.
	 */
	public Generator getGenerator(){
		return g;
	}

	/**
	 * Provides access to the private property Timer
	 * @return a reference to the Timer which controls the
	 * clock.
	 */
	public Timer getTimer() {
		return time;
	}

	/**
	 * Provides access to the private property tellerList
	 * @return the list of tellers
	 */
	public TellerList getTellerList(){
		return tellerList;
	}
	
	/**
	 * Closes the bank 
	 */
	public void closeBank() {
		Statistics.MANUAL_CLOSE_OVERRIDE = true;
	}
}
