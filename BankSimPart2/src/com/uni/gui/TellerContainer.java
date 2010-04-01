/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Provides a container to display the tellers.
 * Tellers themselves are controlled by the TellerGui class
 * which are created in this class from a list of tellers
 * provided by the BankSimulator.
 * 
 */
package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.Teller.Teller;
import com.uni.Teller.TellerList;

public class TellerContainer extends JPanel{

	private JPanel hPanel;
	
	/**
	 * Constructor for this teller gui
	 * 
	 * @param tList the list of tellers
	 */
	public TellerContainer(TellerList tList){
		//set the layout		
		this.setLayout(new BorderLayout());
		
		//use a grid layout panel to hold the tellers
		GridLayout gLayout = new GridLayout(1,3);
		hPanel = new JPanel();
		hPanel.setLayout(gLayout);
		
		for(Teller t: tList){
			hPanel.add(new TellerGui(t));
		}
		
		//create a label
		JLabel tLable = new JLabel("Tellers");
		tLable.setFont(GuiMain.HEADER_FONT);
		
		//add the components
		this.add(tLable, BorderLayout.NORTH);
		this.add(hPanel, BorderLayout.CENTER);
		
		//set the sizes
		this.setMinimumSize(new Dimension(900,200));
		this.setPreferredSize(new Dimension(900,200));
		hPanel.setPreferredSize(new Dimension(900,150));

	}
}
