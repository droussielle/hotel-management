package com.hotelmanager.controller;

import com.hotelmanager.model.*;

public class AdminController {

    private String name;
    Authenticate authentication;
    private boolean login;

    public AdminController(String name) {
        this.name = name;
        this.login = false;
    }
}
