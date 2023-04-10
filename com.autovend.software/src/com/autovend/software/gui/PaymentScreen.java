package com.autovend.software.gui;

//Necessary imports
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SupervisionStation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;


public class PaymentScreen {
	
	//PaymentScreen handles the GUI for the payment screen of the SelfCheckoutStation.
	
	//all the components that are added to the screen
	private JFrame touchScreenFrame;
	private JPanel paymentPanel;
	private JLabel label;
	private JLabel totalDue;
	private JLabel totalDueVal;
	private JLabel msg;
	private JButton cash;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton pay;
	
	public PaymentScreen(SelfCheckoutStation cStation) {
		
		//creating a new JFrame
		this.touchScreenFrame = cStation.screen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setResizable(true);
		
   
		
		
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
				cash.setVisible(false);
				credit.setVisible(false);
				debit.setVisible(false);
				giftCard.setVisible(false);
				label.setVisible(false);
				totalDue = new JLabel("Total Due :");
				totalDue.setFont(new Font("Times New Roman", Font.BOLD, 18));
				totalDue.setBounds(368, 200, 349, 54);
				//get the due from software.
				BigDecimal d = new BigDecimal(250.67);
				String due = d.toPlainString();
				totalDueVal = new JLabel(due);
				totalDueVal.setFont(new Font("Times New Roman", Font.BOLD, 18));
				totalDueVal.setBounds(500, 200, 349, 54);
				paymentPanel.add(totalDue);
				paymentPanel.add(totalDueVal);
				
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
		
		
		this.touchScreenFrame.getContentPane().add(paymentPanel);
		this.touchScreenFrame.setVisible(true);

	

		
		
		
	}
	
	

	public static void main(String[]args) {
		SelfCheckoutStation cStation = new SelfCheckoutStation(null, null, null, 0, 0);
		new PaymentScreen(cStation);
	}
}