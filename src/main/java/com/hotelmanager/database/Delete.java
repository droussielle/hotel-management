package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete
{
    public void delete(int phoneNumber)
    {
        String sql = "DELETE FROM customers WHERE phoneNumber = ?";

        try (Connection conn = Connect.connectDatabase();
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

    public void delete(String name)
    {
        String sql = "DELETE FROM customers WHERE name = ?";

        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, name);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        Delete app = new Delete();
        // delete the row with id 3
        app.delete("pepega clap");
    }
}
