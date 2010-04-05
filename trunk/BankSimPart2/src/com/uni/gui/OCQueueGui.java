/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * This is the gui panel to display
 * the special queue of customers who are only
 * opening or closing an account. 
 * Extends QueueGui.
 * 
 */
package com.uni.gui;

import java.awt.Color;

import javax.swing.BorderFactory;

import com.uni.main.Generator;
import com.uni.queue.CustomerQueue;

public class OCQueueGui extends QueueGui {
	
	private Color borderColor = Color.RED;
	private String headerText = "Open or Close";
	
	/**
	 * 
	 * The constructor for this panel
	 * 
	 * @param g
	 * @param cq
	 */
	public OCQueueGui(Generator g, CustomerQueue cq)
	{
		super(g, cq);
		this.header.setText(headerText);
		this.setBorder(BorderFactory.createLineBorder(borderColor));
	}
	
}
