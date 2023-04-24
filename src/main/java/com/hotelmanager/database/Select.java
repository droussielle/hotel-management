package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select
{
    public void selectAll(String sql)
    {
        try (Connection conn = Connect.connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            // loop through the result set
            while (rs.next())
            {
                System.out.println(rs.getString("name") + "\t" + rs.getString("phoneNumber"));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getCapacityGreaterThan(double capacity, String sql)
    {
        try (Connection conn = Connect.connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            // set the value
            pstmt.setDouble(1, capacity);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next())
            {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("name") + "\t" + rs.getDouble("capacity"));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        Select app = new Select();
        String sql = "SELECT * FROM customers";
        app.selectAll(sql);
    }

}
