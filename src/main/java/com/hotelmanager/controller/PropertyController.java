package com.hotelmanager.controller;

import com.hotelmanager.model.*;
import com.hotelmanager.util.*;

import java.util.List;

import static com.hotelmanager.util.Storage.*;

public class PropertyController
{
    public Object[][] getReservationsObject()
    {
        List<Reservation> reservationList = getReservations();
        Object[][] reservationObject = new Object[reservationList.size()][9];
        for(int i = 0; i<reservationList.size(); i++)
        {
            reservationObject[i][0] = reservationList.get(i).getId();
            reservationObject[i][1] = reservationList.get(i).getName();
            reservationObject[i][2] = reservationList.get(i).getPhoneNumber();
            reservationObject[i][3] = reservationList.get(i).getPaymentMethod();
            reservationObject[i][4] = reservationList.get(i).getCardNumber();
            reservationObject[i][5] = reservationList.get(i).getRoomID();
            reservationObject[i][6] = reservationList.get(i).getDuration();
            reservationObject[i][7] = reservationList.get(i).getTimeString();
            reservationObject[i][8] = reservationList.get(i).getStatusString();
        }
        return reservationObject;
    }

    public Object[][] getAvailableRoomsObject()
    {
        List<Room> availableRooms = getAvailableRooms();
        Object[][] availableRoomsObject = new Object[availableRooms.size()][3];
        for(int i = 0; i < availableRooms.size(); i++)
        {
            availableRoomsObject[i][0] = availableRooms.get(i).getID();
            availableRoomsObject[i][1] = availableRooms.get(i).getType();
            availableRoomsObject[i][2] = availableRooms.get(i).getPrice();
        }
        return availableRoomsObject;
    }

    public Object[][] getLogsObject()
    {
        List<Logs> logsList = getLogs();
        Object[][] logsObject = new Object[logsList.size()][3];
        for(int i = 0; i<logsList.size(); i++)
        {
            logsObject[i][0] = logsList.get(i).getId();
            logsObject[i][1] = logsList.get(i).getDetails();
            logsObject[i][2] = logsList.get(i).getTimeString();
        }
        return logsObject;
    }
}
