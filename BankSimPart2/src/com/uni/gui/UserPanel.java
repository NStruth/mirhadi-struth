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

	/**
	 * Constructor for the user panel
	 * 
	 * @param bs the bank simulator
	 */
	public UserPanel(BankSimulator bs){
		super();
		//set the size
		this.setMinimumSize(new Dimension(450,400));
		this.setPreferredSize(new Dimension(450,400));

		this.bs = bs;
		this.setLayout(new BorderLayout());
		//heading
		JLabel title = new JLabel("User Panel");
		title.setFont(GuiMain.HEADER_FONT);
		this.add(title, BorderLayout.NORTH);
		//The actual tools are in a class of their own
		ToolsPanel tPanel = new ToolsPanel(this.bs);
		this.add(tPanel, BorderLayout.CENTER);

	}
	



}
