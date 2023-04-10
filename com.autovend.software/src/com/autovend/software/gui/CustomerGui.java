package com.autovend.software.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.autovend.Numeral;
import com.autovend.devices.TouchScreen;
import com.autovend.software.controllers.BillPaymentController;
import com.autovend.software.controllers.CheckoutController;
import com.autovend.software.controllers.MembershipCardController;
import com.autovend.devices.SelfCheckoutStation;
public class CustomerGui{
	private JFrame touchScreenFrame;
	private CheckoutController checkoutController;
	private MembershipCardController membershipCardController;
	private JComboBox<String> languageBox;
	private DefaultTableModel paymentTableModel;
	private JPanel tapScreenPanel;
	private JPanel mainPanel;
	private JPanel secondaryPanel;
	private JPanel keyboardPanel;
	private JLayeredPane layeredPane;
	private JButton PLUTextButton;
	private JButton itemCata;
	private JButton memberTextButton;
	private JButton purchaseBagsButton;
	private JButton removeItemButton;
	private JButton screensaver;
	private JButton helpButton;
	private JButton audioButton;
	boolean audioButtonOn = false;
	boolean selectedPLU = false;
	boolean selectedMembership = false;
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
    
    private int bagsCount = 0;
    private double bagsValue = bagsCount*0.1;
    
    // Put data here to access the payment table
    String[][] data = new String[][] {{"Bag(s) @$0.10", String.valueOf(bagsCount), "$"+String.valueOf(bagsValue)},{"Wagyu beef @$250.00", "0.50", "$125.00"},{"Pork chop @$4.67", "5.00", "$23.35"}};
    
    // dataI is used to update data
    private int dataI = 3;
    
    // use to count digits
    private int tempCount = 0;
    
    
	// create a constraints object
	GridBagConstraints c = new GridBagConstraints();
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
  
    
	
	public CustomerGui(SelfCheckoutStation cStation, CheckoutController checkoutController, MembershipCardController membershipCardController){

		this.membershipCardController = membershipCardController;
		this.touchScreenFrame = cStation.screen.getFrame();
		this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
		this.touchScreenFrame.setSize(1000,900);
		layeredPane = new JLayeredPane();
		this.touchScreenFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		this.checkoutController = checkoutController;
		
		setUpMainPanel(cStation);
		setUpSecondaryPanel();
		setUpNumericKeyboard(0);
		tapScreen();
		ownBag();
		
		this.touchScreenFrame.setVisible(true);
	}
	
	private void setUpMainPanel(SelfCheckoutStation cStation) {
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
				selectedPLU = true;
				// 0 means there is no requirement to check
				openKeyboard(0);
			}
		});
		mainPanel.add(PLUTextButton);
		
		itemCata = new JButton("Tap here to browse our catalogue of favorite items!");
		itemCata.setFont(new Font("Tahoma", Font.PLAIN, 15));
		itemCata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPopup();
			}
		});
		
		itemCata.setBounds(42, 106, 468, 40);
		mainPanel.add(itemCata);
		
		memberTextButton = new JButton("Sign in with membership for rewards");
		memberTextButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		memberTextButton.setOpaque(true);
		memberTextButton.setBackground(Color.WHITE);
		memberTextButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberTextButton.setBounds(42, 183, 468, 40);
		memberTextButton.setEnabled(true);
		memberTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedMembership = false;
				// 1 means there is a requirement to meet before enter, in this case: 12 digits
				openKeyboard(1);
				// if member logged in, change text and disable, idk why it doesnt work rn
				if (selectedMembership == true) {
					memberTextButton.setText("Membership logged in!");
					memberTextButton.setEnabled(false);
				}
			}
		});
		mainPanel.add(memberTextButton);
		
		purchaseBagsButton = new JButton("Purchase Bags");
		purchaseBagsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		purchaseBagsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseBag();
			}
		});
		
		purchaseBagsButton.setBounds(42, 256, 468, 40);
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
				mainPanel.setVisible(false);
				Remove remove = new Remove(cStation);
			}
		});
		removeItemButton.setBounds(42, 329, 468, 40);
		mainPanel.add(removeItemButton);
		setUpPaymentTable();
		setUpPayment(cStation);
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
	
	// Purchase bag
	private void purchaseBag() {		
		bagsCount = 0;
		
		JPanel purBagPane = new JPanel();
		layeredPane.setLayer(purBagPane, 1);
		purBagPane.setBounds(0, 0, 1000, 900);
		purBagPane.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(purBagPane);
		purBagPane.setLayout(new GridBagLayout());
		
		// Set up gridbagconstraints
		GridBagConstraints tempC = new GridBagConstraints();
		
		// Back to main screen button
		JButton back = new JButton("Back to main screen");
		back.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back.setPreferredSize(new Dimension(200,50));
		//back.setSize(200, 50);
		back.setBounds(392, 20, 200, 50);
		// add to panel
		tempC.gridx = 1;
		tempC.gridy = 0;
		tempC.fill = GridBagConstraints.HORIZONTAL;
		tempC.anchor = GridBagConstraints.NORTH;
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bagsCount = 0;
				purBagPane.setVisible(false);
			}
		});
		purBagPane.add(back, c);
		
		// Control number of bags customer want to purchase
		
		JTextField tempBagsCount = new JTextField();
		tempBagsCount.setEditable(false);
		tempBagsCount.setFont(new Font("Tahoma", Font.PLAIN, 50));
		tempBagsCount.setHorizontalAlignment(JTextField.CENTER);
		Border tempB = BorderFactory.createLineBorder(Color.black);
		tempBagsCount.setBorder(tempB);
		tempBagsCount.setText(String.valueOf(bagsCount));
		tempBagsCount.setPreferredSize(new Dimension(200, 200));
		tempBagsCount.setBounds(442, 317, 100, 100);
		tempC.gridx = 1;
		tempC.gridy = 1;
		purBagPane.add(tempBagsCount, c);
		
		JButton minus = new JButton("-");
		minus.setFont(new Font("Tahome", Font.BOLD, 50));
		minus.setPreferredSize(new Dimension(200, 200));
		minus.setBounds(300, 317, 100, 100);
		tempC.gridx = 0;
		tempC.gridy = 1;
		tempC.anchor = GridBagConstraints.CENTER;
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//purBagPane.setVisible(false);
				bagsCount = bagsCount - 1;
				tempBagsCount.setText(String.valueOf(bagsCount));
				if (bagsCount <= 0 ) {
					minus.setEnabled(false);
				}
			}
		});
		if (bagsCount <= 0 ) {
			minus.setEnabled(false);
		}
		purBagPane.add(minus, c);
		
		JButton add = new JButton("+");
		add.setFont(new Font("Tahome", Font.BOLD, 50));
		add.setPreferredSize(new Dimension(200, 200));
		add.setBounds(584, 317, 100, 100);
		tempC.gridx = 2;
		tempC.gridy = 1;
		tempC.anchor = GridBagConstraints.CENTER;
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//purBagPane.setVisible(false);
				bagsCount = bagsCount + 1;;
				tempBagsCount.setText(String.valueOf(bagsCount));
				minus.setEnabled(true);
			}
		});
		purBagPane.add(add, c);
		
		
		JButton purchase = new JButton("Purchase Bag(s)");
		purchase.setFont(new Font("Tahome", Font.PLAIN, 15));
		purchase.setPreferredSize(new Dimension(200, 50));
		purchase.setBounds(392, 500, 200, 50);
		tempC.gridx = 1;
		tempC.gridy = 2;
		tempC.anchor = GridBagConstraints.CENTER;
		purchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add i bags to payment summary
				purBagPane.setVisible(false);
				bagsValue = bagsCount * 0.1;
				System.out.println(bagsCount);
				System.out.println(bagsValue);
//				List<List<String>> temp = new 
//				
//				data[dataI][0] = "Bag(s)";
//				data[dataI][1] = String.valueOf(bagsCount);
//				data[dataI][2] = String.valueOf(bagsValue);
				//setUpPaymentTable();
				DecimalFormat tempValue = new DecimalFormat("0.00");
				
				paymentTableModel.addRow(new Object[] {"Bag(s) @$0.10", String.valueOf(bagsCount), "$"+String.valueOf(tempValue.format(bagsValue))});
			}
		});
		purBagPane.add(purchase, c);
	}
	
	// Catalogue popup *Alvin
	private void cataPopup() {
		// Set up catalogue panel, my idea is to have a navigation bar on top of the panel and the rest will be top selling items
		// because of limited time, i think the way we should implement is to use this as top 9 most popular items in last week
		JPanel cataPanel = new JPanel();
		layeredPane.setLayer(cataPanel, 1);
		cataPanel.setBounds(0, 0, 1000, 900);
		cataPanel.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(cataPanel);
		cataPanel.setLayout(new GridBagLayout());
		
		// Set up gridbagconstraints
		GridBagConstraints tempC = new GridBagConstraints();
		
		// Back to main screen button
		JButton back = new JButton("Back to main screen");
		back.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back.setPreferredSize(new Dimension(200,50));
		//back.setSize(200, 50);
		back.setBounds(392, 20, 200, 50);
		// add to panel
		tempC.gridx = 1;
		tempC.gridy = 0;
		tempC.fill = GridBagConstraints.HORIZONTAL;
		tempC.anchor = GridBagConstraints.NORTH;
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(back, c);
		
		// Set up items, inside each button listerner should do sth to add to the summary
		
		// Item 1
		JButton itemCata1 = new JButton();
		Image itemCataPic1 = new ImageIcon(this.getClass().getResource("/beer.png")).getImage();
		itemCata1.setBackground(Color.DARK_GRAY);
		itemCata1.setIcon(new ImageIcon(itemCataPic1));
		itemCata1.setPreferredSize(new Dimension(200,200));
		itemCata1.setBounds(42, 100, 150, 150);
		// add to panel
		tempC.gridx = 0;
		tempC.gridy = 1;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata1, c);
		
		// Item 2
		JButton itemCata2 = new JButton();
		Image itemCataPic2 = new ImageIcon(this.getClass().getResource("/bread.png")).getImage();
		itemCata2.setBackground(Color.DARK_GRAY);
		itemCata2.setIcon(new ImageIcon(itemCataPic2));
		itemCata2.setPreferredSize(new Dimension(200,200));
		itemCata2.setBounds(417, 100, 150, 150);
		// add to panel
		tempC.gridx = 1;
		tempC.gridy = 1;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata2, c);

		// Item 3
		JButton itemCata3 = new JButton();
		Image itemCataPic3 = new ImageIcon(this.getClass().getResource("/cereals.png")).getImage();
		itemCata3.setBackground(Color.DARK_GRAY);
		itemCata3.setIcon(new ImageIcon(itemCataPic3));
		itemCata3.setPreferredSize(new Dimension(200,200));
		itemCata3.setBounds(792, 100, 150, 150);
		// add to panel
		tempC.gridx = 2;
		tempC.gridy = 1;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata3, c);
		
		// Item 4
		JButton itemCata4 = new JButton();
		Image itemCataPic4 = new ImageIcon(this.getClass().getResource("/eggs.png")).getImage();
		itemCata4.setBackground(Color.DARK_GRAY);
		itemCata4.setIcon(new ImageIcon(itemCataPic4));
		itemCata4.setPreferredSize(new Dimension(200,200));
		itemCata4.setBounds(42, 317, 150, 150); //535
		// add to panel
		tempC.gridx = 0;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata4, c);
		
		// Item 5
		JButton itemCata5 = new JButton();
		Image itemCataPic5 = new ImageIcon(this.getClass().getResource("/milk.png")).getImage();
		itemCata5.setBackground(Color.DARK_GRAY);
		itemCata5.setIcon(new ImageIcon(itemCataPic5));
		itemCata5.setPreferredSize(new Dimension(200,200));
		itemCata5.setBounds(417, 317, 150, 150);
		// add to panel
		tempC.gridx = 1;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata5, c);
		// Item 6
		JButton itemCata6 = new JButton();
		Image itemCataPic6 = new ImageIcon(this.getClass().getResource("/products.png")).getImage(); // snacks actually
		itemCata6.setBackground(Color.DARK_GRAY);
		itemCata6.setIcon(new ImageIcon(itemCataPic6));
		itemCata6.setPreferredSize(new Dimension(200,200));
		itemCata6.setBounds(792, 317, 150, 150);
		// add to panel
		tempC.gridx = 2;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata6, c);
		
		// Item 7
		JButton itemCata7 = new JButton();
		Image itemCataPic7 = new ImageIcon(this.getClass().getResource("/soda.png")).getImage();
		itemCata7.setBackground(Color.DARK_GRAY);
		itemCata7.setIcon(new ImageIcon(itemCataPic7));
		itemCata7.setPreferredSize(new Dimension(200,200));
		itemCata7.setBounds(42, 535, 150, 150);
		// add to panel
		tempC.gridx = 0;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata7, c);
		
		// Item 8
		JButton itemCata8 = new JButton();
		Image itemCataPic8 = new ImageIcon(this.getClass().getResource("/sweets.png")).getImage();
		itemCata8.setBackground(Color.DARK_GRAY);
		itemCata8.setIcon(new ImageIcon(itemCataPic8));
		itemCata8.setPreferredSize(new Dimension(200,200));
		itemCata8.setBounds(417, 535, 150, 150);
		// add to panel
		tempC.gridx = 1;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata8, c);
		// Item 9
		JButton itemCata9 = new JButton();
		Image itemCataPic9 = new ImageIcon(this.getClass().getResource("/cheese.png")).getImage();
		itemCata9.setBackground(Color.DARK_GRAY);
		itemCata9.setIcon(new ImageIcon(itemCataPic9));
		itemCata9.setPreferredSize(new Dimension(200,200));
		itemCata9.setBounds(792, 535, 150, 150);
		// add to panel
		tempC.gridx = 2;
		tempC.gridy = 2;
		//tempC.fill = GridBagConstraints.HORIZONTAL;
		itemCata9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cataPanel.setVisible(false);
			}
		});
		cataPanel.add(itemCata9, c);
	}

	private void ownBag() {
			JPanel ownBag = new JPanel();
			layeredPane.setLayer(ownBag, 1);
			ownBag.setBounds(0, 0, 984, 785);
			ownBag.setBackground(Color.LIGHT_GRAY);
			layeredPane.add(ownBag);
			ownBag.setLayout(new GridBagLayout());
			
			JTextField haveBag = new JTextField("Do you bring your own bag(s) today?");
			haveBag.setFont(new Font("Arial", Font.PLAIN, 40));
			haveBag.setEditable(false);
			haveBag.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			haveBag.setBackground(Color.LIGHT_GRAY);
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
		
		checkoutController.addOwnBags();
		// wait for attendant to confirm and then set visible to false, rn i'm changing it right away
		checkoutController.AttendantApproved = false;	// this will change after get acceptance from attendant
		if (checkoutController.AttendantApproved) {
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
		
	private void setUpPayment(SelfCheckoutStation cStation) {
		paymentButton = new JButton("Continue to payment >>>");
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		paymentButton.setBounds(564, 703, 393, 40);
		paymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentTableModel.addRow(new Object[] {"Item Cost", "Item Count", "Item Cost"});
				mainPanel.setVisible(false);
				PaymentScreen s = new PaymentScreen(cStation);
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
	    //String[][] data = {{"Bag(s) @$0.10", String.valueOf(bagsCount), String.valueOf(bagsValue)},{"Wagyu beef @$250.00", "0.50", "$125.00"},{"Pork chop @$4.67", "5.00", "$23.35"}};
	    
	    // column name
		String[] cName = {"Item ", "@cost per unit", "Total cost"};
		
		table = new JTable(new DefaultTableModel(data, cName));
		paymentTableModel = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);
	}
	
	private void digitsFailed() {
		tapScreenPanel = new JPanel();
		layeredPane.setLayer(tapScreenPanel, 1);
		tapScreenPanel.setBounds(0, 0, 985, 785);
		layeredPane.add(tapScreenPanel);
		tapScreenPanel.setLayout(null);
		
		screensaver = new JButton("Your number is not valid. \nTap here to try again");
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
		tapScreenPanel.add(screensaver);
	}
	
	private void setUpNumericKeyboard(int checkDigits) {		
		tempCount = 0;
		
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				tempCount += 1;
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
				System.out.println(keyboardText);
				ArrayList<Numeral> numerals = new ArrayList<Numeral>();
				
				// If it's failed, pop up a screen, otherwise selectedmembership is true, at this moment
				if (checkDigits == 1) {
					if (tempCount != 12) {
						digitsFailed();
					} else {
						selectedMembership = true;
					}
				}
				
				// Not sure about this part
				
				if(selectedPLU) {
					for (char c: keyboardText.toCharArray()) {
						numerals.add(Numeral.valueOf((byte) Character.getNumericValue(c)));
					}
					Numeral[] code = numerals.toArray(new Numeral[numerals.size()]);
					// Insert PLU method here:
					// PLUmethod(code)
				} else if(selectedMembership) {
					// insert Membership method here
					// Enter membership(keyboardText)
					//checkoutController.inputMembershipNumber();
					//membershipCardController.updateMembershipStatus();
				}
				System.out.println("PLU: " + selectedPLU);
				System.out.println("Membership: " + selectedMembership);
				keyboardText = "";
				keyboardTextField.setText(keyboardText); 
				closeKeyboard();
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
				selectedPLU = false;
				selectedMembership = false;
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
	 * @param i 
	 */
	private void openKeyboard(int i) {
		if (i == 1) {
			setUpNumericKeyboard(i);
		}
		keyboardPanel.setVisible(true);
		PLUTextButton.setEnabled(false);
		itemCata.setEnabled(false);
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
		itemCata.setEnabled(true);
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
		int[] billDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.1), new BigDecimal(0.25), new BigDecimal(100), new BigDecimal(200)};

		SelfCheckoutStation selfCheckoutStation = new SelfCheckoutStation(Currency.getInstance("CAD"), billDenominations, coinDenominations,200, 1);
		CheckoutController checkoutController = new CheckoutController(selfCheckoutStation);
		BillPaymentController billPaymentControllerStub = new BillPaymentController(selfCheckoutStation.billValidator);
        billPaymentControllerStub.setMainController(checkoutController);
        checkoutController.registerPaymentController(billPaymentControllerStub);
		SelfCheckoutStation cStation = new SelfCheckoutStation(Currency.getInstance("CAD"), billDenominations, coinDenominations, 1, 1);
		MembershipCardController membershipController = new MembershipCardController();
		CustomerGui newGui = new CustomerGui(cStation, checkoutController, membershipController); 
	}
}
