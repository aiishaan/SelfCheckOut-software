package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Component;
public class JSwingClass {
	JFrame touchScreenFrame;
	JComboBox languageBox;
	JPanel touchScreenPanel;
	JPanel tapScreenPanel;
	JPanel mainPanel;
	JPanel secondaryPanel;
	JLayeredPane layeredPane;
	JButton screensaver;
	JButton helpButton;
	JButton audioButton;
	boolean audioButtonOn = false;
	
	// create a constraints object
	GridBagConstraints c = new GridBagConstraints();
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    private JTextField PLUTextField;
    private JTextField memberTextField;
    private JTable table;
    private JButton paymentButton;
	
	public JSwingClass() {
		
		touchScreenFrame = new JFrame("");
		touchScreenFrame.setSize(1000, 900);
		touchScreenFrame.setResizable(false);
		
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layeredPane = new JLayeredPane();
		touchScreenFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBounds(0, 0, 984, 785);
		layeredPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		PLUTextField = new JTextField();
		PLUTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PLUTextField.setText("Scan a barcode or tap here to search with PLU");
		PLUTextField.setHorizontalAlignment(JTextField.CENTER);
		PLUTextField.setBounds(42, 31, 468, 40);
		mainPanel.add(PLUTextField);
		PLUTextField.setColumns(10);
		
		memberTextField = new JTextField();
		memberTextField.setEditable(false);
		memberTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberTextField.setText("Sign in with membership for rewards");
		memberTextField.setHorizontalAlignment(JTextField.CENTER);
		memberTextField.setColumns(10);
		memberTextField.setBounds(42, 106, 468, 40);
		mainPanel.add(memberTextField);
		
		table = new JTable();
		table.setBounds(564, 31, 393, 646);
		mainPanel.add(table);
		
		JButton purchaseBagsButton = new JButton("Purchase Bags");
		purchaseBagsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		purchaseBagsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		purchaseBagsButton.setBounds(42, 183, 468, 40);
		mainPanel.add(purchaseBagsButton);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setBounds(42, 70, 468, 39);
		mainPanel.add(rigidArea);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2.setBounds(42, 144, 468, 39);
		mainPanel.add(rigidArea_2);
		
		Component rigidArea_2_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_2_1.setBounds(42, 219, 468, 39);
		mainPanel.add(rigidArea_2_1);
		
		JButton btnNewButton_1_1 = new JButton("Remove an Item");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBounds(42, 256, 468, 40);
		mainPanel.add(btnNewButton_1_1);
		
		secondaryPanel = new JPanel();
		secondaryPanel.setBackground(Color.LIGHT_GRAY);
		layeredPane.setLayer(secondaryPanel, 2);
		secondaryPanel.setBounds(0, 784, 984, 77);
		layeredPane.add(secondaryPanel);
		secondaryPanel.setLayout(null);
		
		setUpLanguage();
		setUpAudio();
		setUpHelp();
		setUpPayment();
		tapScreen();
		ownBag();
		
		touchScreenFrame.setVisible(true);
	}
	private void tapScreen() {
		
		tapScreenPanel = new JPanel();
		layeredPane.setLayer(tapScreenPanel, 1);
		tapScreenPanel.setBounds(0, 0, 984, 785);
		layeredPane.add(tapScreenPanel);
		tapScreenPanel.setLayout(null);
		
		screensaver = new JButton("Welcome. \nPlease touch the screen to continue.");
		screensaver.setDisplayedMnemonicIndex(1);
		screensaver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tapScreenPanel.setVisible(false);
			}
		});
		screensaver.setFont(new Font("Arial", Font.PLAIN, 40));
		screensaver.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//screensaver.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screensaver.setBounds(0, 0, 984, 785);
		screensaver.setContentAreaFilled(false);
		tapScreenPanel.add(screensaver);}
	
	private void ownBag() {
		
		JPanel ownBag = new JPanel();
		layeredPane.setLayer(ownBag, 1);
		ownBag.setBounds(0, 0, 984, 785);
		ownBag.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(ownBag);
		ownBag.setLayout(new GridBagLayout());
		
		JTextField haveBag = new JTextField("Do you bring your own bag(s) today?");
//		screensaver.setDisplayedMnemonicIndex(1);
//		screensaver.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tapScreenPanel.setVisible(false);
//			}
//		});
		haveBag.setFont(new Font("Arial", Font.PLAIN, 40));
		haveBag.setEditable(false);
		haveBag.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		haveBag.setBackground(Color.LIGHT_GRAY);
		//haveBag.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		haveBag.setBounds(0, 0, 984, 785);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 20;
		ownBag.add(haveBag, c);
		
		JButton yb = new JButton("Yes");
		JButton nb = new JButton("No");
		yb.setFont(new Font("Arial", Font.PLAIN, 40));
		nb.setFont(new Font("Arial", Font.PLAIN, 40));
		yb.setPreferredSize(new Dimension(200, 75));
		nb.setPreferredSize(new Dimension(200, 75));
		c.gridy = 1;
		ownBag.add(yb, c);
		c.gridy = 2;
		ownBag.add(nb, c);
		nb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownBag.setVisible(false);
			}
		});
		yb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownBag.setVisible(false);
				ownBagAdded();
			}
		});
	}
	
	private void ownBagAdded() {
		JPanel ownBagAdded = new JPanel();
		layeredPane.setLayer(ownBagAdded, 1);
		ownBagAdded.setBounds(0, 0, 984, 785);
		ownBagAdded.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(ownBagAdded);
		ownBagAdded.setLayout(null);
		
		JTextArea hadBag = new JTextArea("Please put your bag(s) into bagging area \n   and wait for our attendant to confirm");
		hadBag.setFont(new Font("Arial", Font.PLAIN, 40));
		hadBag.setEditable(false);
		hadBag.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		hadBag.setBackground(Color.LIGHT_GRAY);
		hadBag.setBounds(145, 350, 984, 785);
		
		ownBagAdded.add(hadBag);
		boolean check = false;
		
		// wait for attendant to confirm and then set visible to false, rn i'm changing it right away
		check = true;	// this will change after get acceptance from attendant
		if (check == true) {
			ownBagAdded.setVisible(false);
		}
	}
	
	private void setUpLanguage() {
		String[] selectedlanguage = {"English", "French", "Spanish"};
		languageBox = new JComboBox(selectedlanguage);
		languageBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		languageBox.setBounds(10, 24, 115, 42);
		secondaryPanel.add(languageBox);
		languageBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(languageBox.getSelectedIndex());
				if(languageBox.getSelectedIndex() == 0) {
					helpButton.setText("Need help?");
					audioButton.setText("Text-to-speech");
				}
				if(languageBox.getSelectedIndex() == 1) {
					helpButton.setText("Besoin d'aide?");
					audioButton.setText("texte pour parler");
				}
				if(languageBox.getSelectedIndex() == 2) {
					helpButton.setText("Â¿Necesitas ayuda?");
					audioButton.setText("texto a voz");
				}
			}
		});
	}
	
	private void setUpAudio() {
		audioButton = new JButton("Audio");
		audioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		audioButton.setBounds(135, 24, 149, 42);
		secondaryPanel.add(audioButton);
	}
		
	private void setUpHelp() {
		helpButton = new JButton("Help");
		helpButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Calling attendant");
			}
		});
		helpButton.setBounds(799, 25, 160, 41);
		secondaryPanel.add(helpButton);
	}
		
	private void setUpPayment() {
		paymentButton = new JButton("Continue to payment >>>");
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		paymentButton.setBounds(564, 703, 393, 40);
		paymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mainPanel.add(paymentButton);
	}
	public static void main(String[] args) {
		new JSwingClass();
	}
}