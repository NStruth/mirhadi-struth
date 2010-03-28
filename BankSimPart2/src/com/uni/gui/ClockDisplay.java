package com.uni.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.Statistics;
import com.uni.main.Timer;


public class ClockDisplay extends JPanel implements Observation.Observer{
	
	private JLabel clock;
	
	
	public ClockDisplay(Timer t)
	{
		//this.setMinimumSize(new Dimension(100,100));
		//this.setMaximumSize(new Dimension(100,100));
		t.registerObserver(this);
		initComponents();
	}
	

	public void initComponents()
	{
		this.setLayout(new BorderLayout());
		
		this.add(new JLabel("Time"), BorderLayout.NORTH);
		
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
	}
	
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
		if(Statistics.CURRENT_HOUR >= 9 && Statistics.CURRENT_HOUR <= 17)
			clock.setForeground(Color.GREEN);
		else
			clock.setForeground(Color.RED);
		
	}

}
