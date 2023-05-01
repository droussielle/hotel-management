package com.hotelmanager.controller;

import com.hotelmanager.model.*;

import static com.hotelmanager.util.Storage.*;

public class AdminController
{
    public AdminController()
    {
    }

    public void bookRoom(String name, String phoneNumber, String paymentMethod,
                         String cardNumber, int roomID, int duration) throws Exception
    {
        insertToReservations(name, phoneNumber, paymentMethod, cardNumber, roomID, duration);
        updateRoomsStatus(roomID, 0);
    }

    public void checkout(int reservationID) throws Exception
    {
        Reservation reservation = getSingleReservation(reservationID);
        updateRoomsStatus(reservation.getRoomID(), 1);
    }
}
