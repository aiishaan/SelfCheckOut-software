package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class JSwingClass {
	JFrame touchScreenFrame;
	JPanel touchScreenPanel;
	JPanel touchScreenPanel2;
	JButton screensaver;
	JButton helpButton;
	JComboBox languageButton;
	JButton audioButton;
	boolean audioButtonOn = false;
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
	public JSwingClass() {
		
		touchScreenFrame = new JFrame("");
		touchScreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		touchScreenFrame.setResizable(false);
		touchScreenPanel2 = new JPanel();
		touchScreenPanel2.setLayout(new GridLayout(1,3));
		touchScreenPanel2.setPreferredSize(new Dimension(1000, 100));
		
		tapScreen();
		language();
		audio();
		help();
		
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		touchScreenFrame.add(touchScreenPanel2, BorderLayout.SOUTH);
		touchScreenFrame.setVisible(true);
	}
	
	private void tapScreen() {
		screensaver = new JButton("Tap the screen when ready");
		screensaver.setBorder(BorderFactory.createLoweredBevelBorder());
		screensaver.setContentAreaFilled(false); // removes the visual effect of clicking a button
		screensaver.setBackground(Color.LIGHT_GRAY);
		screensaver.setOpaque(false);
		screensaver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screensaver.setVisible(false);
				touchScreenPanel = new JPanel();
				JButton button;
				
				// grid bag layout setup

				// set the layout
				touchScreenPanel.setLayout(new GridBagLayout());
				// create a constraints object
				GridBagConstraints c = new GridBagConstraints();

				// insets for all components
				c.insets = new Insets(2,2,2,2);
				// if condition to fill the width
			    if (shouldFill) {
			 
			        // natural height, maximum width
			        c.fill = GridBagConstraints.HORIZONTAL;
			    }
			    
			    // Object initialization
			    
			    // add item button
			    button = new JButton("Scan you items or tap here to search for it");
			 
			    // if condition
			    if (shouldWeightX) {
			        c.weightx = 0.5;
			    }
			    // column 0
			    c.gridx = 0;
			    // row 0
			    c.gridy = 0;
			    // Adding JButton "button" on JFrame.
			    touchScreenPanel.add(button, c);
			    
			    // membership button
			    button = new JButton("Sign in with your membership for rewards");
			 
			    // if condition
			    if (shouldWeightX) {
			        c.weightx = 0.5;
			    }
			    // column 0
			    c.gridx = 0;
			    // row 0
			    c.gridy = 1;
			    // Adding JButton "button" on JFrame.
			    touchScreenPanel.add(button, c);
			    
			    // bags button
			    button = new JButton("Purchase bags");
			 
			    // if condition
			    if (shouldWeightX) {
			        c.weightx = 0.5;
			    }
			    // column 0
			    c.gridx = 0;
			    // row 0
			    c.gridy = 2;
			    // Adding JButton "button" on JFrame.
			    touchScreenPanel.add(button, c);
			    
			    // remove button
			    button = new JButton("Remove an item");
			 
			    // if condition
			    if (shouldWeightX) {
			        c.weightx = 0.5;
			    }
			    // column 0
			    c.gridx = 0;
			    // row 0
			    c.gridy = 3;
			    // Adding JButton "button" on JFrame.
			    touchScreenPanel.add(button, c);
			    
			    // Setup jtable, waiting to connect to software
			    String[][] data = {{"Wagyu beef @$250.00", "0.50", "$125.00"},{"Pork chop @$4.67", "5.00", "$23.35"}};
			    
			    // column name
				String[] cName = {"Item @cost per unit", "Count", "Total cost"};
				
				JTable j = new JTable(data, cName);
		        j.setBounds(30, 40, 200, 300);
		 
		        // adding it to JScrollPane
		        JScrollPane sp = new JScrollPane(j);
		        
		        c.gridx = 1;
		        c.gridy = 0;
		        c.gridheight = 5;
		        touchScreenPanel.add(sp, c);

		        
				//touchScreenPanel.setBackground(Color.RED);
				touchScreenFrame.add(touchScreenPanel, BorderLayout.CENTER);
			}
		});
		touchScreenFrame.add(screensaver, BorderLayout.CENTER);
	}
	private void language() {
		String[] selectedlanguageButton = {"English", "French", "Spanish"};
		languageButton = new JComboBox(selectedlanguageButton);
		languageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(languageButton.getSelectedIndex());
				if(languageButton.getSelectedIndex() == 0) {
					helpButton.setText("Need help?");
					audioButton.setText("Text-to-speech");
				}
				if(languageButton.getSelectedIndex() == 1) {
					helpButton.setText("Besoin d'aide?");
					audioButton.setText("texte pour parler");
				}
				if(languageButton.getSelectedIndex() == 2) {
					helpButton.setText("¿Necesitas ayuda?");
					audioButton.setText("texto a voz");
				}
			}
		});
		touchScreenPanel2.add(languageButton);
	}
	
	private void audio() {
		audioButton = new JButton("Text-to-speech");
		touchScreenPanel2.add(audioButton);
		audioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(audioButtonOn) {
					audioButtonOn = false; 
				}
				else {
					audioButtonOn = true;
				}
				System.out.println("Text-to-speech is: " + audioButtonOn);
			}
		});
	}
	
	private void help() {
		helpButton = new JButton("Need help?");
		touchScreenPanel2.add(helpButton);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Calling attendant");
			}
		});
	}
	
	public static void main(String[] args) {
		new JSwingClass();
	}
}
