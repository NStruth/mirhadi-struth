/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Stores tools allowing interaction from the user.
 * These are:
 * 	Slider for teller sped
 * 	Slider for queue speed
 * 	Slider for overall speed factor
 * 	Start/Resume Simulation
 * 	Pause Simulation
 * 	Override bank close - i.e close bank early
 * 	Disable bank close - re open bank
 * 
 */
package com.uni.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton startButton;
	private JPanel buttonPanel;

	public ToolsPanel(BankSimulator bs){
		this.bs = bs;
		initComponents();
	}
	
	public void initComponents(){
		/* Use a vertical box layout */
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(450, 400));
		
		// Teller Slider
		MySlider tSlider = new MySlider("Teller", "Fast", "Slow", 200, 10, 10);
		tSlider.setChangeListener(new TellerListener());
		this.add(tSlider);
		
		// Queue Slider
		MySlider qSlider = new MySlider("Queue", "Fast", "Slow", 200, 10, 10);
		qSlider.setChangeListener(new QueueListener());
		this.add(qSlider);
		
		//Clock Panel
		MySlider cSlider = new MySlider("Clock", "Fast", "Slow", 20, 1, 1);
		cSlider.setChangeListener(new ClockListener());
		this.add(cSlider);

		buttonPanel = new JPanel(new GridLayout(0,2));
		
		startButton = new JButton("Start/Resume Simulation");
		startButton.setActionCommand("Start Simulation");
		
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
		
		if(!Statistics.RUN_ONCE ){
		
			startButton.setEnabled(true);
			closeButton.setEnabled(false);
			resetButton.setEnabled(false);
			disableClose.setEnabled(false);
			pauseButton.setEnabled(false);
		}else{
			pauseButton.setEnabled(true);
			startButton.setEnabled(false);
			closeButton.setEnabled(true);
			resetButton.setEnabled(false);
			disableClose.setEnabled(false);
		}
		
		JButton nullButton = new JButton("null");
		nullButton.setVisible(false);
		
		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(closeButton);
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(disableClose);
		this.add(buttonPanel);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Start Simulation")){
			this.bs.startSimulation();
			pauseButton.setEnabled(true);
			startButton.setEnabled(false);
			
			closeButton.setEnabled(true);
			resetButton.setEnabled(false);
			disableClose.setEnabled(false);
			
			Statistics.RUN_ONCE = true;
			
		}
		if(arg0.getActionCommand().equals("closeswitch"))
		{
			Statistics.MANUAL_CLOSE_OVERRIDE = true;
			closeButton.setEnabled(false);
			disableClose.setEnabled(true);
		}
		
		if(arg0.getActionCommand().equals("disableswitch"))
		{
			Statistics.MANUAL_CLOSE_OVERRIDE = false;
			closeButton.setEnabled(true);
			disableClose.setEnabled(false);
		}
		
		if(arg0.getActionCommand().equals("Pause")){
			bs.pause();
			pauseButton.setEnabled(false);
			startButton.setEnabled(true);
			resetButton.setEnabled(true);
		}
		
		if(arg0.getActionCommand().equals("reset")){
			final JOptionPane optionPane = new JOptionPane(
	                "Would you like to write these summary stats to a file?\n",
	                JOptionPane.QUESTION_MESSAGE,
	                JOptionPane.YES_NO_CANCEL_OPTION);
			final JDialog dialog = new JDialog();
			dialog.setModal(true);
			dialog.setContentPane(optionPane);
			optionPane.addPropertyChangeListener(
				    new PropertyChangeListener() {
				        public void propertyChange(PropertyChangeEvent e) {
				            String prop = e.getPropertyName();

				            if (dialog.isVisible() 
				             && (e.getSource() == optionPane)
				             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
				                //If you were going to check something
				                //before closing the window, you'd do
				                //it here.
				                dialog.setVisible(false);
				            }
				        }
				    });
				dialog.pack();
				dialog.setVisible(true);

				int value = ((Integer)optionPane.getValue()).intValue();
				if (value == JOptionPane.YES_OPTION) {
					bs.writeSummary();
					bs.reset();
					
				} else if (value == JOptionPane.NO_OPTION) {
					bs.reset();
				}
			
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
			System.out.println(Statistics.SIMULATION_SPEED_FACTOR);
			}    
	    }
	}
	
}
