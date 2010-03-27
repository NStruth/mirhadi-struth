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
	
	public TellerContainer(TellerList tList){
		
		//this.add(new JLabel("Tellers"), BorderLayout.PAGE_START);
		
		this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		
		this.setLayout(new BorderLayout());
		GridLayout gLayout = new GridLayout(1,3);
		hPanel = new JPanel();
		hPanel.setLayout(gLayout);
		this.tList = tList;
		for(Teller t: tList){
			hPanel.add(new TellerGui(t));
		}
		this.add(new JLabel("Tellers"), BorderLayout.NORTH);
		this.add(hPanel, BorderLayout.CENTER);
		/*this.setMinimumSize(new Dimension(800,400));
		this.setMaximumSize(new Dimension(800,400));*/
		this.setPreferredSize(new Dimension(750,200));
	}
}
