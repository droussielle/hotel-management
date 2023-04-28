package com.hotelmanager.model;

public class Admin
{
    private String username = "admin";
    private String password = "admin";
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void changePassword(String password)
    {
        this.password = password;
    }

    public boolean checkLogin(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }
}
