package com.uni.gui;

import java.awt.BorderLayout;
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
		
		this.setSize(900,500);
		this.setVisible(true);

		this.setLayout(gbl);
		initComponents();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	private void initComponents(){
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		

		
	    this.getContentPane().setLayout(new GridBagLayout());
	    c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(2,2,2,2);

		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.6;
		c.weighty = 0.2;
		this.getContentPane().add(tCont, c);
		
		
		//holderPanel.add(tCont, c);
		
		ClockDisplay clock = new ClockDisplay(bs.getTimer());
		c.gridx = 2;
		//c.gridy++;
		c.weightx = 0.0;
		this.getContentPane().add(clock,c);
		
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.2;
		c.weighty = 0.8;
		this.getContentPane().add(qList,c);		
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.6;
		c.weighty = 0.8;
		ToolsPanel tPanel = new ToolsPanel();
		this.getContentPane().add(tPanel,c);
		
		
	}
}
