package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.uni.main.BankSimulator;

public class ToolsPanel extends JPanel implements ActionListener{

	private BankSimulator bs;

	public ToolsPanel(BankSimulator bs){
		super();
		initComponents();
		this.bs = bs;
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
		
		startButton.addActionListener(this);
		
		p.add(startButton);
		p.add(stopButton);
		
		
		this.add(p, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Start Simulation")){
			bs.startSimulation();
		}
		
	}
}