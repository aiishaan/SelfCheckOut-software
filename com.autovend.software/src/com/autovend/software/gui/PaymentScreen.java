package com.autovend.software.gui;

//Necessary imports
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;


public class PaymentScreen {
	
	//PaymentScreen handles the GUI for the payment screen of the SelfCheckoutStation.
	
	//all the components that are added to the screen
	private JFrame touchScreenFrame1;
	private JPanel paymentPanel;
	private JLabel label;
	private JButton cash;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	
	public PaymentScreen() {
		
		touchScreenFrame1 = new JFrame();
		touchScreenFrame1.setSize(new Dimension(1000,900));
		
		
		
		paymentPanel = new JPanel();
		paymentPanel.setSize(new Dimension(985,785));
		paymentPanel.setLayout(null);
		
		
		label = new JLabel("Please select a payment option:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(368, 200, 349, 54);
		paymentPanel.add(label);
		
		cash = new JButton("Cash");
		cash.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cash.setBounds(405, 266, 171, 38);
		cash.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//implement the button press
				System.out.println("Paying using cash");
				
			}
			
		});
		paymentPanel.add(cash);
		
		
		
		credit = new JButton("Credit");
		credit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		credit.setBounds(405, 324, 171, 38);
		credit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//implement the button press
				System.out.println("Paying using Credit Card");
				
			}
			
		});
		paymentPanel.add(credit);
		
		
		debit = new JButton("Debit");
		debit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		debit.setBounds(405, 384, 171, 38);
		debit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//implement the button press
				System.out.println("Paying using Debit Card");
			}
			
		});
		paymentPanel.add(debit);
		
		
		giftCard = new JButton("Gift Card");
		giftCard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		giftCard.setBounds(405, 451, 171, 38);
		giftCard.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//implement the button press
				System.out.println("Paying using Gift Card");
				
			}
			
		});
		paymentPanel.add(giftCard);
		
		
		touchScreenFrame1.getContentPane().add(paymentPanel);
		touchScreenFrame1.setVisible(true);
		touchScreenFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		touchScreenFrame1.setResizable(false);

		
		
		
	}
	
	public static void main(String[]args) {
		new PaymentScreen();
	}
}