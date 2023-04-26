package com.hotelmanager.controller;

import com.hotelmanager.model.*;

public class AdminController {

    private String name;
    Authenticate authentication;
    private Database database;
    private boolean login;

    public AdminController(String name) {
        this.name = name;
        this.login = false;
    }

    public void addExtra(Extra extra) {
        if (login) {
            database.addExtra(extra);
        }
    }

    public void removeExtra(Extra extra) {
        if (login) {
            database.removeExtra(extra);
        }
    }

    public void addFreeRoom(FreeRoom room) {
        if (login) {
            database.addFreeRoom(room);
        }
    }

    public void removeRoom(FreeRoom room) {
        if (login) {
            for (FreeRoom i : database.getFreeRoomList()) {
                database.removeFreeRoom(i.getRoom());
            }
        }
    }

    public void addBookedRoom(BookedRoom room) {
        if (login) {
            database.addBookedRoom(room);
        }
    }

    public void removeBookedRoom(BookedRoom room) {
        if (login) {
            for (BookedRoom i : database.getBookedRoomList()) {
                database.removeBookedRoom(i.getRoom());
            }
        }
    }

    public void addCustomer(Customer customer) {
        if (login) {
            database.addCustomer(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        if (login) {
            database.removeCustomer(customer);
        }
    }

}
