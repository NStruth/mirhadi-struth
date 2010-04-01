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
import com.uni.Teller.TellerList;
import com.uni.account.AccountList;
import com.uni.customer.CustomerList;
import com.uni.file.FileIO;
import com.uni.gui.GuiMain;
import com.uni.queue.CustomerQueue;


public class BankSimulator {
	
	private static final int NUM_OF_TELLERS = 3;
	
	public TellerList tellerList;
	public Teller teller;
	
	private CustomerQueue cq;
	private CustomerQueue ocq;

	private Generator g;
	
	private Timer time;

	private CustomerList cl;

	private AccountList al;

	private GuiMain gm;
	
	private boolean runOnce = false;
	/**
	 * The main class for this application
	 * @param args
	 * @throws InterruptedException 
	 */
	public void run() throws InterruptedException {
		//clear the log file
		Log l = Log.getInstance();
		l.clearLog();
		
		//read in list of accounts and customers
		FileIO filehandle = new FileIO();
		al = filehandle.readAccountLines();
		cl = filehandle.readCustomerLines();
		
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
		
		
		l.writeMessage(al.toString());
		l.writeMessage("DISPLAYING CUSTOMER LIST");
		cl.print();
				
		//Create main queue and the open/close account queue
		cq = new CustomerQueue();
		ocq = new CustomerQueue();
		
		//The clock thread for the simulation
		time = new Timer();
		
		g = new Generator(cl, al, cq, ocq);
		
		l.writeMessage("\n\n" + cq.toString());
		
		/* Set up the tellers */
		tellerList = new TellerList();
		for(int i = 0; i < NUM_OF_TELLERS; i++){
			teller = new Teller(al, cq, ocq);
			tellerList.add(teller);
			
		}
		
		
		/* Uncomment for testing */
		/*for(QueueItem qi: testArray){
			cq.add(qi);
		}*/
		
		/* Process the queue 
		int size = cq.size();
		for(int i=0; i<size;i++){
			teller.processQueueItem(cq.get(i));
		}*/
		
		
		l.writeMessage("State AFTER transactions");
		l.writeMessage(al.toString());
		
		//display summary results
		//GuiDisplay gd = new GuiDisplay();
		
		//Display the Graphical User Interface
		gm = new GuiMain(this);
		
		//startSimulation();
		//Thread.sleep(10000);
		//this.resetSimulation();


	}
	/**
	 * Get next customer from queue
	 * Checks to see if there are any customers in the open/close queue
	 * if not get the next customer number from the main queue.
	 * @return customer number
	 */
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
		
		if(runOnce){
			restart();
		}else{
			runOnce = true;
		}
		
		for(Teller t: tellerList){
			t.start();
		}
		//g.generate();
		g.start();
		time.start();
		Statistics.MANUAL_CLOSE_OVERRIDE = false;
	}
	
	/*public void resetSimulation(){
		g.done();
		time.done();
		
		cq = new CustomerQueue();
		ocq = new CustomerQueue();
		
		time = new Timer();
		
		g = new Generator(cl, al, cq, ocq);
		
			
		//CustomerQueue cq = g.generate();
		//l.writeMessage("\n\n" + cq.toString());
		
		// Set up the teller 
		tellerList = new TellerList();
		for(int i = 0; i < NUM_OF_TELLERS; i++){
			teller = new Teller(al, cq, ocq);
			tellerList.add(teller);
			
		}
		this.startSimulation();
	}*/
	
	
	public void pause(){


		for(Teller t: tellerList){
		if(t.pleaseWait){
			synchronized (t) { t.pleaseWait = false; t.notify(); } 
		}else{
			synchronized (t) { t.pleaseWait = true; } 
			
		}
		}
		if(g.pleaseWait){
			synchronized (g) { g.pleaseWait = false; g.notify(); } 
		}else{
			synchronized (g) { g.pleaseWait = true; } 
			
		}
		
		//while()
		
		if(time.pleaseWait){
			synchronized (time) { time.pleaseWait = false; time.notify(); } 
		}else{
			synchronized (time) { time.pleaseWait = true; } 
			
		}
		
		
		g.done();
		for(Teller t: tellerList)
			t.done();
		time.done();
		
		///////////////////////////
		
		
	}
	
	private void restart(){
		//cq = new CustomerQueue();
		//ocq = new CustomerQueue();
		
		//The clock thread for the simulation
		time = new Timer();
		
		g = new Generator(cl, al, cq, ocq);
		
		//l.writeMessage("\n\n" + cq.toString());
		
		/* Set up the tellers */
		tellerList = new TellerList();
		for(int i = 0; i < NUM_OF_TELLERS; i++){
			teller = new Teller(al, cq, ocq);
			tellerList.add(teller);
			
		}
		
		gm.resetComponents();
	}
	
	public void reset(){
		Statistics.reset();
		this.restart();
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
	
			/*g.done();
			time.done();
			for(Teller t: tellerList)
			{
				t.done();
			}*/
		Statistics.MANUAL_CLOSE_OVERRIDE = true;
		
		
		
		System.out.println("Simulation Stopped!");
	}
}
