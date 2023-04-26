package com.hotelmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    private Connect()
    {
    }

    static String fileName = "hotel.db";

    public static Connection connectDatabase()
    {
        String url = "jdbc:sqlite:src/main/data/" + fileName;
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void detachDatabase(Connection conn)
    {
        try
        {
            if (conn != null)
            {
                conn.close();
                System.out.println("Successfully detached database.");
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
