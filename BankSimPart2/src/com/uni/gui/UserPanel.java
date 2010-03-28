package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.BankSimulator;

public class UserPanel extends JPanel{

	private BankSimulator bs;
	private JButton closeButton;
	private JButton disableClose;
	private JPanel p;

	public UserPanel(BankSimulator bs){
		super();
		initComponents();
		this.bs = bs;
	}
	
	public void initComponents(){
		this.setLayout(new BorderLayout());
		
		Font f = this.getFont();
		f = f.deriveFont(new Float(20));
		JLabel title = new JLabel("User Panel");
		title.setFont(f);
		this.add(title, BorderLayout.NORTH);
		
		this.add(new ToolsPanel(bs), BorderLayout.CENTER);
		
	}



}
