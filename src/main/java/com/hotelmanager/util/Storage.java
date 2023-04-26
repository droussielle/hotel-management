package com.hotelmanager.util;

import com.hotelmanager.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Storage
{
    private Storage() {}
    
    public static String fileName = "hotel.db";

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

    /* Create */
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
    public void deleteCustomer(int phoneNumber)
    {
        String sql = "DELETE FROM customers WHERE phoneNumber = ?";

        try (Connection conn = connectDatabase();
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

    public void deleteRoom(int roomID)
    {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, roomID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteExtra(int extraID)
    {
        String sql = "DELETE FROM extras WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, extraID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteReceipts(int receiptID)
    {
        String sql = "DELETE FROM receipts WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, receiptID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteReceiptsExtra(int receiptExtraID)
    {
        String sql = "DELETE FROM receipts_extra WHERE receiptID = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, receiptExtraID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /* Insert -----------------------------------------------------------------------*/
    public static void insertToCustomers(String name, String phoneNumber, String governmentID)
    {
        String sql = "INSERT INTO customers (name, phoneNumber, governmentID) VALUES(?,?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, governmentID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
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
        String sql = "INSERT INTO extras (id, type, price) VALUES(?,?,?)";
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

    public static void insertToReceipts(int customerID, int roomID)
    {
        String sql = "INSERT INTO receipts (customerID, roomID) VALUES(?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, roomID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void insertToReceiptsExtra(int receiptID, int extraID)
    {
        String sql = "INSERT INTO receipts_extra (receiptID, extraID) VALUES(?,?)";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, receiptID);
            pstmt.setInt(2, extraID);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /* Select -----------------------------------------------------------------------*/
    public static ResultSet selectAll(String sql)
    {
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

    public static List<Customer> getCustomers()
    {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
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

    public static ResultSet getAllRooms()
    {
        String sql = "SELECT * FROM rooms";
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

    public static ResultSet getAvailableRooms()
    {
        String sql = "SELECT * FROM rooms WHERE status = 0";
        ResultSet result = null;
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            result = pstmt.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ResultSet getExtras()
    {
        String sql = "SELECT * FROM extras";
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

    public static ResultSet getReceiptsExtras()
    {
        String sql = "SELECT * FROM receipts_extra";
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

    public static ResultSet getLogs()
    {
        String sql = "SELECT * FROM logs";
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

    /* Update -----------------------------------------------------------------------*/
    public void updateCustomer(int customerID, String name, String phoneNumber)
    {
        String sql = "UPDATE customers SET name = ?, phoneNumber = ? WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setInt(3, customerID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void checkout(int receiptID)
    {
        String sql = "UPDATE receipts SET status = 1 WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, receiptID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void makeRoomAvailable(int roomID)
    {
        String sql = "UPDATE rooms SET status = 1 WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, roomID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void makeRoomOccupied(int roomID)
    {
        String sql = "UPDATE rooms SET status = 0 WHERE id = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, roomID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
