package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.autovend.Barcode;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.BarcodeScanner;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.BarcodeScannerObserver;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;

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
	JButton purchaseBagsButton;
	JButton removeItemButton;
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
	
	public JSwingClass2(){
		
		touchScreenFrame = new JFrame("");
		touchScreenFrame.setSize(1000, 900);
		touchScreenFrame.setResizable(false);
		
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layeredPane = new JLayeredPane();
		touchScreenFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		setUpMainPanel();
		setUpSecondaryPanel();
		setUpPaymentTable();
		setUpPayment();
		tapScreen();
		ownBag();
		
		touchScreenFrame.setVisible(true);
	}
	
	private void setUpMainPanel() {
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
		
		purchaseBagsButton = new JButton("Purchase Bags");
		purchaseBagsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		purchaseBagsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		purchaseBagsButton.setBounds(42, 183, 468, 40);
		mainPanel.add(purchaseBagsButton);
		
		/* Adding RigidAreas. RigidAreas simply ensure that no component 
		 * enters a specific part of the screen. */
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setBounds(42, 70, 468, 39);
		mainPanel.add(rigidArea);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea2.setBounds(42, 144, 468, 39);
		mainPanel.add(rigidArea2);
		
		Component rigidArea3 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea3.setBounds(42, 219, 468, 39);
		mainPanel.add(rigidArea3);
		
		removeItemButton = new JButton("Remove an Item");
		removeItemButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeItemButton.setBounds(42, 256, 468, 40);
		mainPanel.add(removeItemButton);
	}
	
	private void setUpSecondaryPanel() {
		secondaryPanel = new JPanel();
		secondaryPanel.setBackground(Color.LIGHT_GRAY);
		layeredPane.setLayer(secondaryPanel, 2);
		secondaryPanel.setBounds(0, 784, 984, 77);
		layeredPane.add(secondaryPanel);
		secondaryPanel.setLayout(null);
		setUpLanguage();
		setUpAudio();
		setUpHelp();
	}
	
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
	
	private void tapScreen() {
		
		tapScreenPanel = new JPanel();
		layeredPane.setLayer(tapScreenPanel, 1);
		tapScreenPanel.setBounds(0, 0, 985, 785);
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
		languageBox = new JComboBox<String>();
		languageBox.setModel(new DefaultComboBoxModel<String>(new String[] {"English", "French", "Spanish"}));
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
		String[] cName = {"Item ", "@cost per unit", "Total cost"};
		
		table = new JTable(new DefaultTableModel(data, cName));
		paymentTableModel = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);
	}
	public static void main(String[] args) {
		new JSwingClass2();
	}
	
	public void updateTable(String itemPrice, String itemWeight) {
		paymentTableModel.addRow(new Object[] {"placeholder", itemPrice, itemWeight});
	}
	
	/*class MyBarcodeScanner implements BarcodeScannerObserver {

		@Override
		public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		}

		@Override
		public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		}

		@Override
		public void reactToBarcodeScannedEvent(BarcodeScanner barcodeScanner, Barcode barcode) {
			BarcodedProduct scannedItem = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
			if (scannedItem != null) {
				String item = scannedItem.getBarcode().toString();
				String itemPrice = scannedItem.getPrice().toString();
				String itemWeight = String.valueOf(scannedItem.getExpectedWeight());
				paymentTableModel.addRow(new Object[] {item, itemPrice, itemWeight});
			}
		}
	}*/
}
