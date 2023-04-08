package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.TouchScreenObserver;
import com.autovend.devices.TouchScreen;
public class CustomerGui implements TouchScreenObserver{
	JFrame touchScreenFrame;
	JComboBox<String> languageBox;
	DefaultTableModel paymentTableModel;
	JPanel touchScreenPanel;
	JPanel tapScreenPanel;
	JPanel mainPanel;
	JPanel secondaryPanel;
	JPanel keyboardPanel;
	JLayeredPane layeredPane;
	JButton PLUTextButton;
	JButton memberTextButton;
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
  
    private JButton paymentButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton keyZero;
    private JButton keyOne;
    private JButton keyTwo;
    private JButton keyThree;
    private JButton keyFour;
    private JButton keyFive;
    private JButton keySix;
    private JButton keySeven;
    private JButton keyEight;
    private JButton keyNine;
    private JButton keyEnter;
    private JButton keyExit;
    private JTextField keyboardTextField;
    private String keyboardText;
	
	public CustomerGui(TouchScreen customerScreen){
		
		this.touchScreenFrame = customerScreen.getFrame();
		this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
		this.touchScreenFrame.setSize(1000,900);
		layeredPane = new JLayeredPane();
		this.touchScreenFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		setUpMainPanel();
		setUpSecondaryPanel();
		setUpNumericKeyboard();
		tapScreen();
		ownBag();
		
		this.touchScreenFrame.setVisible(true);
	}
	
	private void setUpMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBounds(0, 0, 984, 785);
		layeredPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		PLUTextButton = new JButton("Scan a barcode or tap here to search with PLU");
		PLUTextButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PLUTextButton.setOpaque(true);
		PLUTextButton.setBackground(Color.WHITE);
		PLUTextButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PLUTextButton.setBounds(42, 31, 468, 40);
		PLUTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openKeyboard();
			}
		});
		mainPanel.add(PLUTextButton);
		
		memberTextButton = new JButton("Sign in with membership for rewards");
		memberTextButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		memberTextButton.setOpaque(true);
		memberTextButton.setBackground(Color.WHITE);
		memberTextButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberTextButton.setBounds(42, 106, 468, 40);
		memberTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openKeyboard();
			}
		});
		mainPanel.add(memberTextButton);
		
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
		setUpPaymentTable();
		setUpPayment();
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
	
	private void setUpNumericKeyboard() {
		keyboardPanel = new JPanel();
		keyboardPanel.setOpaque(true);
		layeredPane.setLayer(keyboardPanel, 1);
		keyboardPanel.setBounds(350, 144, 290, 465);
		layeredPane.add(keyboardPanel);
		keyboardPanel.setLayout(null);
		
		keyboardText = "";
		keyboardTextField = new JTextField(keyboardText);
		keyboardTextField.setEditable(false);
		keyboardTextField.setFocusable(false);
		keyboardTextField.setBounds(65, 10, 215, 44);
		keyboardTextField.setBackground(Color.WHITE);
		keyboardTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		keyboardPanel.add(keyboardTextField);
		keyboardTextField.setColumns(10);
		
		//Add the buttons
		keyZero = new JButton("0");
		keyZero.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyZero.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyZero.setBounds(0, 65, 90, 90);
		keyZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "0";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyZero);
		
		keyOne = new JButton("1");
		keyOne.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyOne.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyOne.setBounds(100, 65, 90, 90);
		keyOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "1";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyOne);
		
		keyTwo = new JButton("2");
		keyTwo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyTwo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyTwo.setBounds(200, 65, 90, 90);
		keyTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "2";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyTwo);
		
		keyThree = new JButton("3");
		keyThree.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyThree.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyThree.setBounds(0, 165, 90, 90);
		keyThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "3";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyThree);
		
		keyFour = new JButton("4");
		keyFour.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyFour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyFour.setBounds(100, 165, 90, 90);
		keyFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "4";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyFour);
		
		keyFive = new JButton("5");
		keyFive.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyFive.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyFive.setBounds(200, 165, 90, 90);
		keyFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "5";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyFive);
		
		keySix = new JButton("6");
		keySix.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keySix.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keySix.setBounds(0, 265, 90, 90);
		keySix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "6";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keySix);
		
		keySeven = new JButton("7");
		keySeven.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keySeven.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keySeven.setBounds(100, 265, 90, 90);
		keySeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "7";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keySeven);
		
		keyEight = new JButton("8");
		keyEight.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyEight.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyEight.setBounds(200, 265, 90, 90);
		keyEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "8";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyEight);
		
		keyNine = new JButton("9");
		keyNine.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyNine.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyNine.setBounds(0, 365, 90, 90);
		keyNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText += "9";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyNine);
		
		// Would probably call Membership and PLU searching here
		keyEnter = new JButton("Enter");
		keyEnter.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyEnter.setFont(new Font("Tahoma", Font.PLAIN, 25));
		keyEnter.setBounds(100, 365, 190, 90);
		keyEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(keyboardTextField.getText());
				keyboardText = "";
				keyboardTextField.setText(keyboardText); 
			}
		});
		keyboardPanel.add(keyEnter);
		
		keyExit = new JButton("X");
		keyExit.setOpaque(true);
		keyExit.setBackground(new Color(255, 0, 0));
		keyExit.setForeground(new Color(255, 255, 255));
		keyExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		keyExit.setBounds(10, 10, 45, 45);
		keyExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyboardText = "";
				keyboardTextField.setText(keyboardText); 
				closeKeyboard();
			}
		});
		keyboardPanel.add(keyExit);
		
		keyboardPanel.setVisible(false);
	}
	
	/**
	 * Helper method to lock buttons while the keyboard is up
	 */
	private void openKeyboard() {
		keyboardPanel.setVisible(true);
		PLUTextButton.setEnabled(false);
		memberTextButton.setEnabled(false);
		purchaseBagsButton.setEnabled(false);
		removeItemButton.setEnabled(false);
		paymentButton.setEnabled(false);
	}
	
	/**
	 * Helper method to unlock buttons when the user is done with the keyboard
	 */
	private void closeKeyboard() {
		keyboardPanel.setVisible(false);
		PLUTextButton.setEnabled(true);
		memberTextButton.setEnabled(true);
		purchaseBagsButton.setEnabled(true);
		removeItemButton.setEnabled(true);
		paymentButton.setEnabled(true);
	}
	
	public void updateTable(String itemPrice, String itemWeight) {
		paymentTableModel.addRow(new Object[] {"placeholder", itemPrice, itemWeight});
		System.out.println("Hello");
	}
	public static void main(String[] args) {
		TouchScreen CustomerScreen = new TouchScreen();
		CustomerGui newGui = new CustomerGui(CustomerScreen); 
	}
	
	@Override
	public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}
}
