package com.autovend.software.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.autovend.devices.SelfCheckoutStation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Remove {

    // remove handles the GUI for the remove an item for the self checkout station. 
	
    // all the components that are added to the screen
    JFrame touchScreenFrame;
    private JPanel removePanel;
    private JLabel label;
    private JScrollPane scrollPane;

    public Remove(SelfCheckoutStation cStation, String[] items, int count) {
    	
    	this.touchScreenFrame = cStation.screen.getFrame();
        this.touchScreenFrame.setExtendedState(JFrame.NORMAL);
        this.touchScreenFrame.setSize(1000,900);
        this.touchScreenFrame.setBounds(0, 0, 1000, 900);
        this.touchScreenFrame.setResizable(true);
    	this.touchScreenFrame.getContentPane().setBackground(new Color(247, 247, 247));
    	
        removePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        removePanel.setBounds(0, 0, 984, 785);
        removePanel.setBackground(new Color(247, 247, 247));
        Border paddingBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
        removePanel.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        label = new JLabel("Removing item! Attendant is taking care of it.", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(0, 102, 204));
        removePanel.add(label, BorderLayout.CENTER);
        
        removePanel.setVisible(false);
        checkbox(items, count);

        this.touchScreenFrame.getContentPane().add(removePanel);
        this.touchScreenFrame.setVisible(true);
        this.touchScreenFrame.setResizable(false);
    }
    
    public void checkSetup(String tmp) {
    	JCheckBox tmpC = new JCheckBox(tmp);
    	tmpC.setFont(new Font("Segoe UI", Font.PLAIN, 26));
    	scrollPane.getViewport().add(tmpC);
    }
    
	public void checkbox(String[] items, int count) {
    	JPanel checkP = new JPanel();//(new GridLayout(1, 2));
    	checkP.setBounds(0, 0, 984, 785);
    	
    	scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(300, 500));
		scrollPane.setAlignmentX(count);
		scrollPane.setBounds(0, 0, 500, 785);	//984, 785
		for (int i = 0; i < count; i++) {
			checkSetup(items[i]);
			System.out.println(items[i]);
		}
		checkP.add(scrollPane);
		
		
//		String[] tmp = new String[count];
//		for (int i = 0; i < count; i++) {
//			tmp[i] = items[i]; 
//		}
//		JList<JCheckBox> l = new JList<JCheckBox>();
//		l.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		JCheckBox tmp1 = new JCheckBox(items[0]);
//		l.add(tmp1, 0);
//		checkP.add(l);
//		checkP.add(tmp1);
		//scrollPane.add(l);
//		for (int i = 0; i <= count; i++) {
//			JCheckBox c = new JCheckBox(items[i]); 
//			l.add(c, i);
//			c.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//			// scrollPane.add(l);
//		}
//		scrollPane.add(l);
		
		JButton cf = new JButton("Remove an Item");
		cf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkP.setVisible(false);
				removePanel.setVisible(true);
			}
		});
		cf.setBounds(142, 342, 200, 100);
		checkP.add(cf);
		
		touchScreenFrame.add(checkP);
		
    }

//    public static void main(String[] args) {
//    	SelfCheckoutStation cStation = new SelfCheckoutStation(null, null, null, 0, 0);
//        new Remove(cStation);
//    }
}

