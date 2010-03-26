package com.uni.gui;

import javax.swing.JFrame;

import com.uni.main.BankSimulator;

public class GuiMain extends JFrame{

	public GuiMain(BankSimulator bs){
		super();
		
		this.setBounds(50, 50, 800, 600);
		this.setVisible(true);

		//TellerGui gui = new TellerGui(bs.teller);
		TellerContainer tCont = new TellerContainer(bs.tellerList);
		this.add(tCont);
		
		//initComponents();
		
		
	}
	
}
