package com.autovend.software.gui;

//Necessary imports
import javax.swing.JFrame;


import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPasswordField;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

import com.autovend.software.controllers.AttendantController;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SupervisionStation;
import com.autovend.devices.Keyboard;




public class AttendantLogin {
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
	private JLabel failMessage;
	private JLabel loginText;
	private Keyboard keyboard;
	
	private AttendantController attendant;

	
	
	//constructor
	public AttendantLogin(SupervisionStation aStation) {
		
		
		
		
		
		//creating a new JFrame
		this.touchScreenFrame = aStation.screen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setResizable(true);
		//creating a new layered pane
		logInPane = new JLayeredPane();
		//setting the size of the pane
		logInPane.setSize(new Dimension(1000,900));
		//creating a new JPanel for the main log in screen
		logInScreen = new JPanel();
		//setting the size of the panel
		logInScreen.setSize(new Dimension(985, 785));
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
		
		userName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("clicked username field");
            }
        });
		
		//adding the text field to the logInScreen panel
		logInScreen.add(userName);
		userName.setColumns(10);
		
		//creating a new password field
		password = new JPasswordField();
		password.setBounds(534, 337, 201, 51);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		password.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	 System.out.println("clicked password field");
               
            }
        });
		
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
		
		// Sign in infos: 				*wrong rn
		// username: James
		// password: 4268
		
		
		//creating a new button
		loginButton = new JButton("Login");
		loginButton.setBounds(534, 446, 201, 51);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		//adding an action listener to the button
		loginButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//check for login success or failure
				
				attendant = new AttendantController(userName.getText(),password.getText());
				
				if(attendant.AttendantList.containsValue(password.getText()) && attendant.AttendantList.containsKey(userName.getText())) {
					//if the credentials match,go to the next panel(attendant main screen)do not match, display the try again message
					
					logInScreen.setVisible(false);
					
					AttendantMain attendantGui = new AttendantMain(aStation);
					
					
				}
				else {
					
				//display the try again message
				logInScreen.setVisible(true);
				failMessage.setVisible(true);
				}
				
			}
			
		});
		//adding the button the logInScreen panel
		logInScreen.add(loginButton);
		
		
		
		
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
		//setting the layered pane to be visible
		logInPane.setVisible(true);
		

	}
      
        
	
	public static void main(String[]args) {
		SupervisionStation aStation = new SupervisionStation();
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		aStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
		 new AttendantLogin(aStation);
	}

}