package com.hotelmanager.controller;

import com.hotelmanager.model.*;

import static com.hotelmanager.util.Storage.*;

public class AdminController
{

    private String name;
    Admin authentication;
    private boolean login;

    public AdminController(String name)
    {
        this.name = name;
        this.login = false;
    }

    public void bookRoom(String name, String phoneNumber, String paymentMethod,
                         String cardNumber, int roomID, int duration)
    {
        insertToReservations(name, phoneNumber, paymentMethod, cardNumber, roomID, duration);
        updateSingle("rooms", "status", 0, "id", roomID);
    }

    public void checkout(int reservationID)
    {
        Reservation reservation = getSingleReservation(reservationID);
        updateSingle("rooms", "status", 1, "id", reservation.getRoomID());
    }
}
