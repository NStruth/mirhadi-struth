package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;

public class TellerContainer extends JPanel{

	private TellerList tList;
	private JPanel hPanel;
	
	/**
	 * Constructor for this teller gui
	 * 
	 * @param tList the list of tellers
	 */
	public TellerContainer(TellerList tList){
		
		
		//this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		
		this.setLayout(new BorderLayout());
		
		//use a grid layout panel to hold the tellers
		GridLayout gLayout = new GridLayout(1,3);
		hPanel = new JPanel();
		hPanel.setLayout(gLayout);
		this.tList = tList;
		for(Teller t: tList){
			hPanel.add(new TellerGui(t));
		}
		
		//create a label
		JLabel tLable = new JLabel("Tellers");
		tLable.setFont(GuiMain.HEADER_FONT);
		
		//add the components
		this.add(tLable, BorderLayout.NORTH);
		this.add(hPanel, BorderLayout.CENTER);
		
		//set the sizes
		this.setMinimumSize(new Dimension(900,200));
		this.setPreferredSize(new Dimension(900,200));
		hPanel.setPreferredSize(new Dimension(900,150));

	}
}
