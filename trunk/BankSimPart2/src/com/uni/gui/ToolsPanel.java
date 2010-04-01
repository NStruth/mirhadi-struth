package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class ToolsPanel extends JPanel implements ActionListener {
	private JButton closeButton;
	private JButton disableClose;
	BankSimulator bs;
	private JButton pauseButton;
	private JButton resetButton;

	public ToolsPanel(BankSimulator bs){
		this.bs = bs;
		initComponents();
	}
	
	public void initComponents(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.add(new JLabel("Tools"), BorderLayout.NORTH);
		this.setPreferredSize(new Dimension(450, 400));
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
		this.add(tPanel);
		
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
		this.add(qPanel);
		
		
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
		cPanel.setPreferredSize(new Dimension(450,20));
		clockSlider.setPreferredSize(new Dimension(450,20));
		this.add(cPanel);

		
		
		JButton startButton = new JButton("Start Simulation");
		
		closeButton = new JButton("Override Bank Open");
		closeButton.setActionCommand("closeswitch");
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setActionCommand("reset");
		
		disableClose = new JButton("Disable Override");
		disableClose.setActionCommand("disableswitch");
		disableClose.setEnabled(false);
		
		startButton.addActionListener(this);
		closeButton.addActionListener(this);
		disableClose.addActionListener(this);
		
		startButton.setPreferredSize(new Dimension(300,30));
		closeButton.setPreferredSize(new Dimension(120,30));
		disableClose.setPreferredSize(new Dimension(120,30));
		
		
		this.add(startButton);
		this.add(pauseButton);
		this.add(resetButton);
		this.add(closeButton);
		this.add(disableClose);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Start Simulation")){
			this.bs.startSimulation();
			System.out.println("CLICK");
		}
		if(arg0.getActionCommand().equals("closeswitch"))
		{
			//p.remove(closeButton);
			Statistics.MANUAL_CLOSE_OVERRIDE = true;
			//p.add(disableClose);
			//p.revalidate();
			closeButton.setEnabled(false);
			disableClose.setEnabled(true);
		}
		
		if(arg0.getActionCommand().equals("disableswitch"))
		{
			Statistics.MANUAL_CLOSE_OVERRIDE = false;
		//	p.remove(disableClose);
			//p.add(closeButton);
			
			//p.revalidate();
			closeButton.setEnabled(true);
			disableClose.setEnabled(false);
		}
		
		if(arg0.getActionCommand().equals("Pause")){
			bs.pause();
		}
		
		if(arg0.getActionCommand().equals("reset")){
			bs.reset();
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
