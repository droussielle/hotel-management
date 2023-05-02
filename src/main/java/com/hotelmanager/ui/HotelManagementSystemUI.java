package com.hotelmanager.ui;

import com.hotelmanager.controller.StorageController;

import javax.swing.*;

public class HotelManagementSystemUI {
    private static LoginUI loginUI = new LoginUI();
    public static void main(String[] args) {
        StorageController storage = null;
        try
        {
            storage = new StorageController();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Failed to connect to database: \n" + ex.getMessage());
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loginUI.refresh();
            }
        });
    }
}
