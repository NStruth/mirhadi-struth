/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 1.0
 * 
 * The is the main gui used for displaying summary results
 * stored in statistics after a simulation.
 * Contains a button to view log hence implements action listener
 */

package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Observation.Observer;

import com.uni.Teller.Teller;
import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

public class GuiDisplay extends JPanel implements ActionListener, Observer{

	private SpringLayout layout;
	private JPanel jp;
	
	private int north = 35; //y position the labels start
	private int westLabel = 30; //x position labels start
	private int westText = 150; //x position values start
	private int padding = 34; //space between lines
	
	
	private BankSimulator bs;
	
	/**
	 * Constructor for this gui
	 */
	public GuiDisplay(BankSimulator bs){
		super();
		this.setBounds(50, 50, 400, 400);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		initComponents();
	}
	/**
	 * Initialise components on the gui
	 */
	private void initComponents(){
		
		layout = new SpringLayout();
		this.setLayout(layout); //set the layout
		
		
		
		//get the font so we can fix it
		Font f = this.getFont();
		
		//while we have the font set the padding
		FontMetrics fM = this.getFontMetrics(f);
		this.westText = fM.stringWidth("  Accounts Opened:     ");
		
		//biiiig font for heading
		f = f.deriveFont(new Float(20));
		JLabel header = new JLabel("Statistics");
		header.setFont(f);
		//add the header		
		this.add(header);
		
		//add the results
		addPair("Customers Served:",Statistics.CUSTOMERS_SERVED,0);
		addPair("Total Transactions:",Statistics.TRANSACTION_TOTAL , 0);
		addPair("Accounts Opened:",Statistics.ACCOUNTS_OPENED,20);
		addPair("Accounts Closed:",Statistics.ACCOUNTS_CLOSED,20);
		addPair("Account Deposits:",Statistics.ACCOUNT_DEPOSIT,20);
		addPair("Total:", Statistics.toPoundsAndPence(Statistics.TOTALS_DEPOSTIT),40);
		addPair("Account Withdraw:", Statistics.ACCOUNT_WITHDRAW,20);
		addPair("Total:", Statistics.toPoundsAndPence(Statistics.TOTALS_WITHDRAW),40);
		
		//make a button for the log
		JButton logButton = new JButton("Click to view log");
		logButton.addActionListener(this);
		this.add(logButton);
		layout.putConstraint(SpringLayout.WEST, logButton,westLabel,SpringLayout.WEST, this);
	    layout.putConstraint(SpringLayout.NORTH, logButton,north,SpringLayout.NORTH, this);
	    
	    
	   
        
        
	}
	
	/**
	 * Add a label/value pair with an integer value
	 * @param label
	 * @param intMessage
	 * @param hOffset
	 */
	private void addPair(String label, int intMessage, int hOffset){
		String message = intMessage + "";
		addPair(label,message, hOffset);
	}
	
	/**
	 * Add a label/value pair with a string value
	 * Ultimately this method will be called when adding a pair.
	 * Simply places a label and a value on the screen with 
	 * an x coordinate offset
	 * @param label the label
	 * @param message the message
	 * @param hOffset the x coordinate offset
	 */
	private void addPair(String label, String message, int hOffset){
		//create the labels
		JLabel custLabel = new JLabel(label); 
		JLabel label2 = new JLabel(message);
		//get the font so we can change it
		Font f = (this.getFont());
		f = f.deriveFont(Font.PLAIN);
		//change the font for the value
		label2.setFont(f);
		//add the labels
		this.add(custLabel);
        this.add(label2);
		//set the layout constraints for the label
        layout.putConstraint(SpringLayout.WEST, custLabel,westLabel+hOffset,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, custLabel,north,SpringLayout.NORTH, this);
		//set the layout constraints for the value
        layout.putConstraint(SpringLayout.WEST, label2,westText+hOffset,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, label2,north,SpringLayout.NORTH, this);	
        //change north value by padding amount
        north += padding;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//show the log viewer
		LogViewer lv = new LogViewer();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		// TODO update the values
		
	}
	
}
