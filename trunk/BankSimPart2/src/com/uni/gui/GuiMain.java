package com.uni.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		GridBagLayout gbl = new GridBagLayout();
		c = new GridBagConstraints();
		

		holderPanel.setLayout(gbl);
		
		this.setSize(900,500);
		this.setVisible(true);

		this.setLayout(gbl);
		this.add(holderPanel);
		initComponents();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	private void initComponents(){
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		c.anchor = c.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		//tCont.setBounds(this.getWidth() - 750, 0, 750, 200);
		holderPanel.add(tCont, c);
		
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy++;
		holderPanel.add(qList,c);
		//qList.setBounds(0,0,200,200);
		
	}
}
