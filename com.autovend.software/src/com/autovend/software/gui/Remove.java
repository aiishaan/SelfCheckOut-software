package com.autovend.software.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.autovend.devices.SelfCheckoutStation;

import javax.swing.ImageIcon;

public class Remove {

    // remove handles the GUI for the remove an item for the self checkout station. 

    // all the components that are added to the screen
    private JFrame touchScreenFrame;
    private JPanel removePanel;
    private JLabel label;

    public Remove(SelfCheckoutStation cStation) {
    	
    	this.touchScreenFrame = cStation.screen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setResizable(true);
    	this.touchScreenFrame.getContentPane().setBackground(new Color(247, 247, 247));

        removePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        removePanel.setBackground(new Color(247, 247, 247));
        Border paddingBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
        removePanel.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        label = new JLabel("Removing item! Attendant is taking care of it.", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(0, 102, 204));
        removePanel.add(label, BorderLayout.CENTER);

        this.touchScreenFrame.getContentPane().add(removePanel);
        this.touchScreenFrame.setVisible(true);
        this.touchScreenFrame.setResizable(false);
    }

    public static void main(String[] args) {
    	SelfCheckoutStation cStation = new SelfCheckoutStation(null, null, null, 0, 0);
        new Remove(cStation);
    }
}

