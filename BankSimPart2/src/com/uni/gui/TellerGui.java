package com.uni.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Observation.Observer;

import com.uni.Teller.Teller;

public class TellerGui extends JPanel implements Observer{
 
	private Teller teller;
	JTextArea tempText = new JTextArea(20,5);
	
	public TellerGui(Teller t){
		this.teller = t;
		tempText.setEditable(false);
		this.add(tempText);
		t.registerObserver(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		tempText.setText(teller.getMessage());
	}
	
	
}
