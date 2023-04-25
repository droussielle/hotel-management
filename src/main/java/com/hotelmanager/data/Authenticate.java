package com.hotelmanager.data;

public class Authenticate {
    private String username = "admin";
    private String password = "admin";

    public void changePassword(String password) {
        this.password = password;
    }

    public boolean checkLogin(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }
}
