/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Stores a list of Summary objects
 * 
 */
package com.uni.summary;

import java.util.ArrayList;

import com.uni.Teller.Teller;
import com.uni.account.Transaction.Choices;
import com.uni.customer.Customer;
import com.uni.main.Statistics;

public class SummaryList extends ArrayList<Summary> {
	
	static SummaryList instance;
	
	/**
	 * Constructor for this SummaryList
	 */
	private SummaryList()
	{
		super();
	}
	
	/**
	 * Obtain the instance currently in use in the 
	 * system.
	 * If there is no instance in use it creates a new one.
	 * @return instance, the instance of the Log object. 
	 */
	public static SummaryList getInstance()
	{
		if(instance == null)
		{
			instance = new SummaryList();
		}
		return instance;
	}
	
	/**
	 * 
	 * Get all the stats for a particular customer
	 * 
	 * @param c the customer to get the statistics for
	 * @return a formated string of statistics for this customer
	 */
	public String getCustomerStats(Customer c){
		String retV = "";
		//initialise statistics
		int transactions = 0;
		int totalWithdrawn = 0, withdraws = 0;
		int totalDeposited = 0, deposits = 0;
		int opened = 0, closed = 0;
		//loop through stats
		for(Summary s: this){
			//if the stat is to do with this customer enter
			if(s.getC() == c){
				transactions++; //performed transaction
				//add to appropriate transaction type
				if(s.getType() == Choices.DEPOSIT){
					totalDeposited += s.getAmount();
					deposits++;
				}else if(s.getType() == Choices.WITHDRAW){
					withdraws++;
					totalWithdrawn += s.getAmount();
				}else if(s.getType() == Choices.OPEN)
					opened++;
				else if(s.getType() == Choices.CLOSE)
					closed++;
			}
		}
		//format the string
		retV = formatStat("Customer Name:" + c.getFullName(), transactions, deposits, totalDeposited,
				withdraws, totalWithdrawn, closed, closed); 
		return retV;
	}
	
	/**
	 * Get all the sumary stats associated with a 
	 * particular teller
	 * 
	 * @param t the teller to check
	 * @return the stats formatted as a string
	 */
	public String getTellerStats(Teller t){
		String retV = "";
		//initialise statistics
		int transactions = 0;
		int totalWithdrawn = 0, withdraws = 0;
		int totalDeposited = 0, deposits = 0;
		int opened = 0, closed = 0;
		//loop through summary list
		for(Summary s: this){
			//check if transaction performed by this teller
			if(s.getT() == t){
				transactions++;
				//Increment appropriate values
				if(s.getType() == Choices.DEPOSIT){
					totalDeposited += s.getAmount();
					deposits++;
				}
				if(s.getType() == Choices.WITHDRAW){
					withdraws++;
					totalWithdrawn += s.getAmount();
				}
				if(s.getType() == Choices.OPEN)
					opened++;
				if(s.getType() == Choices.CLOSE)
					closed++;
			}
		}
		//format as a string
		retV = formatStat("Teller id:" + t.getIdent(), transactions, deposits, totalDeposited,
				withdraws, totalWithdrawn, closed, closed); 
		return retV;

	}
	
	/**
	 * 
	 * Format a set of statistics as a string
	 * 
	 * @param firstLine a message line
	 * @param transactions number of transactions
	 * @param deposits number of deposits
	 * @param totalDeposited amount deposited
	 * @param withdraws number of withdraws
	 * @param totalWithdrawn amount withdrawn
	 * @param opened accounts opened
	 * @param closed accounts closed
	 * @return
	 */
	private String formatStat(String firstLine, int transactions, int deposits, int totalDeposited,
			int withdraws, int totalWithdrawn, int opened, int closed){
		String retV = "";
		retV += "\n*******************\n";
		retV +="\n" + firstLine + "\n";
		retV+="\nTotal transactions:" +transactions + "\n";
		retV+="\nTotal deposits:" + deposits + "\n";
		retV+="Deposit amount:" + Statistics.toPoundsAndPence(totalDeposited) + "\n";
		retV+="\nTotal withdraws:" + withdraws + "\n";
		retV+="Withdraw amount:" + Statistics.toPoundsAndPence(totalWithdrawn) + "\n";
		retV+="\nAccounts Opened:" + opened + "\n";
		retV+="\nAccounts Closed:" + closed + "\n";
		retV+="\n*******************\n";
		return retV;
	}
}
