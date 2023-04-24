package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert
{
    /*
     * Insert a new row into the table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name, String phoneNumber)
    {
        String sql = "INSERT INTO customers(name,phoneNumber) VALUES(?,?)";
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Insert app = new Insert();
        // insert three new rows
        app.insert("pepega clap", "019291982");
    }
}
