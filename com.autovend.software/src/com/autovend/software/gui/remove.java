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
import javax.swing.ImageIcon;

public class remove {

    // remove handles the GUI for the remove an item for the self checkout station. 

    // all the components that are added to the screen
    private JFrame touchScreenFrame1;
    private JPanel removePanel;
    private JLabel label;

    public remove() {

        touchScreenFrame1 = new JFrame("Remove Item");
        touchScreenFrame1.setSize(new Dimension(1000, 900));
        touchScreenFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        touchScreenFrame1.getContentPane().setBackground(new Color(247, 247, 247));

        removePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        removePanel.setBackground(new Color(247, 247, 247));
        Border paddingBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
        removePanel.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        label = new JLabel("Removing item! Attendant is taking care of it.", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(0, 102, 204));
        removePanel.add(label, BorderLayout.CENTER);

        touchScreenFrame1.getContentPane().add(removePanel);
        touchScreenFrame1.setVisible(true);
        touchScreenFrame1.setResizable(false);
    }

    public static void main(String[] args) {
        new remove();
    }
}

