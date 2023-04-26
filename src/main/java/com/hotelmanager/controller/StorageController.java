package com.hotelmanager.controller;

import com.hotelmanager.model.Customer;
import com.hotelmanager.model.Extra;
import com.hotelmanager.model.Receipt;
import com.hotelmanager.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class StorageController
{
//    public static List<Customer> getCustomer() throws SQLException
//    {
//        List<Customer> customerList = new ArrayList<Customer>();
//        try (ResultSet rs = com.hotelmanager.util.Storage.getCustomers())
//        {
//            while (rs.next())
//            {
//                customerList.add(new Customer(rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("phoneNumber"),
//                        rs.getString("governmentID")));
//            }
//        } catch (SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return customerList;
//    }

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
