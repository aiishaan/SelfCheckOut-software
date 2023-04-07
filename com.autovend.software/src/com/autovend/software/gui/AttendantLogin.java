package com.autovend.software.gui;

//Necessary imports
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
	//AttendantLogin class handles the login screen for the attendant station.
	
	//all the components that are added to the screen
	private JFrame touchScreenFrame;
	private JLayeredPane logInPane;
	private JPanel logInScreen;
	private JTextField userName;
	private JPasswordField password;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	//logInSuccess panel is used for the main attendant screen.
	private JPanel logInSuccess;
	private JLabel failMessage;
	private JLabel loginText;
	
	//constructor
	public AttendantLogin() {
		//creating a new JFrame
		touchScreenFrame = new JFrame();
		//setting the size of the Frame
		touchScreenFrame.setSize(new Dimension(1000, 900));
		//creating a new layered pane
		logInPane = new JLayeredPane();
		//setting the size of the pane
		logInPane.setSize(new Dimension(1000,900));
		//creating a new JPanel for the main log in screen
		logInScreen = new JPanel();
		//setting the size of the panel
		logInScreen.setSize(new Dimension(1000,900));
		//using absolute layout
		logInScreen.setLayout(null);
		
		//creating a new JLabel
		loginText = new JLabel("Attendant Login");
		loginText.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		loginText.setBounds(376, 99, 201, 51);
		//adding the label to the logInScreen panel
		logInScreen.add(loginText);
		//creating a new text field
		userName = new JTextField();
		userName.setBounds(534, 235, 201, 51);
		userName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		//adding the text field to the logInScreen panel
		logInScreen.add(userName);
		userName.setColumns(10);
		
		//creating a new password field
		password = new JPasswordField();
		password.setBounds(534, 337, 201, 51);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		//adding the password field to the logInScreen panel
		logInScreen.add(password);
		
		//creating a new label
		userLabel = new JLabel("Enter Your Username:");
		userLabel.setBounds(135, 236, 189, 46);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		//adding the label to the logInScreen panel
		logInScreen.add(userLabel);
		
		//creating a new label
		passwordLabel = new JLabel("Enter Your Password:");
		passwordLabel.setBounds(135, 338, 189, 46);
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		//adding the label to the logInScreen panel
		logInScreen.add(passwordLabel);
		
		//creating a new button
		loginButton = new JButton("Login");
		loginButton.setBounds(534, 446, 201, 51);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		//adding an action listener to the button
		loginButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.out.println("check username and password against the Database");
				//check for login success or failure
				//false for now(testing)
				if(false) {
					//if the credentials do not match, display the try agian message
					System.out.println("Log in successful for now");
					logInScreen.setVisible(false);
					logInSuccess.setVisible(true);
					
				}
				else {
					//go to the next panel(attendant main screen)
				logInSuccess.setVisible(false);
				logInScreen.setVisible(true);
				failMessage.setVisible(true);
				}
				
			}
			
		});
		//adding the button the logInScreen panel
		logInScreen.add(loginButton);
		
		//creating a new panel(attendant main screen)
		logInSuccess = new JPanel();
		logInSuccess.setSize(new Dimension(1000,900));
		logInSuccess.setVisible(false);
		
		//creating a new label for incorrect credentials
		failMessage = new JLabel("Incorrect credentials, please try again.");
		failMessage.setFont(new Font("Times New Roman", Font.BOLD, 18));
		failMessage.setForeground(Color.RED);
		failMessage.setBounds(307, 172, 331, 38);
		//adding the label to the logInScreen panel
		logInScreen.add(failMessage);
		failMessage.setVisible(false);
		
		//setting the frame to be visible
		touchScreenFrame.setVisible(true);
		//adding the layered pane to the frame
		touchScreenFrame.getContentPane().add(logInPane);
		//adding the panels to the layered pane
		logInPane.add(logInScreen, Integer.valueOf(1));
		logInPane.add(logInSuccess, Integer.valueOf(0));
		//setting the layered pane to be visible
		logInPane.setVisible(true);

		touchScreenFrame.setResizable(false);
		touchScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	public static void main(String[]args) {
		new AttendantLogin();
	}
}
