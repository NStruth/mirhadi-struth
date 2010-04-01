package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
	protected JLabel header = new JLabel("Queue");
	
	private JScrollPane jScroll;
	
	
	public QueueGui(Generator g, CustomerQueue cq){
		this.setLayout(new BorderLayout());
		//this.setSize(800,400);
		//this.setBounds(new Rectangle(0, 0, 800, 400));
		//this.setMinimumSize(new Dimension(150,200));
		//this.setMaximumSize(new Dimension(150,200));
		
		this.setPreferredSize(new Dimension(150,200));
		
		this.cq = cq;
		
		cq.registerObserver(this);
		g.registerObserver(this);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		String[] testList = {"Value 1","value2"};
		
		jList = new JList(testList);
		jList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList.setMinimumSize(new Dimension(150,200));
		jList.setMaximumSize(new Dimension(150,200));
		
		jScroll = new JScrollPane(jList);
		jScroll.setMinimumSize(new Dimension(150,200));
		jScroll.setMaximumSize(new Dimension(150,200));
		this.add(header, BorderLayout.NORTH);
		this.add(jScroll, BorderLayout.CENTER);
	}

	@Override
	public void update() {
		jList.setListData(cq.toArray());
		
	}
	


}
