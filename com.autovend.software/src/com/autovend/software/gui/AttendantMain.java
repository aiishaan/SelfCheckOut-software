package com.autovend.software.gui;

import com.autovend.devices.TouchScreen;
import com.autovend.software.controllers.CheckoutController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        stationListPane.setLayout(new BoxLayout(stationListPane, BoxLayout.PAGE_AXIS));
        for(CheckoutController checkoutController : stationList) {
            StationStatusBar tempBar = new StationStatusBar(checkoutController);
            stationListPane.add(tempBar);
            stationListPane.add(Box.createVerticalGlue());
        }

        stationScrollPane = new JScrollPane(stationListPane);
        stationScrollPane.setPreferredSize(new Dimension(900,800));

        mainPanel.add(stationScrollPane);
        mainPanel.add(logoutButton);

        this.touchScreenFrame.setVisible(true);
    }

    class StationStatusBar extends JPanel {

        CheckoutController checkoutController;
        JLabel stationTitle;
        JButton disableButton;
        JLabel warningField;
        JButton weightDiscrepancyButton;
        JButton removeApproveButton;

        public StationStatusBar(CheckoutController checkoutControllerIn) {
            this.checkoutController = checkoutControllerIn;
//            this.setSize(950,100);
            this.setBorder(new LineBorder(Color.BLACK));
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            stationTitle = new JLabel(String.format("Station %d", checkoutController.getID()));
            disableButton = new JButton("Disable Station");
            warningField = new JLabel();
            weightDiscrepancyButton = new JButton("Weight discrepancy detected.\n Approve?");
            removeApproveButton = new JButton("Approve requested removal");

            disableButton.addActionListener(actionEvent -> disableButtonPressed());


            this.add(stationTitle);
            this.add(disableButton);
            this.add(warningField);
            this.add(weightDiscrepancyButton);
            this.add(removeApproveButton);

            stationTitle.setVisible(true);
            disableButton.setVisible(true);
            warningField.setVisible(true);
            weightDiscrepancyButton.setVisible(true);
            removeApproveButton.setVisible(true);

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
    }

    public static void main(String[] args) {
        TouchScreen attendantScreen = new TouchScreen();
        ArrayList<CheckoutController> stationList = new ArrayList<>(Arrays.asList(new CheckoutController(), new CheckoutController()));
        AttendantMain attendantGUI = new AttendantMain(attendantScreen, stationList);
    }

}
