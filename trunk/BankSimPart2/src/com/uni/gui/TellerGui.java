package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JLabel serving = new JLabel(" XXX");
	
	private JLabel custLabel = new JLabel("Customer:");
	private JLabel custName = new JLabel(" --- ");
	
	private JLabel typeLabel = new JLabel("Type:");
	private JLabel typeText = new JLabel(" --- ");
	
	private JLabel statusLabel = new JLabel("Status:");
	private JLabel statusText = new JLabel(" --- ");
	
	
	private GridBagLayout gbl;
	private GridBagConstraints c;
	private JButton open;
	private JButton close;
	
	
	public TellerGui(Teller t){
		
		this.setLayout(new BorderLayout());
		
		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(gbl);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.teller = t;
		//this.add(hPanel, BorderLayout.NORTH);		
		//this.add(new JLabel("Test"), BorderLayout.CENTER);
		
		t.registerObserver(this);
		this.setPreferredSize(new Dimension(250,150));
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
		//c.insets = new Insets(2,2,2,2);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1.0;
		c.gridwidth = 1;
		
		Font f = this.getFont();
		FontMetrics fM = this.getFontMetrics(f);
		
		c.gridwidth=1;
		c.gridx = 2;
		c.weightx = 0.1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.add(status,c);
		
		c.ipadx = 0;
		c.gridy++;
		c.gridx=0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.1;
		c.gridwidth = 1;
		this.add(custLabel,c);
		
		c.ipady = 0;
		c.gridx = 1;
		c.weightx = 0.8;
		c.gridwidth = 2;

		this.add(custName,c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 1;
		this.add(typeLabel,c);
		c.gridx = 1;
		c.gridwidth = 2;

		this.add(typeText,c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 1;

		this.add(statusLabel,c);
		c.gridx = 1;
		c.gridwidth = 2;

		this.add(statusText,c);
		
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		open = new JButton("Open");
		close = new JButton("close");
		
		open.addActionListener(this);
		close.addActionListener(this);
		
		
		
		buttonPanel.add(open,BorderLayout.NORTH);
		buttonPanel.add(close,BorderLayout.SOUTH);
		buttonPanel.setPreferredSize(new Dimension(100, 50));
		
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy++;
		
		this.add(buttonPanel, c);
		
		
	}

	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Open")){
			teller.setOpen(true);
			close.setEnabled(true);
			open.setEnabled(false);
		}else{
			teller.setOpen(false);
			close.setEnabled(false);
			open.setEnabled(true);
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
			serving.setText(teller.getCustNumber() + "");
			
			typeText.setText(teller.getTranType());
			statusText.setText(teller.getMessage());
			
		}else{
			status.setText("Closed");
			status.setForeground(Color.RED);
		}
	}
	
	
	
}
