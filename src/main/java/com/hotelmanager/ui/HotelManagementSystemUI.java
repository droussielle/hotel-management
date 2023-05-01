package com.hotelmanager.ui;

import com.hotelmanager.controller.StorageController;

import javax.swing.SwingUtilities;

public class HotelManagementSystemUI {
    private static LoginUI loginUI = new LoginUI();
    public static void main(String[] args) {
        StorageController storage = new StorageController();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loginUI.refresh();
            }
        });
    }
}
