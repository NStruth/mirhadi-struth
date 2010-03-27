package com.uni.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ToolsPanel extends JPanel{

	public ToolsPanel(){
		super();
		initComponents();
	}
	
	public void initComponents(){
		this.setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		this.add(new JLabel("Tools"), BorderLayout.NORTH);
		
		JSlider speedSlider = new JSlider();
		speedSlider.setPaintTicks(true);
		p.add(speedSlider);
		
		JButton startButton = new JButton("Start Simulation");
		JButton stopButton = new JButton("Stop Simulation");
		
		p.add(startButton);
		p.add(stopButton);
		
		
		this.add(p, BorderLayout.CENTER);
		
	}
}
