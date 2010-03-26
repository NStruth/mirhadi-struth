package com.uni.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Observation.Observer;

import com.uni.Teller.Teller;

public class TellerGui extends JPanel implements Observer{
 
	private Teller teller;
	JTextArea tempText = new JTextArea(10,30);
	
	private JLabel status = new JLabel("OPEN");
	private JLabel serving = new JLabel(" Serving: XXX");
	
	private JLabel custLabel = new JLabel("Customer");
	private JLabel custName = new JLabel(" --- ");
	
	
	
	public TellerGui(Teller t){
		this.setBounds(0,0 , 250, 200);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	
		this.teller = t;
				
		t.registerObserver(this);
			
		initComponents();
	}

	private void initComponents(){
		if(teller.getOpen()){
			status.setText("Open");
			
			Color open = new Color(20,200,20);
			status.setForeground(open);
		}else{
			status.setText("Closed");
			status.setForeground(Color.RED);
		}
		status.setBounds(this.getWidth() - 40 ,0, 40, 20);
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(status);
		
		//
		this.add(serving);
		serving.setBounds(0,0,this.getWidth() - 40, 20);
		
		this.add(custLabel);
		custLabel.setBounds(0, 30, 75, 20);
		
		this.add(custName);
		custName.setBounds(80, 30, this.getWidth() - 80, 20);
		
	}

	

	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		custName.setText(teller.getCustomerName());
		serving.setText("Serving: " + teller.getCustNumber());
		
	}
	
	
	
}
