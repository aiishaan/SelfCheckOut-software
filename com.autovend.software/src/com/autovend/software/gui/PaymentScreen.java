package com.autovend.software.gui;

//Necessary imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;


public class PaymentScreen {
	
	//PaymentScreen handles the GUI for the payment screen of the SelfCheckoutStation.
	
	//all the components that are added to the screen
	private JFrame touchScreenFrame1;
	private JLayeredPane lPane;
	private JPanel paymentPanel;
	private JLabel label;
	private JButton cash;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	
	public PaymentScreen() {
		
		touchScreenFrame1 = new JFrame();
		touchScreenFrame1.setSize(new Dimension(1000,900));
		
		lPane = new JLayeredPane();
		lPane.setSize(new Dimension(985,785));
		
		paymentPanel = new JPanel();
		paymentPanel.setSize(new Dimension(985,785));
		
		
		
	}

}