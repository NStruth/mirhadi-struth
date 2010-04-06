/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Extends JPanel to display a single teller
 * and their current activity.
 * Provides options to open/close the teller.
 * 
 *   
 */
package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	
	/*Some initial values */
	private JLabel status = new JLabel("OPEN");
	private JLabel serving = new JLabel(" XXX");
	
	//the current customer
	private JLabel custLabel = new JLabel("Customer:");
	private JLabel custName = new JLabel(" --- ");
	
	//the transaction type
	private JLabel typeLabel = new JLabel("Type:");
	private JLabel typeText = new JLabel(" --- ");
	
	//the transaction status
	private JLabel statusLabel = new JLabel("Status:");
	private JLabel statusText = new JLabel(" --- ");

	private GridBagLayout gbl; //layout
	private GridBagConstraints c; //constraints
	private JButton open; //open button
	private JButton close; //close button
	
	/**
	 * Constructor for this TellerGui
	 * 
	 * @param t the teller for which this gui is associated
	 */
	public TellerGui(Teller t){
		//use a grid bag layout		
		gbl = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(gbl);
		//distinguish section with a border
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		//add association with a teller
		this.teller = t;
		//register the observer
		t.registerObserver(this);
		this.setPreferredSize(new Dimension(300,150));
		initComponents();
	}

	/**
	 * Initialise the components on this panel
	 */
	private void initComponents(){
		//teller open status
		if(teller.getOpen()){
			status.setText("Open");
			
			Color open = new Color(20,200,20);
			status.setForeground(open);
		}else{
			status.setText("Closed");
			status.setForeground(Color.RED);
		}
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//add label for serving
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1.0;
		c.gridwidth = 2;
		this.add(serving,c);
				
		/* Add status label */
		c.gridwidth=1;
		c.gridx = 2;
		c.weightx = 0.1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.add(status,c);
		
		/* Add customer label */
		c.gridy++;
		c.gridx=0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.weightx = 0.2;
		custLabel.setMinimumSize(new Dimension(100,10));
		this.add(custLabel,c);
		
		/* Add customer name */
		c.gridx = 1;
		c.weightx = 0.8;
		c.gridwidth = 2;
		custName.setMinimumSize(new Dimension(200,10));
		this.add(custName,c);
		
		/* Add transaction type label */
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 1;
		c.weightx =0.2;
		typeLabel.setMinimumSize(new Dimension(100,10));
		this.add(typeLabel,c);
		
		/* Add transaction type */
		c.gridx = 1;
		c.gridwidth = 2;
		c.weightx = 0.8;
		typeText.setMinimumSize(new Dimension(200,10));
		this.add(typeText,c);
		
		/* Add transaction status label */
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 1;
		c.weightx = 0.2;
		statusLabel.setMinimumSize(new Dimension(100,10));
		this.add(statusLabel,c);
		
		/* Add transaction status text */
		c.gridx = 1;
		c.gridwidth = 2;
		c.weightx = 0.8;
		statusText.setMinimumSize(new Dimension(200,10));
		this.add(statusText,c);
		
		/* Panel for open/close buttons */
		JPanel buttonPanel = new JPanel(new BorderLayout());
		open = new JButton("Open");
		close = new JButton("close");
		
		/* set listener */
		open.addActionListener(this);
		close.addActionListener(this);
		
		/*disable open button */
		open.setEnabled(false);
		
		/*add buttons*/
		buttonPanel.add(open,BorderLayout.NORTH);
		buttonPanel.add(close,BorderLayout.SOUTH);
		buttonPanel.setPreferredSize(new Dimension(300, 50));
		close.setMinimumSize(new Dimension(300,20));
		
		/* add the button panel */
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy++;
		c.weightx = 1;
		this.add(buttonPanel, c);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Open")){
			//open teller and change buttons
			teller.setOpen(true);
			close.setEnabled(true);
			open.setEnabled(false);
		}else{
			//close teller and change buttons
			teller.setOpen(false);
			close.setEnabled(false);
			open.setEnabled(true);
		}
		//update display
		update();
		
	}

	@Override
	public void update() {
		/* If the teller is open update all values */
		if(teller.getOpen()){
			status.setText("Open");
			//different colour when open
			Color open = new Color(20,200,20);
			status.setForeground(open);
			
			custName.setText(teller.getCustomerName());
			serving.setText(teller.getCustNumber() + "");
			
			typeText.setText(teller.getTranType());
			statusText.setText(teller.getMessage());

		}else if(teller.serving){
			/*Otherwise if they are closed but still serving
			 * Then the transaction must finish first so 
			 * update all but change status
			 */
			status.setText("Closing");
			status.setForeground(Color.ORANGE);
			//customer
			custName.setText(teller.getCustomerName());
			serving.setText(teller.getCustNumber() + "");
			//type and status
			typeText.setText(teller.getTranType());
			statusText.setText(teller.getMessage());
		}else{
			//otherwise closed
			status.setText("Closed");
			status.setForeground(Color.RED);
		}
	}
}
