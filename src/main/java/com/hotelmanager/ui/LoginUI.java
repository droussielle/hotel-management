package com.hotelmanager.ui;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import com.hotelmanager.model.Admin;

//import javax.swing.table.*;
public class LoginUI {
    private JFrame frame;
    private Admin auth;

    // private JTable table;
    // private DefaultTableModel model;
    public LoginUI() {
        auth = new Admin();
        createLoginUI();
    }

    private void createLoginUI() {
        frame = new JFrame("Hotel Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints_Main = new GridBagConstraints();
        constraints_Main.fill = GridBagConstraints.CENTER;
        constraints_Main.gridx = 0;
        constraints_Main.gridy = 0;

        ClassLoader cldr = Thread.currentThread().getContextClassLoader();
        URL hotel_image = cldr.getResource("hotel_image.png");
        JPanel panel_image = new JPanel(new BorderLayout());
        ImageIcon hotelIcon = new ImageIcon(hotel_image);
        Image image = hotelIcon.getImage().getScaledInstance(170, 100, Image.SCALE_SMOOTH);
        Icon newIcon = new ImageIcon(image);
        JLabel hotLabel = new JLabel(newIcon);
        // panel_image.setSize(150, 50);
        panel_image.add(hotLabel, BorderLayout.CENTER);
        panel.add(panel_image, constraints_Main);

        constraints_Main.gridx = 0;
        constraints_Main.gridy = 1;
        panel.add(new Label(null, 0), constraints_Main);

        constraints_Main.gridx = 0;
        constraints_Main.gridy = 2;
        JPanel panel_Main = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel_Main.add(new JLabel("User Name: "), constraints);

        JTextField userNameField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel_Main.add(userNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel_Main.add(new JLabel("Password: "), constraints);

        JPasswordField passwordField = new JPasswordField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel_Main.add(passwordField, constraints);

        JButton addButton_Login = new JButton("Log In");
        addButton_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameField.getText();
                String password = new String(passwordField.getPassword());
                boolean isAuthenticated = auth.checkLogin(username, password);
                if (!isAuthenticated) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    return;
                }
                Home HomeUI = new Home();
                frame.setVisible(false);
                HomeUI.refresh();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 10;
        panel_Main.add(addButton_Login, constraints);
        panel.add(panel_Main, constraints_Main);

        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        userNameField.setToolTipText("Enter your username");
        passwordField.setToolTipText("Enter your password");
        frame.getRootPane().setDefaultButton(addButton_Login);
    }

    public void refresh() {
        // model.setRowCount(0);
        // HotelManagementSystem.getRooms().forEach(room -> {
        // model.addRow(new Object[]{room.getRoomNumber(), room.getRoomType(),
        // room.getPrice()});
        // });
    }
}
