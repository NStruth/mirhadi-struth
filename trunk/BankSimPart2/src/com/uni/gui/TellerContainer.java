package com.uni.gui;

import javax.swing.JPanel;

import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;

public class TellerContainer extends JPanel{

	private TellerList tList;
	
	public TellerContainer(TellerList tList){
		this.tList = tList;
		for(Teller t: tList){
			this.add(new TellerGui(t));
		}
	}
	


}
