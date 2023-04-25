package com.hotelmanager.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.hotelmanager.data.Authenticate;

//import javax.swing.table.*;
public class LoginUI {
    private JFrame frame;
    private Authenticate auth;

    // private JTable table;
    // private DefaultTableModel model;
    public LoginUI() {
        auth = new Authenticate();
        createLoginUI();
    }

    private void createLoginUI() {
        frame = new JFrame("Hotel Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.CENTER;
        panel.add(new JLabel("User Name: "), constraints);

        JTextField userNameField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Password: "), constraints);

        JTextField passwordField = new JPasswordField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        JButton addButton_Login = new JButton("Log In");
        addButton_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameField.getText();
                String password = new String(passwordField.getText());
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
        panel.add(addButton_Login, constraints);

        // JScrollPane scrollPane = new JScrollPane(table);

        frame.add(panel, BorderLayout.CENTER);
        // frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void refresh() {
        // model.setRowCount(0);
        // HotelManagementSystem.getRooms().forEach(room -> {
        // model.addRow(new Object[]{room.getRoomNumber(), room.getRoomType(),
        // room.getPrice()});
        // });
    }
}
