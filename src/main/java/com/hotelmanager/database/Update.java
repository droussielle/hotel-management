package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update
{
    public void update(String name, String phoneNumber)
    {
        String sql = "UPDATE customers SET phoneNumber = ? " + "WHERE name = ?";

        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, name);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {

        Update app = new Update();
        // update the warehouse with id 3
        app.update("daniel", "01234567893287");
    }
}
