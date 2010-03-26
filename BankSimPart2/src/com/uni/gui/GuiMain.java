package com.uni.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.main.BankSimulator;

public class GuiMain extends JFrame{
	
	private JPanel holderPanel;
	BankSimulator bs;
	
	public GuiMain(BankSimulator bs){
		super();
		this.bs = bs;
		holderPanel = new JPanel();
		holderPanel.setLayout(null);
		
		this.setBounds(50, 50, 1000, 600);
		this.setVisible(true);
		
		this.add(holderPanel);
		initComponents();
		
		
	}
	
	
	private void initComponents(){
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		tCont.setBounds(this.getWidth() - 750, 0, 750, 100);
		holderPanel.add(tCont);
	}
}
