package com.hotelmanager.UI;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class MenuUI {
    private JPanel menuUIpanel;

    public MenuUI() {
        createMenuUI();
    }

    private void createMenuUI() {
        menuUIpanel = new JPanel();
        menuUIpanel.setPreferredSize(new Dimension(1024, 70));
        menuUIpanel.setBackground(Color.LIGHT_GRAY);
        menuUIpanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK),
                new EmptyBorder(10, 10, 10, 10)));
        menuUIpanel.setLayout(new GridLayout(0, 7));
        ImageIcon bookButton_icon = new ImageIcon("src/main/resources/book_icon.png");
        JButton bookButton = new JButton("Book", bookButton_icon);
        // bookButton.setBorderPainted(false);
        bookButton.setContentAreaFilled(false);
        bookButton.setFocusPainted(false);

        ImageIcon editButton_icon = new ImageIcon("src/main/resources/edit_icon.png");
        JButton ediButton = new JButton("Edit", editButton_icon);
        ediButton.setContentAreaFilled(false);
        ediButton.setFocusPainted(false);

        ImageIcon checkoutButton_icon = new ImageIcon("src/main/resources/checkout_icon.png");
        JButton checkoutButton = new JButton("Checkout", checkoutButton_icon);
        checkoutButton.setContentAreaFilled(false);
        checkoutButton.setFocusPainted(false);

        ImageIcon searchButton_icon = new ImageIcon("src/main/resources/search_icon.png");
        JButton searchButton = new JButton("Search", searchButton_icon);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);

        ImageIcon propertyButton_icon = new ImageIcon("src/main/resources/property_icon.png");
        JButton propertybButton = new JButton("Property", propertyButton_icon);
        propertybButton.setContentAreaFilled(false);
        propertybButton.setFocusPainted(false);

        ImageIcon logButton_icon = new ImageIcon("src/main/resources/log_icon.png");
        JButton logButton = new JButton("Log", logButton_icon);
        logButton.setContentAreaFilled(false);
        logButton.setFocusPainted(false);

        menuUIpanel.add(bookButton);
        menuUIpanel.add(ediButton);
        menuUIpanel.add(checkoutButton);
        menuUIpanel.add(searchButton);
        menuUIpanel.add(new JLabel());
        menuUIpanel.add(propertybButton);
        menuUIpanel.add(logButton);
    }

    public JPanel getMenuUI() {
        return menuUIpanel;
    }
}
