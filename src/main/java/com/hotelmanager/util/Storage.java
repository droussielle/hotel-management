package com.hotelmanager.util;

import com.hotelmanager.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Storage
{
    private Storage()
    {
    }

    public static String fileName = "sample.db";

    /* Connect -----------------------------------------------------------------------*/
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

    /* Create -----------------------------------------------------------------------*/
    public static void createNewDatabase()
    {
        try (Connection conn = connectDatabase())
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
        try (Connection conn = connectDatabase(); Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /* Delete -----------------------------------------------------------------------*/

    public void deleteFromTable_Single(String tableName, int toDel)
    {
        String sql = "DELETE FROM ? WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setInt(2, toDel);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromTable_Single(String tableName, String toDel)
    {
        String sql = "DELETE FROM ? WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setString(2, toDel);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /* Insert -----------------------------------------------------------------------*/
    public static void insertToRooms(int id, String type, int price)
    {
        String sql = "INSERT INTO rooms (id, type, price) VALUES(?,?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            pstmt.setString(2, type);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToExtras(String type, String name, int price)
    {
        String sql = "INSERT INTO extras (type, name, price) VALUES(?,?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, type);
            pstmt.setString(2, name);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToReservations(String name, String phoneNumber, String paymentMethod,
                                            String cardNumber, int roomID, int duration)
    {
        String sql = "INSERT INTO reservations (name, phoneNumber, paymentMethod, cardNumber, roomID, duration, time)" +
                " VALUES(?,?,?,?,?,?,unixepoch())";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, paymentMethod);
            pstmt.setString(4, cardNumber);
            pstmt.setInt(5, roomID);
            pstmt.setInt(6, duration);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToReservationExtras(int reservationID, int extraID)
    {
        String sql = "INSERT INTO reservation_extras (reservationID, extraID) VALUES(?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, reservationID);
            pstmt.setInt(2, extraID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /* Select -----------------------------------------------------------------------*/
    public static List<Customer> getCustomers()
    {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                customerList.add(new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("governmentID")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public static List<Room> getAllRooms()
    {
        List<Room> roomList = new ArrayList<Room>();
        String sql = "SELECT * FROM rooms";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                roomList.add(new Room(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public static List<Room> getAvailableRooms()
    {
        List<Room> roomList = new ArrayList<Room>();
        String sql = "SELECT * FROM rooms WHERE status = 1";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                roomList.add(new Room(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public static List<Extra> getExtras()
    {
        List<Extra> extraList = new ArrayList<Extra>();
        String sql = "SELECT * FROM extras";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                extraList.add(new Extra(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("price")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return extraList;
    }

    public static ResultSet getReceipts()
    {
        String sql = "SELECT * FROM receipts";
        ResultSet result = null;
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement())
        {
            result = stmt.executeQuery(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ResultSet getReservationExtras()
    {
        String sql = "SELECT * FROM reservation_extras";
        ResultSet result = null;
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement())
        {
            result = stmt.executeQuery(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static List<Logs> getLogs()
    {
        List<Logs> logList = new ArrayList<Logs>();
        String sql = "SELECT * FROM logs";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                logList.add(new Logs(rs.getInt("id"),
                        rs.getString("details"),
                        rs.getInt("time"),
                        rs.getString("fileName")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return logList;
    }

    /* Update -----------------------------------------------------------------------*/
    public void updateSingle(String tableName, String columnName, String newValue,
                             String condition1, String condition2)
    {
        String sql = "UPDATE ? SET ? = ? WHERE ? = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName);
            pstmt.setString(3, newValue);
            pstmt.setString(4, condition1);
            pstmt.setString(5, condition2);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateSingle(String tableName, String columnName, int newValue,
                             String condition1, int condition2)
    {
        String sql = "UPDATE ? SET ? = ? WHERE ? = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName);
            pstmt.setInt(3, newValue);
            pstmt.setString(4, condition1);
            pstmt.setInt(5, condition2);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateDouble(String tableName, String columnName1, String newValue1,
                             String columnName2, String newValue2, String condition1, String condition2)
    {
        String sql = "UPDATE ? SET ? = ?, ? = ? WHERE ? = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName1);
            pstmt.setString(3, newValue1);
            pstmt.setString(4, columnName2);
            pstmt.setString(5, newValue2);
            pstmt.setString(6, condition1);
            pstmt.setString(7, condition2);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateDouble(String tableName, String columnName1, int newValue1,
                             String columnName2, int newValue2, String condition1, int condition2)
    {
        String sql = "UPDATE ? SET ? = ?, ? = ? WHERE ? = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, tableName);
            pstmt.setString(2, columnName1);
            pstmt.setInt(3, newValue1);
            pstmt.setString(4, columnName2);
            pstmt.setInt(5, newValue2);
            pstmt.setString(6, condition1);
            pstmt.setInt(7, condition2);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
