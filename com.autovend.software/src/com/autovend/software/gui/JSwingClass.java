package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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
				
				// grid bag layout setup
				// GridBagConstraints mainscreenLayout = new GridBagConstraints();
				//touchScreenPanel.add(mainscreenLayout);
				
				
				
				/*
				touchScreenPanel.setLayout(new GridLayout(3,3));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.add(new JLabel("hello"));
				touchScreenPanel.setBackground(Color.RED);
				*/
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
