package com.hotelmanager.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Update
{
    private Update() {}
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
}
