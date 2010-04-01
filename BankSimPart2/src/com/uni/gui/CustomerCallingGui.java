package com.uni.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Observation.Observer;

import com.uni.Teller.Teller;
import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

public class CustomerCallingGui extends JPanel implements Observer {

	private JLabel currLabel;
	private JLabel nextLabel;
	
	private JLabel currCust;
	private JLabel nextCust;
	private BankSimulator bs;
	
	public CustomerCallingGui(BankSimulator bs){
		super();
		this.bs = bs;
		bs.getQueue().registerObserver(this);
		bs.getOCQueue().registerObserver(this);
		
		for(Teller t: bs.tellerList)
			t.registerObserver(this);
		
		//bs.getGenerator().registerObserver(this);
		initComponents();
	}
	
	public void initComponents(){
		
		this.setPreferredSize(new Dimension(150,250));
		this.setMaximumSize(new Dimension(150,250));
		

		
		currLabel = new JLabel("Now:");
		nextLabel = new JLabel("Next:");
		currCust =  new JLabel(" --- ");
		nextCust =  new JLabel(" --- ");
		Font f = currLabel.getFont();
		f = f.deriveFont(new Float(16));
		
		currLabel.setFont(f);
		nextLabel.setFont(f);
		
		Font plainF = currLabel.getFont();
		plainF = plainF.deriveFont(Font.PLAIN);
		
		currCust.setFont(plainF);
		nextCust.setFont(plainF);
		
		this.setLayout(new GridLayout(0,2));
		this.add(currLabel);
		this.add(currCust);
		
		this.add(nextLabel);
		this.add(nextCust);
	}

	@Override
	public void update() {
		System.out.println("Update");
		currCust.setText(Statistics.last_customer + "");
		 int next = bs.getNext();
		 if(next != -1)
		 nextCust.setText(next+"");
		 else
		 nextCust.setText(" --- ");
	}
	
}
