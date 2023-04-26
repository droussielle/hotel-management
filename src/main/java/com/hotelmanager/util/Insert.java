package com.hotelmanager.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Insert
{
    private Insert() {}
    public static void insertToRooms(Integer id, String type, Integer price)
    {
        String sql = "INSERT INTO rooms (id, type, price) VALUES(?,?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            pstmt.setString(2, type);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToCustomers(String name, String phoneNumber, String governmentID)
    {
        String sql = "INSERT INTO customers (name, phoneNumber, governmentID) VALUES(?,?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, governmentID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToExtra(String type, String name, Integer price)
    {
        String sql = "INSERT INTO extras (type, name, price) VALUES(?,?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, type);
            pstmt.setString(2, type);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToReceipts(Integer customerID, Integer roomID)
    {
        String sql = "INSERT INTO receipts (customerID, roomID) VALUES(?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, roomID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToReceiptsExtra(Integer receiptID, Integer extraID)
    {
        String sql = "INSERT INTO rooms (receiptID, extraID) VALUES(?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, receiptID);
            pstmt.setInt(2, extraID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
