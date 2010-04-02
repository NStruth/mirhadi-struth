package com.uni.summary;

import java.util.ArrayList;

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
	
	public String getTotalWithdrawn(Customer c)
	{
		int withdraw = 0;
		for(Summary s: this)
			if(s.getC() == c && s.getType() == Choices.WITHDRAW)
			{
				withdraw += s.getAmount();
			}
			else
				if(s.getC() == c && s.getType() == Choices.CLOSE)
				{
					withdraw += s.getAmount();
				}
		return Statistics.toPoundsAndPence(withdraw);
	}

}
