package com.uni.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.main.Statistics;
import com.uni.main.Timer;


public class ClockDisplay extends JPanel implements Observation.Observer{
	
	private JLabel clock;
	
	
	public ClockDisplay(Timer t)
	{
		t.registerObserver(this);
		initComponents();
	}
	

	public void initComponents()
	{
		clock = new JLabel(Statistics.CURRENT_HOUR+":00");
		if(Statistics.HOUR_VAL >= 9 && Statistics.HOUR_VAL <= 17)
			clock.setForeground(Color.GREEN);
		else
			clock.setForeground(Color.RED);
		clock.setFont(new Font("Dialog", Font.BOLD, 26));
		this.add(clock);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		clock.setText(Statistics.CURRENT_HOUR+":00");
		if(Statistics.CURRENT_HOUR >= 9 && Statistics.CURRENT_HOUR <= 17)
			clock.setForeground(Color.GREEN);
		else
			clock.setForeground(Color.RED);
		
	}

}
