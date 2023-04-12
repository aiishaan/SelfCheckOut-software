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

public class weight {

    // PaymentScreen handles the GUI for the payment screen of the SelfCheckoutStation.

    // all the components that are added to the screen
    private JFrame touchScreenFrame1;
    private JPanel weightchange;
    private JLabel label;

    public weight() {

        touchScreenFrame1 = new JFrame("Weight Discrepancy!");
        touchScreenFrame1.setSize(new Dimension(1000, 900));
        touchScreenFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        touchScreenFrame1.getContentPane().setBackground(new Color(247, 247, 247));

        weightchange = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        weightchange.setBackground(new Color(247, 247, 247));
        Border paddingBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
        weightchange.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        label = new JLabel("There is weight discrepancy! Wait for attendant approval..", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(0, 102, 204));
        weightchange.add(label, BorderLayout.CENTER);

        touchScreenFrame1.getContentPane().add(weightchange);
        touchScreenFrame1.setVisible(true);
        touchScreenFrame1.setResizable(false);
    }

    public static void main(String[] args) {
        new weight();
    }
}
