package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    /* Connect to a sample database */
    public static Connection connectDatabase()
    {
        String url = "jdbc:sqlite:src/main/data/sample.db";
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

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Connection conn = connectDatabase();
        detachDatabase(conn);
    }
}
