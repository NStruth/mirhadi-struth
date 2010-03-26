package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Observation.Observer;

import com.uni.main.Generator;
import com.uni.queue.CustomerQueue;

public class QueueGui extends JPanel implements Observer{

	
	private JList jList;
	private CustomerQueue cq;
	
	public QueueGui(Generator g, CustomerQueue cq){
		this.cq = cq;
		g.registerObserver(this);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList = new JList(cq.toArray());
		jList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(new JLabel("Queue"), BorderLayout.NORTH);
		this.add(new JScrollPane(jList), BorderLayout.CENTER);
	}

	@Override
	public void update() {
		System.out.println("UPDATING");
		jList.setListData(cq.toArray());
		
	}
	


}
