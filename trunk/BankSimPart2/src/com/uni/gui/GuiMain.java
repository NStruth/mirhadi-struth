package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.BankSimulator;

public class GuiMain extends JFrame{
	
	private JPanel holderPanel;
	private JPanel parent;
	BankSimulator bs;
	private GridBagConstraints c;
	
	
	public GuiMain(BankSimulator bs){
		super();

		//parent.add(BorderLayout.CENTER, new JLabel("test"));
		
		this.bs = bs;
		//this.add(new JLabel("Bank Simulator"), BorderLayout.NORTH);
		
		holderPanel = new JPanel();
		//holderPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		GridBagLayout gbl = new GridBagLayout();
		c = new GridBagConstraints();
		

		holderPanel.setLayout(gbl);
		
		this.setSize(new Dimension(1150,500));
		//this.setResizable(false);
		this.setMaximumSize(new Dimension(1150,500));
		this.setMinimumSize(new Dimension(1150,500));
		this.setVisible(true);
		this.setLayout(gbl);
		initComponents();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	private void initComponents(){
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		

		
	    this.getContentPane().setLayout(new GridBagLayout());
	    c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(2,2,2,2);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.weighty = 0.2;
		c.gridwidth = 2;

		this.getContentPane().add(tCont, c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx=0.1;
		c.gridwidth = 1;
		this.getContentPane().add(new CustomerCallingGui(bs),c);
		
		
	
		//holderPanel.add(tCont, c);
		
		ClockDisplay clock = new ClockDisplay(bs.getTimer());
		c.gridx = 3;
		
		//c.gridy++;
		c.weightx = 0.1;
		c.gridwidth = 1;
		this.getContentPane().add(clock,c);
		
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.weighty = 0.8;
		this.getContentPane().add(qList,c);		
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.4;
		c.weighty = 0.8;
		UserPanel tPanel = new UserPanel(bs);
		this.getContentPane().add(tPanel,c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.4;
		//TODO Fix this
		this.getContentPane().add(new StatisticsDisplay(bs),c);
		
		
		
	}
}
