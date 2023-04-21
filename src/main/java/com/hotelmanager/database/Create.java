package com.hotelmanager.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create
{
    public static void createNewDatabase(String fileName)
    {

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url))
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

    public static void createNewTable(String fileName, String sql)
    {
        // SQLite connection string
        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        createNewDatabase("test.db");
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS orders (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        createNewTable("sample.db", sql);
    }
}
