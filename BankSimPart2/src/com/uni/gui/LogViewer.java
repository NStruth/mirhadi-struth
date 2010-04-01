/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * A basic gui to view the log file
 * 
 */

package com.uni.gui;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogViewer extends JFrame{

	/**
	 * Constructor for this viewer
	 */
	public LogViewer(){
		super();
		this.setLayout(new FlowLayout());
		this.setBounds(50,50,400,600);
		this.setVisible(true);
		initComponents();
	}
	/**
	 * Initialise the components to hold the log
	 */
	public void initComponents(){
		JTextArea jta = new JTextArea(readFromFile(),400,600);
		JScrollPane jScroll = new JScrollPane(jta);
		jScroll.setPreferredSize(this.getSize());
		
		this.add(jScroll);
	}
	/**
	 * Read the log from the file
	 * @return the log file as a string
	 */
	public String readFromFile(){
		File file = new File("log.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null)
            {
                contents.append(text)
                    .append(System.getProperty(
                        "line.separator"));
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
       return contents.toString();
	}
}
