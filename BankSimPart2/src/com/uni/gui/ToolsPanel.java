package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.uni.main.BankSimulator;
import com.uni.main.Statistics;

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
		
		//this.add(new JLabel("Tools"), BorderLayout.NORTH);
		
		// Teller Slider
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new BorderLayout());
		
		tPanel.add(new JLabel("Teller"), BorderLayout.NORTH);
		tPanel.add(new JLabel("Fast"), BorderLayout.WEST);
		JSlider tellerSlider = new JSlider(1, 200);
		tellerSlider.setMajorTickSpacing(10);
		tellerSlider.setMinorTickSpacing(10);
		tellerSlider.setPaintTicks(true);
		tellerSlider.setSnapToTicks(true);
		tellerSlider.addChangeListener(new TellerListener());
		tPanel.add(tellerSlider);
		tPanel.add(new JLabel("Slow"), BorderLayout.EAST);
		p.add(tPanel);
		
		// Queue Slider
		JPanel qPanel = new JPanel();
		qPanel.setLayout(new BorderLayout());
		
		qPanel.add(new JLabel("Queue"), BorderLayout.NORTH);
		qPanel.add(new JLabel("Fast"), BorderLayout.WEST);
		JSlider queueSlider = new JSlider(1, 200);
		queueSlider.setMajorTickSpacing(10);
		queueSlider.setMinorTickSpacing(10);
		queueSlider.setPaintTicks(true);
		queueSlider.setSnapToTicks(true);
		queueSlider.addChangeListener(new QueueListener());
		qPanel.add(queueSlider);
		qPanel.add(new JLabel("Slow"), BorderLayout.EAST);
		p.add(qPanel);
		
		
		//Clock Panel
		
		JPanel cPanel = new JPanel();
		cPanel.setLayout(new BorderLayout());
		
		cPanel.add(new JLabel("Clock"), BorderLayout.NORTH);
		cPanel.add(new JLabel("Fast"), BorderLayout.WEST);
		JSlider clockSlider = new JSlider(1, 20);
		clockSlider.setMajorTickSpacing(1);
		clockSlider.setMinorTickSpacing(1);
		clockSlider.setPaintTicks(true);
		clockSlider.setSnapToTicks(true);
		clockSlider.addChangeListener(new ClockListener());
		cPanel.add(clockSlider);
		cPanel.add(new JLabel("Slow"), BorderLayout.EAST);
		p.add(cPanel);

		
		
		JButton startButton = new JButton("Start Simulation");
		JButton stopButton = new JButton("Stop Simulation");
		
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		p.add(startButton);
		p.add(stopButton);
		
		
		this.add(p, BorderLayout.CENTER);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Start Simulation")){
			bs.startSimulation();
			System.out.println("CLICK");
		}
		if(arg0.getActionCommand().equals("Stop Simulation"))
		{
			bs.stopSimulation();
		}

	}
	class TellerListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            Statistics.TELLER_SPEED = (int)source.getValue();
	        }    
	    }
	}
	    
	class QueueListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			Statistics.GENERATOR_SPEED = (int)source.getValue();
			}    
	    }
	}
	class ClockListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			Statistics.SIMULATION_SPEED_FACTOR = (int)source.getValue();
			}    
	    }
	}
}
