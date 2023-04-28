package com.hotelmanager.ui;

import javax.swing.SwingUtilities;

public class HotelManagementSystemUI {
    private static LoginUI loginUI = new LoginUI();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loginUI.refresh();
            }
        });
    }
}
