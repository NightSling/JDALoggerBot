package me.daysling.gui;

import javax.swing.*;

public class GUI extends JFrame {

    public void maine(String error){
        JPanel jPanel = new JPanel();
        JFrame frame = new JFrame("Error!");
        jPanel.add(frame);
        jPanel.setVisible(true);
        jPanel.setSize(500, 500);
        JComponent component = new JLabel(error);
        jPanel.add(component);
        try {
            wait(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}
