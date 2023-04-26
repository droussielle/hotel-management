package com.hotelmanager.util;

import java.sql.*;

public final class Select
{
    private Select() {}
    public static ResultSet selectAll(String sql)
    {
        ResultSet result = null;
        try (Connection conn = Connect.connectDatabase();
             Statement stmt = conn.createStatement())
        {
            result = stmt.executeQuery(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ResultSet selectAvailableRoom()
    {
        String sql = "SELECT * FROM rooms WHERE status = 0";
        ResultSet result = null;
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // loop through the result set
            result = pstmt.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
