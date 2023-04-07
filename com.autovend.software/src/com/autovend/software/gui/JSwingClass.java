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
	
	JPanel mainPanel;
	JButton addItemButton;
	JButton membershipButton;
	JButton bagsButton;
	JButton removeButton;
	JButton paymentButton;
	
	// create a constraints object
	GridBagConstraints c = new GridBagConstraints();
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
	public JSwingClass() {
		
		touchScreenFrame = new JFrame("");
		touchScreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		touchScreenFrame.setResizable(false);
		
		touchScreenPanel2 = new JPanel();
		touchScreenPanel2.setLayout(new GridLayout(1,3));
		touchScreenPanel2.setPreferredSize(new Dimension(1000, 100));
		
		mainPanel = new JPanel();
				
		tapScreen();
		defaultLayerSetup();
		language();
		audio();
		help();
		
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//touchScreenFrame.add(mainPanel, BorderLayout.NORTH);
		touchScreenFrame.add(touchScreenPanel2, BorderLayout.SOUTH);
		touchScreenFrame.setVisible(true);
	}
	
	private void tapScreen() {
		JPanel tempPanel = new JPanel();
		
		screensaver = new JButton("Tap the screen when ready");
		screensaver.setBorder(BorderFactory.createLoweredBevelBorder());
		screensaver.setContentAreaFilled(false); // removes the visual effect of clicking a button
		screensaver.setBackground(Color.LIGHT_GRAY);
		screensaver.setOpaque(false);
		screensaver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screensaver.setVisible(false);
//				touchScreenPanel = new JPanel();
//				touchScreenPanel.setLayout(new GridLayout(3,3));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.add(new JLabel("hello"));
//				touchScreenPanel.setBackground(Color.RED);

//				touchScreenFrame.add(touchScreenPanel, BorderLayout.CENTER);
				
				//defaultLayerSetup();
			}
		});
		//tempPanel.add(screensaver, BorderLayout.CENTER);
		//touchScreenFrame.add(tempPanel, BorderLayout.NORTH);
		touchScreenFrame.add(screensaver, BorderLayout.CENTER);
		//mainPanel.add(screensaver);
		//touchScreenFrame.add(screensaver, -1);
	}
	
	
	private void defaultLayerSetup() {
		//touchScreenPanel = new JPanel();
		//JPanel p = new JPanel();
		//tapScreen();
		
		touchScreenFrame.add(mainPanel, BorderLayout.NORTH);
		
		// grid bag layout setup

		// set the layout
		mainPanel.setLayout(new GridBagLayout());
		

		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		addItem();
		membership();
		bags();
		remove();
		summary();
		payment();
	}
	
	private void addItem() {
		addItemButton = new JButton("Scan your items or tap here to search");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 150;
		mainPanel.add(addItemButton, c);
	}
	
	private void membership() {
		membershipButton = new JButton("Sign in with your membership for rewards");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 150;
		mainPanel.add(membershipButton, c);
	}
	
	private void bags() {
		bagsButton = new JButton("Purchase bags");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 150;
		mainPanel.add(bagsButton, c);
	}
	
	private void remove() {
		removeButton = new JButton("Remove an item");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 150;
		mainPanel.add(removeButton, c);
	}
	
	private void summary() {
		// Setup jtable, waiting to connect to software
	    String[][] data = {{"Wagyu beef @$250.00", "0.50", "$125.00"},{"Pork chop @$4.67", "5.00", "$23.35"}};
	    
	    // column name
		String[] cName = {"Item @cost per unit", "Count", "Total cost"};
		
		JTable j = new JTable(data, cName);
        j.setBounds(30, 40, 200, 300);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 282;
        c.gridheight = 4;
        mainPanel.add(sp, c);
	}
	
	private void payment() {
		paymentButton = new JButton("Continue to payment >>>");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 100;
		c.gridheight = 1;
		c.insets = new Insets(50,0,0,0);
		mainPanel.add(paymentButton, c);
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
