package com.autovend.software.gui;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SupervisionStation;
import com.autovend.software.controllers.CheckoutController;
import com.autovend.software.controllers.MembershipCardController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class AttendantMain {

    JFrame touchScreenFrame;
    JLabel titleText;
    JPanel mainPanel;
    JScrollPane stationScrollPane;
    JButton logoutButton;
    JPanel stationListPane;
    
    CheckoutController checkoutController;
	MembershipCardController membershipController;

    public AttendantMain(SupervisionStation attendantStation){

        this.touchScreenFrame = attendantStation.screen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setResizable(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        this.touchScreenFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        titleText = new JLabel("Attendant Terminal");
        titleText.setFont(new Font("Tahoma", Font.BOLD, 20));
        logoutButton = new JButton("Logout");

        logoutButton.addActionListener(actionEvent -> logoutButtonPressed(attendantStation));

        stationListPane = new JPanel();
        stationListPane.setLayout(new GridLayout(0, 1, 0, 30));
        List<SelfCheckoutStation> supervisedStations = attendantStation.supervisedStations();
        for (int i = 0; i < supervisedStations.size(); i++) {
            SelfCheckoutStation checkoutStation = supervisedStations.get(i);
            
            CustomerGui tempCGui = new CustomerGui(checkoutStation, checkoutController, membershipController, i+1);
            tempCGui.touchScreenFrame.setVisible(false);
            
            StationStatusBar tempBar = new StationStatusBar(checkoutStation, i+1, tempCGui);
            tempBar.setPreferredSize(new Dimension(980, 100));
            stationListPane.add(tempBar);
        }

        stationScrollPane = new JScrollPane(stationListPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(titleText);
        mainPanel.add(stationScrollPane);
        mainPanel.add(logoutButton);
        stationScrollPane.revalidate();

        this.touchScreenFrame.setVisible(true);
    }

    public void logoutButtonPressed(SupervisionStation attendantStation) {
        //logout action
    	mainPanel.setVisible(false);
    	AttendantLogin logInScreen = new AttendantLogin(attendantStation);
    }

    static class StationStatusBar extends JPanel {

        SelfCheckoutStation selfCheckoutStation;
        JLabel stationTitle;
        JButton disableButton;
        JLabel warningField;
        JButton weightDiscrepancyButton;
        JButton removeApproveButton;
        JButton ownBag;

        public StationStatusBar(SelfCheckoutStation checkoutStationIn, int ID, CustomerGui tempC) {
            this.selfCheckoutStation = checkoutStationIn;
            this.setBackground(Color.LIGHT_GRAY);
            this.setSize(980,100);

            this.setBorder(new LineBorder(Color.BLACK));
            this.setLayout(null);


            stationTitle = new JLabel(String.format(" Station %d", ID));
            disableButton = new JButton("Enable Station");
            warningField = new JLabel("This station can run normally"); //might replace with a JList?
            weightDiscrepancyButton = new JButton("Weight discrepancy");
            removeApproveButton = new JButton("Removal request");
            ownBag = new JButton("Own bag");

            disableButton.addActionListener(actionEvent -> disableButtonPressed(tempC));
            weightDiscrepancyButton.addActionListener(actionEvent -> weightDiscrepancyButtonPressed());
            removeApproveButton.addActionListener(actionEvent -> removeButtonPressed());
//            if (tempC.oB == true) {
//            	ownBagTriggered(tempC.oB);
//            }
            ownBag.addActionListener(actionEvent -> ownBagPressed(tempC));

            stationTitle.setBounds(0, 0, 100, 100);
            disableButton.setBounds(100, 0, 200, 100);
            warningField.setBounds(300, 0, 300, 100);
            weightDiscrepancyButton.setBounds(800, 0, 100, 100);
            removeApproveButton.setBounds(900, 0, 100, 100);
            ownBag.setBounds(700, 0, 100, 100);

            warningField.setHorizontalTextPosition(SwingConstants.CENTER);
            stationTitle.setFont(new Font("Tahoma", Font.BOLD, 13));

            this.add(stationTitle);
            this.add(disableButton);
            this.add(warningField);
            this.add(weightDiscrepancyButton);
            this.add(removeApproveButton);
            this.add(ownBag);

            stationTitle.setVisible(true);
            disableButton.setVisible(true);
            warningField.setVisible(true);
            weightDiscrepancyButton.setVisible(false);
            removeApproveButton.setVisible(false);
            ownBag.setVisible(false);
            this.setVisible(true);
        }

        public void disableButtonPressed(CustomerGui tempC) {
            String buttonText = disableButton.getText();
            if(buttonText.equals("Disable Station")) {
                //code to disable station
                disableButton.setText("Enable Station");
                warningField.setText("This station can run normally");
                tempC.touchScreenFrame.setVisible(false);
            } else {
                //code to enable station
                disableButton.setText("Disable Station");
                warningField.setText("Station is running mormally");
                tempC.touchScreenFrame.setVisible(true);
                ownBagTriggered(tempC.oB);
            }
        }

        public void ownBagTriggered(boolean c) {
        	ownBag.setVisible(true);
        }
        
        public void ownBagPressed(CustomerGui tempC) {
        	tempC.oB = false;
        	tempC.ownBagAdded.setVisible(false);
        	ownBag.setVisible(false);
        }
        
        //has no trigger atm
        public void weightDiscrepancyEventTriggered() {
            weightDiscrepancyButton.setVisible(true);
        }

        public void weightDiscrepancyButtonPressed() {
            //code to end weight discrepancy event at station
            weightDiscrepancyButton.setVisible(false);
        }

        //has no trigger atm
        public void removalEventTriggered() {
            removeApproveButton.setVisible(true);
        }

        public void removeButtonPressed() {
            //code to approve removal at station
            removeApproveButton.setVisible(false);
        }

        //these methods have no triggers atm
        public void lowInkEventTriggered() {
            warningField.setText("Low ink at station");
        }

        public void lowInkEventEnded() {
            warningField.setText("Station running normally");
        }

        public void lowPaperEventTriggered() {
            warningField.setText("Low paper at station");
        }

        public void lowPaperEventEnded() {
            warningField.setText("Station running normally");
        }

    }

    public static void main(String[] args) {
        SupervisionStation attendantStation = new SupervisionStation();
        //add a bunch of checkout stations to test scrolling
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        attendantStation.add(new SelfCheckoutStation(Currency.getInstance("CAD"), new int[]{1,24}, new BigDecimal[]{BigDecimal.ONE}, 1, 1));
        AttendantMain attendantGUI = new AttendantMain(attendantStation);
    }

}
