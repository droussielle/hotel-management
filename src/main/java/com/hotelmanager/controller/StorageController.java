package com.hotelmanager.controller;

import com.hotelmanager.model.Extra;
import com.hotelmanager.model.Reservation;
import com.hotelmanager.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hotelmanager.util.Storage.*;

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

    public static List<Reservation> getReceipts()
    {
        List<Reservation> receiptList = new ArrayList<Reservation>();
        return receiptList;
    }

    public void deleteCustomer(int phoneNumber)
    {
        String sql = "DELETE FROM customers WHERE phoneNumber = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, phoneNumber);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
