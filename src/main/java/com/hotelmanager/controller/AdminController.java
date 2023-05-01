package com.hotelmanager.controller;

import com.hotelmanager.model.*;

import java.util.List;

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
        updateReservationsStatus(reservationID, 1);
    }

    public int calculateTotalPrice(int reservationID) throws Exception
    {
        Reservation reservation = getSingleReservation(reservationID);
        int roomPrice = getRoomPrice(reservation.getRoomID());
        int subTotal = roomPrice * reservation.getDuration();
        List<ReservationExtras> reservationExtrasList = getReservationExtras(reservationID);
        for (ReservationExtras r : reservationExtrasList)
        {
            subTotal += r.getPrice() * r.getQuantity();
        }
        return subTotal;
    }
}
