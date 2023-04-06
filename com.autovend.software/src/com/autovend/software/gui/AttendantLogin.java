package com.autovend.software.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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
import java.awt.FlowLayout;

public class AttendantLogin{
	
	private JFrame touchScreenFrame;
	private JLayeredPane logInPane;
	private JPanel logInScreen;
	private JTextField userName;
	private JPasswordField password;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JPanel test;
	private JButton bt;
	
	public AttendantLogin() {
		touchScreenFrame = new JFrame();
		touchScreenFrame.setSize(new Dimension(1000,900));
		logInPane = new JLayeredPane();
		logInPane.setSize(new Dimension(1000,900));
		logInScreen = new JPanel();
		logInScreen.setSize(new Dimension(1000,900));
		logInScreen.setLayout(null);
		
		userName = new JTextField();
		userName.setBounds(534, 235, 201, 51);
		userName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		logInScreen.add(userName);
		userName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(534, 337, 201, 51);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		logInScreen.add(password);
		
		userLabel = new JLabel("Enter Your Username:");
		userLabel.setBounds(135, 236, 189, 46);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logInScreen.add(userLabel);
		
		passwordLabel = new JLabel("Enter Your Password:");
		passwordLabel.setBounds(135, 338, 189, 46);
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logInScreen.add(passwordLabel);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(546, 471, 189, 51);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		loginButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.out.print("check username and password against the Database");
				logInScreen.setVisible(false);
				test.setVisible(true);
				
			}
			
		});
		
		logInScreen.add(loginButton);
		
		bt = new JButton("Click me");
		bt.setBounds(50, 50, 180, 50);
		test = new JPanel();
		test.setSize(new Dimension(1000,900));
		bt.setVisible(true);
		test.add(bt);
		test.setVisible(false);
		
		
		touchScreenFrame.setVisible(true);
		touchScreenFrame.getContentPane().add(logInPane);
		logInPane.add(logInScreen, Integer.valueOf(1));
		logInPane.add(test, Integer.valueOf(0));
		logInPane.setVisible(true);

		touchScreenFrame.setResizable(false);
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	public static void main(String[]args) {
		new AttendantLogin();
	}

	
}
