/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * A class which extends JPanel to inform the current customer that was 
 * called to be served by a teller, and the next customer.
 * 
 * Simulates a ticket system in a shop.
 * 
 */
package com.uni.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Observation.Observer;

import com.uni.Teller.Teller;
import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

public class CustomerCallingGui extends JPanel implements Observer {

	//labels to store values
	private JLabel currLabel;
	private JLabel nextLabel;
	
	private JLabel currCust;
	private JLabel nextCust;
	private BankSimulator bs;
	
	/**
	 * Constructo for this gui panel
	 * @param bs the bank simulator
	 */
	public CustomerCallingGui(BankSimulator bs){
		super();
		this.bs = bs;
		//register observers
		//can update from queue change or teller activity
		bs.getQueue().registerObserver(this);
		bs.getOCQueue().registerObserver(this);
		
		for(Teller t: bs.tellerList)
			t.registerObserver(this);
		
		initComponents();
	}
	
	public void initComponents(){
		
		this.setPreferredSize(new Dimension(150,250));
		this.setMaximumSize(new Dimension(150,250));
		
		//initialise labels
		currLabel = new JLabel("Now:");
		nextLabel = new JLabel("Next:");
		currCust =  new JLabel(" --- ");
		nextCust =  new JLabel(" --- ");
		
		//bold font
		Font f = currLabel.getFont();
		f = f.deriveFont(new Float(16));
		//apply the font
		currLabel.setFont(f);
		nextLabel.setFont(f);
		//plain font
		Font plainF = currLabel.getFont();
		plainF = plainF.deriveFont(Font.PLAIN);
		//apply the font
		currCust.setFont(plainF);
		nextCust.setFont(plainF);
		//grid layout to store labels
		this.setLayout(new GridLayout(0,2));
		this.add(currLabel);
		this.add(currCust);
		
		this.add(nextLabel);
		this.add(nextCust);
	}

	/* (non-Javadoc)
	 * @see Observation.Observer#update()
	 */
	@Override
	public void update() {
		currCust.setText(Statistics.last_customer + "");
		int next = bs.getNext();
		//if -1 was returned queues are empty
		if(next != -1)
			nextCust.setText(next+"");
		else
			nextCust.setText(" --- ");
	}	
}
