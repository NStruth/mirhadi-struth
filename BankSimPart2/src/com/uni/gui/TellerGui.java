package com.uni.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Observation.Observer;

import com.uni.Teller.Teller;

public class TellerGui extends JPanel implements Observer, ActionListener{
 
	private Teller teller;
	JTextArea tempText = new JTextArea(10,30);
	
	private JLabel status = new JLabel("OPEN");
	private JLabel serving = new JLabel(" Serving: XXX");
	
	private JLabel custLabel = new JLabel("Customer:");
	private JLabel custName = new JLabel(" --- ");
	
	private JLabel typeLabel = new JLabel("Type:");
	private JLabel typeText = new JLabel(" --- ");
	
	
	private GridBagLayout gbl;
	private GridBagConstraints c;
	private JButton open;
	private JButton close;
	
	
	public TellerGui(Teller t){
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(gbl);
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
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		//
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		this.add(serving,c);
		
		c.gridx = 2;
		c.ipadx = 20;
		this.add(status,c);
		
		c.ipady = 20;
		c.gridy++;
		c.gridx=0;
		this.add(custLabel,c);
		
		c.ipady = 0;
		c.gridx = 1;
		this.add(custName,c);
		
		c.gridx = 0;
		c.gridy++;
		this.add(typeLabel,c);
		c.gridx = 1;
		this.add(typeText,c);
		
		
		open = new JButton("Open");
		close = new JButton("close");
		
		open.addActionListener(this);
		close.addActionListener(this);
		
		c.gridx = 0;
		c.gridy++;
		
		this.add(open,c);
		c.gridx++;
		this.add(close,c);
		
		
		
		
	}

	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Open")){
			teller.setOpen(true);
		}else{
			teller.setOpen(false);
		}
		update();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(teller.getOpen()){
			status.setText("Open");
			
			Color open = new Color(20,200,20);
			status.setForeground(open);
			
			custName.setText(teller.getCustomerName());
			serving.setText("Serving: " + teller.getCustNumber());
			typeText.setText(teller.getTranType());
		}else{
			status.setText("Closed");
			status.setForeground(Color.RED);
		}
	}
	
	
	
}
