package com.uni.summary;

import java.util.ArrayList;

import com.uni.Teller.Teller;
import com.uni.account.Transaction.Choices;
import com.uni.customer.Customer;
import com.uni.main.Statistics;

public class SummaryList extends ArrayList<Summary> {
	
	static SummaryList instance;
	
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
	
	
	
	public String getCustomerStats(Customer c){
		String retV = "";
		int transactions = 0;
		int totalWithdrawn = 0, withdraws = 0;
		int totalDeposited = 0, deposits = 0;
		int opened = 0, closed = 0;
		
		for(Summary s: this){
			if(s.getC() == c){
				transactions++;
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
		
		retV += "\n*******************\n";
		retV+= "Customer Name:" + c.getFullName();
		retV+="Total transactions:" +transactions + "\n";
		retV+="\nTotal deposits:" + deposits + "\n";
		retV+="Deposit amount:" + totalDeposited + "\n";
		retV+="\nTotal withdraws:" + withdraws + "\n";
		retV+="Withdraw amount:" + totalWithdrawn + "\n";
		retV+="\nAccounts Opened:" + opened + "\n";
		retV+="\nAccounts Closed:" + closed + "\n";
		retV+="\n*******************\n";
		
		return "";
	}
	
	public String getTellerStats(Teller t){
		String retV = "";
		
		int transactions = 0;
		int totalWithdrawn = 0, withdraws = 0;
		int totalDeposited = 0, deposits = 0;
		int opened = 0, closed = 0;
		
		for(Summary s: this){
			if(s.getT() == t){
				transactions++;
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
		retV += "\n*******************\n";
		retV+= "Teller id:" + t.getIdent();
		retV+="Total transactions:" +transactions + "\n";
		retV+="\nTotal deposits:" + deposits + "\n";
		retV+="Deposit amount:" + totalDeposited + "\n";
		retV+="\nTotal withdraws:" + withdraws + "\n";
		retV+="Withdraw amount:" + totalWithdrawn + "\n";
		retV+="\nAccounts Opened:" + opened + "\n";
		retV+="\nAccounts Closed:" + closed + "\n";
		retV+="\n*******************\n";
		
		return retV;

	}
}
