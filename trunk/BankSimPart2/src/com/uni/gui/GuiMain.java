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
		this.getContentPane().add(new JLabel("Hello"), BorderLayout.NORTH);
		this.getContentPane().add(new JLabel("Goodbye"), BorderLayout.CENTER);
		this.setSize(600,600);
		this.setVisible(true);
		/*parent = new JPanel(new BorderLayout());
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
		initComponents();*/
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	private void initComponents(){
		/*TellerContainer tCont = new TellerContainer(bs.tellerList);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;

		c.insets = new Insets(2,2,2,2);
		
		holderPanel.add(tCont, c);
		
		ClockDisplay clock = new ClockDisplay(bs.getTimer());
		c.gridx = 3;
		//c.gridy++;
		c.weightx = 0.0;
		//holderPanel.add(clock,c);
		
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		//c.ipadx = 150;
		//c.ipady = 300;
		//holderPanel.add(qList,c);		
		
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		ToolsPanel tPanel = new ToolsPanel();
	    holderPanel.add(tPanel,c);*/

		

		
		
	}
}
