/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * This is the main window for this BankSimulator which extends JFrame.
 * Stores all the GUI components for this application.
 * 
 */
package com.uni.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import com.uni.main.BankSimulator;

public class GuiMain extends JFrame{
	
	BankSimulator bs; //the bank simulator
	private GridBagConstraints c; //constraints for layout
	
	//a header font all panels can use
	public static Font HEADER_FONT;
	
	/**
	 * 
	 * Constructor for this gui
	 * @param bs the bank simulator
	 */
	public GuiMain(BankSimulator bs){
		super();
		//set reference
		this.bs = bs;
		//initialise layout tools
		GridBagLayout gbl = new GridBagLayout();
		c = new GridBagConstraints();
		//set the sizes
		this.setSize(new Dimension(1150,500));
		this.setMaximumSize(new Dimension(1150,500));
		this.setMinimumSize(new Dimension(1150,500));
		this.setVisible(true);
		this.setLayout(gbl);
		//initialise components
		initComponents();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * This removes all components and recreates
	 * them
	 */
	public void resetComponents(){
		this.getContentPane().removeAll();
		this.repaint();
		this.initComponents();
	}
	
	/**
	 * Initialse the components used in this display
	 */
	private void initComponents(){
	
		//set up the header font
		HEADER_FONT = this.getFont();
		HEADER_FONT = HEADER_FONT.deriveFont(new Float(20));
		
		this.getContentPane().setLayout(new GridBagLayout());
		
		/* Add the tellers */
		TellerContainer tCont = new TellerContainer(bs.tellerList);
	    //set the constraints
	    c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(2,2,2,2);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.weighty = 0.2;
		c.gridwidth = 2;
		this.getContentPane().add(tCont, c);
		
		
		/*Add Now/Next customer panel */
		c.gridx = 0;
		c.gridy = 0;
		c.weightx=0.1;
		c.gridwidth = 1;
		this.getContentPane().add(new CustomerCallingGui(bs),c);

		/* Clock display*/
		ClockDisplay clock = new ClockDisplay(bs.getTimer(), bs.getQueue(), bs.getOCQueue());
		c.gridx = 3;
		c.weightx = 0.1;
		c.gridwidth = 1;
		this.getContentPane().add(clock,c);
		
		/* Queue List */
		QueueGui qList = new QueueGui(bs.getGenerator(),bs.getQueue());
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.weighty = 0.8;
		this.getContentPane().add(qList,c);	
		
		/* the tools panel */
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.4;
		c.weighty = 0.8;
		UserPanel tPanel = new UserPanel(bs);
		this.getContentPane().add(tPanel,c);
		
		/*the statistics panel */
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.4;
		this.getContentPane().add(new StatisticsDisplay(bs),c);
		
		/* the special queue for opening/closing accounts */
		OCQueueGui ocqList = new OCQueueGui(bs.getGenerator(),bs.getOCQueue());
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 0.1;
		c.weighty = 0.8;
		this.getContentPane().add(ocqList,c);
		this.pack();
	}
}
