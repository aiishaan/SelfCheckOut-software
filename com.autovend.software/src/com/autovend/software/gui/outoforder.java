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

public class outoforder {

    // PaymentScreen handles the GUI for the payment screen of the SelfCheckoutStation.

    // all the components that are added to the screen
    private JFrame touchScreenFrame1;
    private JPanel order;
    private JLabel label;

    public outoforder() {

        touchScreenFrame1 = new JFrame("Out of Order!");
        touchScreenFrame1.setSize(new Dimension(1000, 900));
        touchScreenFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        touchScreenFrame1.getContentPane().setBackground(new Color(247, 247, 247));

        order = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        order.setBackground(new Color(247, 247, 247));
        Border paddingBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
        order.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        label = new JLabel("System is out of order! ", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(0, 102, 204));
        order.add(label, BorderLayout.CENTER);

        touchScreenFrame1.getContentPane().add(order);
        touchScreenFrame1.setVisible(true);
        touchScreenFrame1.setResizable(false);
    }

    public static void main(String[] args) {
        new outoforder();
    }
}