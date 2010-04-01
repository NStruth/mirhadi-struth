/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Displays a CustomerQueue as a list which is updated
 * as and when people enter/exit the queue
 * 
 */

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
	
	
	/**
	 * Constructor for this queue list panel
	 * 
	 * @param g the generator
	 * @param cq the customer queue
	 */
	public QueueGui(Generator g, CustomerQueue cq){
		//layout stuff
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(150,200));
		//set reference to queue
		this.cq = cq;
		//set up observers
		cq.registerObserver(this);
		g.registerObserver(this);
		
		//give it a border
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//initialise with default data
		String[] testList = {"Please","start","the","simualtion"};
		//create the list
		jList = new JList(testList);
		jList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList.setMinimumSize(new Dimension(150,200));
		jList.setMaximumSize(new Dimension(150,200));
		//store the list in a scrollable panel
		jScroll = new JScrollPane(jList);
		jScroll.setMinimumSize(new Dimension(150,200));
		jScroll.setMaximumSize(new Dimension(150,200));
		//add the components
		this.add(header, BorderLayout.NORTH);
		this.add(jScroll, BorderLayout.CENTER);
	}

	@Override
	public void update() {
		jList.setListData(cq.toArray());
	}
}