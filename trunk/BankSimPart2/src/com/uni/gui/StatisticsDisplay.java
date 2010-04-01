package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;
import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

public class StatisticsDisplay extends JPanel implements Observation.Observer, ActionListener {
	
	private BankSimulator bs;
	private GridLayout layout;
	private TellerList tl;
	
	private JLabel custServedHead = new JLabel("Customers Served:");
	private JLabel custServedVal = new JLabel(""+Statistics.CUSTOMERS_SERVED);
	private JLabel totalTransHead = new JLabel("Total Transactions:");
	private JLabel totalTransVal = new JLabel(""+Statistics.TRANSACTION_TOTAL);
	private JLabel accOpenHead = new JLabel("Accounts Opened:");
	private JLabel accOpenVal = new JLabel(""+Statistics.ACCOUNTS_OPENED);
	private JLabel accClosedHead = new JLabel("Accounts Closed:");
	private JLabel accClosedVal = new JLabel(""+Statistics.ACCOUNTS_CLOSED);
	private JLabel accDepositHead = new JLabel("Account Deposits:");
	private JLabel accDepositVal = new JLabel(""+Statistics.ACCOUNT_DEPOSIT);
	private JLabel totalHead1 = new JLabel("Total: ");
	private JLabel totalHead2 = new JLabel("Total: ");
	private JLabel totalAccDepositVal = new JLabel(""+Statistics.toPoundsAndPence(Statistics.TOTALS_DEPOSTIT));
	private JLabel totalAccountWithdrawHead = new JLabel("Account Withdraw: ");
	private JLabel totalAccountWithdrawVal = new JLabel(""+Statistics.ACCOUNT_WITHDRAW);
	private JLabel totalWithdrawAmount = new JLabel(""+Statistics.toPoundsAndPence(Statistics.TOTALS_WITHDRAW));
	
	/**
	 * Constructor for this gui
	 */
	public StatisticsDisplay(BankSimulator bs){
		super();
		this.bs = bs;
		this.tl = bs.getTellerList();
		this.setBounds(50, 50, 400, 400);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		for(Teller t: tl)
		{
			t.registerObserver(this);
		}
		initComponents();
	}

	private void initComponents() {
		
		JButton logButton = new JButton("View Log");
		logButton.addActionListener(this);
		
		// TODO Auto-generated method stub
		layout = new GridLayout(0,2);
		this.setLayout(layout);
		this.add(custServedHead);
		this.add(custServedVal);
		this.add(totalTransHead);
		this.add(totalTransVal);
		this.add(accOpenHead);
		this.add(accOpenVal);
		this.add(accClosedHead);
		this.add(accClosedVal);
		this.add(accDepositHead);
		this.add(accDepositVal);
		this.add(totalHead1);
		this.add(totalAccDepositVal);
		this.add(totalAccountWithdrawHead);
		this.add(totalAccountWithdrawVal);
		this.add(totalHead2);
		this.add(totalWithdrawAmount);
		this.add(logButton);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		custServedVal.setText(""+Statistics.CUSTOMERS_SERVED);
		totalTransVal.setText(""+Statistics.TRANSACTION_TOTAL);
		accOpenVal.setText(""+Statistics.ACCOUNTS_OPENED);
		accClosedVal.setText(""+Statistics.ACCOUNTS_CLOSED);
		accDepositVal.setText(""+Statistics.ACCOUNT_DEPOSIT);
		totalAccDepositVal.setText(""+Statistics.toPoundsAndPence(Statistics.TOTALS_DEPOSTIT));
		totalAccountWithdrawVal.setText(""+Statistics.ACCOUNT_WITHDRAW);
		totalWithdrawAmount.setText(""+Statistics.toPoundsAndPence(Statistics.TOTALS_WITHDRAW));
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		LogViewer l = new LogViewer();
	}
	

}
