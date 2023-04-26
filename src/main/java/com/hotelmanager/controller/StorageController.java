package com.hotelmanager.controller;

import com.hotelmanager.model.*;

import java.util.ArrayList;
import java.util.List;

public class StorageController
{
    public static List<Customer> getCustomer()
    {
        List<Customer> customerList = new ArrayList<Customer>();
        return customerList;
    }

    public static List<Room> getAvailableRooms()
    {
        List<Room> availableRooms = new ArrayList<Room>();
        return availableRooms;
    }

    public static List<Extra> getExtras()
    {
        List<Extra> extraList = new ArrayList<Extra>();
        return extraList;
    }

    public static List<Receipt> getReceipts()
    {
        List<Receipt> receiptList = new ArrayList<Receipt>();
        return receiptList;
    }
}
