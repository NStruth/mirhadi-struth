package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.Statistics;
import com.uni.main.Timer;
import com.uni.queue.CustomerQueue;



public class ClockDisplay extends JPanel implements Observation.Observer, ActionListener{
	
	private JLabel clock;
	private JButton skipTimeButton;
	private CustomerQueue cq;
	private CustomerQueue ocq;
	
	
	public ClockDisplay(Timer t, CustomerQueue cq, CustomerQueue ocq)
	{
		//this.setMinimumSize(new Dimension(100,100));
		//this.setMaximumSize(new Dimension(100,100));
		this.cq = cq;
		this.ocq = ocq;
		t.registerObserver(this);
		initComponents();
		this.setPreferredSize(new Dimension(100,200));
	}
	

	/**
	 * Initialise the components for this clock
	 */
	public void initComponents()
	{
		//some layout stuff
		this.setLayout(new BorderLayout());
		//add a header
		JLabel timeHeader = new JLabel("Time");
		timeHeader.setFont(GuiMain.HEADER_FONT);
		this.add(timeHeader, BorderLayout.NORTH);
		
		//Format the clock to 24 hour
		if(Statistics.CURRENT_HOUR < 10)	
			clock = new JLabel("0"+Statistics.CURRENT_HOUR+":00");
		else
			clock = new JLabel(Statistics.CURRENT_HOUR+":00");
		
		if(Statistics.HOUR_VAL >= 9 && Statistics.HOUR_VAL <= 17)
			clock.setForeground(Color.GREEN);
		else
			clock.setForeground(Color.RED);
		clock.setFont(new Font("Dialog", Font.BOLD, 26));
		
		clock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(clock, BorderLayout.CENTER);
		
		//add a skip button to "skip" the night
		skipTimeButton = new JButton("Skip");
		skipTimeButton.setEnabled(false);
		skipTimeButton.setActionCommand("skip");
		skipTimeButton.addActionListener(this);
		this.add(skipTimeButton, BorderLayout.SOUTH);
		
	}
	
	
	/* (non-Javadoc)
	 * @see Observation.Observer#update()
	 */
	public void update() {
		
		String mins = "";
		
		if(Statistics.CURRENT_MIN < 10)
			mins = "0" + Statistics.CURRENT_MIN;
		else
			mins = Statistics.CURRENT_MIN + "";
		
		if(Statistics.CURRENT_HOUR < 10)
			clock.setText("0"+Statistics.CURRENT_HOUR+":" + mins);
		else
			clock.setText(Statistics.CURRENT_HOUR+":" + mins);
		if(Statistics.CURRENT_HOUR >= 9 && Statistics.CURRENT_HOUR <= 17){
			skipTimeButton.setEnabled(false);
			clock.setForeground(Color.GREEN);
		}else{
			//
			//System.out.println("Size:" + cq.size() + " Size: " + ocq.size());
			clock.setForeground(Color.RED);
			if(cq.size() == 0 && ocq.size() == 0){
				skipTimeButton.setEnabled(true);
			}else{
				skipTimeButton.setEnabled(false);
			}
		}
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//change the time to tomorrow morning
		if(e.getActionCommand().equals("skip")){
			Statistics.CURRENT_HOUR = Statistics.OPEN_TIME;
			Statistics.CURRENT_MIN = 0;
		}
	}

}
