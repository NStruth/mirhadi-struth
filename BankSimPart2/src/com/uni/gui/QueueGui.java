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
	
	private JScrollPane jScroll;
	
	
	public QueueGui(Generator g, CustomerQueue cq){
		this.setLayout(new BorderLayout());
		this.setSize(800,400);
		
		this.cq = cq;
		
		g.registerObserver(this);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		String[] testList = {"Value 1","value2"};
		
		jList = new JList(testList);
		jList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		jScroll = new JScrollPane(jList);
		this.add(new JLabel("Queue"), BorderLayout.NORTH);
		this.add(jScroll, BorderLayout.CENTER);
	}

	@Override
	public void update() {
		System.out.println("UPDATING");
		jList.setListData(cq.toArray());
		
	}
	


}
