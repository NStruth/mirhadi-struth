package com.uni.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.main.BankSimulator;

public class GuiMain extends JFrame{
	
	private JPanel holderPanel;
	BankSimulator bs;
	private GridBagConstraints c;
	
	public GuiMain(BankSimulator bs){
		super();
		
		this.bs = bs;
		//this.add(new JLabel("Bank Simulator"), BorderLayout.NORTH);
		
		holderPanel = new JPanel();
		//holderPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		GridBagLayout gbl = new GridBagLayout();
		c = new GridBagConstraints();
		

		holderPanel.setLayout(gbl);
		
		this.setSize(900,500);
		this.setVisible(true);

		this.setLayout(gbl);
		this.getContentPane().add(holderPanel);
		initComponents();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	private void initComponents(){
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		c.anchor = c.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(2,2,2,2);
		
		holderPanel.add(tCont, c);
		
		ClockDisplay clock = new ClockDisplay(bs.getTimer());
		c.gridx = 3;
		//c.gridy++;
		c.weightx = 0.0;
		holderPanel.add(clock,c);
		
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy++;
		c.weightx = 1.0;
		c.ipadx = 150;
		c.ipady = 300;
		holderPanel.add(qList,c);		
		

		
		
		
	}
}
