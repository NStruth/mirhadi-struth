package com.uni.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Observation.Observer;

import com.uni.main.BankSimulator;

public class CustomerCallingGui extends JPanel implements Observer {

	private JLabel currLabel;
	private JLabel nextLabel;
	
	private JLabel currCust;
	private JLabel nextCust;
	private BankSimulator bs;
	
	public CustomerCallingGui(BankSimulator bs){
		super();
		this.bs = bs;
		bs.getQueue().registerObserver(this);
		initComponents();
	}
	
	public void initComponents(){
		
		this.setPreferredSize(new Dimension(150,250));
		this.setMaximumSize(new Dimension(150,250));
		

		
		currLabel = new JLabel("Serving:");
		nextLabel = new JLabel("Next:");
		currCust =  new JLabel(" --- ");
		nextCust =  new JLabel(" --- ");
		Font f = currLabel.getFont();
		f = f.deriveFont(new Float(20));
		
		currLabel.setFont(f);
		nextLabel.setFont(f);
		
		Font plainF = currLabel.getFont();
		plainF = plainF.deriveFont(Font.PLAIN);
		
		currCust.setFont(plainF);
		nextCust.setFont(plainF);
		
		this.setLayout(new GridLayout(0,2));
		this.add(currLabel);
		this.add(currCust);
		
		this.add(nextLabel);
		this.add(nextCust);
	}

	@Override
	public void update() {
		System.out.println("Update");
		currCust.setText(bs.getQueue().getNextNumber() + "");
		nextCust.setText((bs.getQueue().getNextNumber() + 1) + "");	
	}
	
}
