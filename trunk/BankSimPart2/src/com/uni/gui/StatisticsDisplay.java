package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

public class StatisticsDisplay extends JPanel implements Observer {
	
	private BankSimulator bs;
	private GridLayout layout;
	
	/**
	 * Constructor for this gui
	 */
	public StatisticsDisplay(BankSimulator bs){
		super();
		this.bs = bs;
		this.setBounds(50, 50, 400, 400);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		layout = new GridLayout(0,2);
		this.setLayout(layout);
		
		this.add(new JLabel("Customers Served:"));
		this.add(new JLabel(""+Statistics.CUSTOMERS_SERVED));
		this.add(new JLabel("Total Transactions:"));
		this.add(new JLabel(""+Statistics.TRANSACTION_TOTAL));
		this.add(new JLabel("Accounts Opened:"));
		this.add(new JLabel(""+Statistics.ACCOUNTS_OPENED));
		this.add(new JLabel("Accounts Closed:"));
		this.add(new JLabel(""+Statistics.ACCOUNTS_CLOSED));
		this.add(new JLabel("Account Deposits:"));
		this.add(new JLabel(""+Statistics.ACCOUNT_DEPOSIT));
		this.add(new JLabel("Total:"));
		this.add(new JLabel(""+Statistics.toPoundsAndPence(Statistics.TOTALS_DEPOSTIT)));
		this.add(new JLabel("Account Withdraw:"));
		this.add(new JLabel(""+Statistics.ACCOUNT_WITHDRAW));
		this.add(new JLabel("Total:"));
		this.add(new JLabel(""+Statistics.toPoundsAndPence(Statistics.TOTALS_WITHDRAW)));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
