package com.uni.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.uni.main.Generator;
import com.uni.queue.CustomerQueue;

public class OCQueueGui extends QueueGui {
	public OCQueueGui(Generator g, CustomerQueue cq)
	{
		super(g, cq);
		this.header = new JLabel("Open and Close");
		this.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	

}
