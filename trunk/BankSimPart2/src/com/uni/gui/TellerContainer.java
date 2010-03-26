package com.uni.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;

public class TellerContainer extends JPanel{

	private TellerList tList;
	
	
	public TellerContainer(TellerList tList){
		this.setLayout(new GridLayout(1,3));
		this.tList = tList;
		for(Teller t: tList){
			this.add(new TellerGui(t));
		}
	}
	


}
