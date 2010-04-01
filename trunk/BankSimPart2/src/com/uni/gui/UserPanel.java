package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.BankSimulator;

public class UserPanel extends JPanel{

	BankSimulator bs;
	private JButton closeButton;
	private JButton disableClose;
	private JPanel p;

	public UserPanel(BankSimulator bs){
		super();
		initComponents();
		this.setMinimumSize(new Dimension(450,400));
		this.setPreferredSize(new Dimension(450,400));
		/*Weird bug - Ask me!*/
		this.bs = bs;
		System.out.println(bs.getTellerList().size());
		this.setLayout(new BorderLayout());
		
		Font f = this.getFont();
		f = f.deriveFont(new Float(20));
		JLabel title = new JLabel("User Panel");
		title.setFont(f);
		this.add(title, BorderLayout.NORTH);
		ToolsPanel tPanel = new ToolsPanel(this.bs);
		this.add(tPanel, BorderLayout.CENTER);

	}
	
	public void initComponents(){
		

	}



}
