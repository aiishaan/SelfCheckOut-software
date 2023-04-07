package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
public class JSwingClass2 {
	JFrame touchScreenFrame;
	JComboBox<String> languageBox;
	DefaultTableModel paymentTableModel;
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
    private JButton paymentButton;
    private JScrollPane scrollPane;
    private JTable table;
	
	public JSwingClass2() {
		
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
		memberTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberTextField.setText("Sign in with membership for rewards");
		memberTextField.setHorizontalAlignment(JTextField.CENTER);
		memberTextField.setColumns(10);
		memberTextField.setBounds(42, 106, 468, 40);
		mainPanel.add(memberTextField);
		
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
		setUpPaymentTable();
		setUpPayment();
		tapScreen();
		
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
		screensaver.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screensaver.setBounds(0, 0, 984, 785);
		screensaver.setContentAreaFilled(false);
		tapScreenPanel.add(screensaver);}
	
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
					helpButton.setText("¿Necesitas ayuda?");
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
				paymentTableModel.addRow(new Object[] {"Item Cost", "Item Count", "Item Cost"});
			}
		});
		mainPanel.add(paymentButton);
	}
	
	private void setUpPaymentTable() {
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(564, 45, 393, 646);
		mainPanel.add(scrollPane);
		
		// Setup jtable, waiting to connect to software
	    String[][] data = {{"Wagyu beef @$250.00", "0.50", "$125.00"},{"Pork chop @$4.67", "5.00", "$23.35"}};
	    
	    // column name
		String[] cName = {"Item @cost per unit", "Count", "Total cost"};
		
		table = new JTable(new DefaultTableModel(data, cName));
		paymentTableModel = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);
	}
	public static void main(String[] args) {
		new JSwingClass2();
	}
}
