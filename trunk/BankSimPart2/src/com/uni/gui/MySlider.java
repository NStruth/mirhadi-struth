package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class MySlider extends JPanel{
	
	private JSlider slider;

	public MySlider(String label, String topMessage, String bottomMessage, int maxRange, int majS, int minS){
		
		this.setLayout(new BorderLayout());
		
		this.add(new JLabel(label), BorderLayout.NORTH);
		this.add(new JLabel(topMessage), BorderLayout.WEST);
		slider = new JSlider(1, maxRange);
		slider.setMajorTickSpacing(majS);
		slider.setMinorTickSpacing(minS);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		
		this.setPreferredSize(new Dimension(450,20));
		slider.setPreferredSize(new Dimension(450,20));
		
		this.add(slider);
		this.add(new JLabel(bottomMessage), BorderLayout.EAST);
	}
	
	public void setChangeListener(ChangeListener l){
		slider.addChangeListener(l);
	}
	
}
