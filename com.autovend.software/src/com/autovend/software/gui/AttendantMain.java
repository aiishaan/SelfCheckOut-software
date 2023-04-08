package com.autovend.software.gui;

import com.autovend.devices.TouchScreen;
import com.autovend.software.controllers.CheckoutController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AttendantMain {

    JFrame touchScreenFrame;
    JLabel titleText;
    JPanel mainPanel;
    JScrollPane stationScrollPane;
    JButton logoutButton;
    JPanel stationListPane;

    public AttendantMain(TouchScreen attendantScreen, ArrayList<CheckoutController> stationList){

        this.touchScreenFrame = attendantScreen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setResizable(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        this.touchScreenFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        titleText = new JLabel("Attendant Terminal");
        logoutButton = new JButton("Logout");

        stationListPane = new JPanel();
        stationListPane.setLayout(new GridLayout(0, 1, 0, 30));
        for (CheckoutController checkoutController : stationList) {
            StationStatusBar tempBar = new StationStatusBar(checkoutController);
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

    static class StationStatusBar extends JPanel {

        CheckoutController checkoutController;
        JLabel stationTitle;
        JButton disableButton;
        JLabel warningField;
        JButton weightDiscrepancyButton;
        JButton removeApproveButton;

        public StationStatusBar(CheckoutController checkoutControllerIn) {
            this.checkoutController = checkoutControllerIn;
            this.setSize(980,100);

            this.setBorder(new LineBorder(Color.BLACK));
            this.setLayout(null);


            stationTitle = new JLabel(String.format("Station %d", checkoutController.getID()));
            disableButton = new JButton("Disable Station");
            warningField = new JLabel("Station running normally"); //might replace with a JList?
            weightDiscrepancyButton = new JButton("Weight discrepancy detected.\n Approve?");
            removeApproveButton = new JButton("Approve requested removal");

            disableButton.addActionListener(actionEvent -> disableButtonPressed());
            weightDiscrepancyButton.addActionListener(actionEvent -> weightDiscrepancyButtonPressed());
            removeApproveButton.addActionListener(actionEvent -> removeButtonPressed());

            stationTitle.setBounds(0, 0, 200, 100);
            disableButton.setBounds(200, 0, 200, 100);
            warningField.setBounds(400, 0, 200, 100);
            weightDiscrepancyButton.setBounds(600, 0, 200, 100);
            removeApproveButton.setBounds(800, 0, 200, 100);

            this.add(stationTitle);
            this.add(disableButton);
            this.add(warningField);
            this.add(weightDiscrepancyButton);
            this.add(removeApproveButton);

            stationTitle.setVisible(true);
            disableButton.setVisible(true);
            warningField.setVisible(true);
            weightDiscrepancyButton.setVisible(false);
            removeApproveButton.setVisible(false);

            this.setVisible(true);
        }

        public void disableButtonPressed() {
            String buttonText = disableButton.getText();
            if(buttonText.equals("Disable Station")) {
                //code to disable station
                disableButton.setText("Enable Station");
            } else {
                //code to enable station
                disableButton.setText("Disable Station");
            }
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
            weightDiscrepancyButton.setVisible(true);
        }

        public void removeButtonPressed() {
            //code to approve removal at station
            weightDiscrepancyButton.setVisible(false);
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
        TouchScreen attendantScreen = new TouchScreen();
        //add a bunch of checkout controllers to test scrolling
        ArrayList<CheckoutController> stationList = new ArrayList<>(Arrays.asList(new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController(), new CheckoutController()));
        AttendantMain attendantGUI = new AttendantMain(attendantScreen, stationList);
    }

}
