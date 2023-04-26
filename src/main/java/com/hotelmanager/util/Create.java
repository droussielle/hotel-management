package com.hotelmanager.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public final class Create
{
    private Create()
    {
    }

    public static void createNewDatabase()
    {
        try (Connection conn = Connect.connectDatabase())
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String sql)
    {
        // SQLite connection string
        try (Connection conn = Connect.connectDatabase(); Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
