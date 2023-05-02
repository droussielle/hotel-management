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

    public static String fileName = "hotel.db";

    /* Connect -----------------------------------------------------------------------*/
    public static Connection connectDatabase()
    {
        String url = "jdbc:sqlite:" + fileName;
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
    public static void createNewDatabase() throws Exception
    {
        try (Connection conn = connectDatabase())
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created at fileDirectory/" + fileName);
            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public static void createNewTable(String sql)
    {
        // SQLite connection string
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTableBatch(ArrayList<String> sqlBatch) throws Exception
    {
        // SQLite connection string
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement())
        {
            for(String s : sqlBatch)
            {
                stmt.addBatch(s);
            }
            // create a new table
            stmt.executeBatch();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /* Delete -----------------------------------------------------------------------*/
    public static void deleteFromRooms(int id) throws Exception
    {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    public static void deleteFromExtras(int id) throws Exception
    {
        String sql = "DELETE FROM extras WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    /* Insert -----------------------------------------------------------------------*/
    public static void insertToRooms(int id, String type, int price) throws Exception
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
            throw new Exception(e.getMessage());
        }
    }

    public static void insertToExtras(String type, String name, int price) throws Exception
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
            throw new Exception(e.getMessage());
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
            System.out.println("Exception during insert to reservations: " + e.getMessage());
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
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT DISTINCT name, phoneNumber FROM reservations";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                customerList.add(new Customer(rs.getString("name"),
                        rs.getString("phoneNumber")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public static List<Room> getAllRooms()
    {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                roomList.add(new Room(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt(("status"))));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public static List<Room> getAvailableRooms()
    {
        List<Room> roomList = new ArrayList<>();
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
        List<Extra> extraList = new ArrayList<>();
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

    public static List<Reservation> getReservations()
    {
        List<Reservation> reservationList =  new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                reservationList.add(new Reservation(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("paymentMethod"),
                        rs.getString("cardNumber"),
                        rs.getInt("roomID"),
                        rs.getInt("duration"),
                        rs.getInt("time"),
                        rs.getInt("status")));
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return reservationList;
    }

    public static Reservation getSingleReservation(int reservationID)
    {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, reservationID);
            ResultSet rs = pstmt.executeQuery();
            reservation = new Reservation(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("paymentMethod"),
                    rs.getString("cardNumber"),
                    rs.getInt("roomID"),
                    rs.getInt("duration"),
                    rs.getInt("time"),
                    rs.getInt("status"));
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return reservation;
    }

    public static List<ReservationExtras> getReservationExtras(int reservationID)
    {
        List<ReservationExtras> reservationExtrasList = new ArrayList<>();
        String sql = "SELECT extraID,type,name,price,COUNT(extraID) FROM reservation_extras " +
                    "INNER JOIN extras ON extras.id = reservation_extras.extraID " +
                    "WHERE reservationID = ? GROUP BY extraID";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, reservationID);
            try (ResultSet rs = pstmt.executeQuery())
            {
                while (rs.next())
                {
                    reservationExtrasList.add(new ReservationExtras(rs.getInt("extraID"),
                            rs.getString("type"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("COUNT(extraID)")));
                }
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return reservationExtrasList;
    }

    public static List<Logs> getLogs()
    {
        List<Logs> logList = new ArrayList<>();
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

    public static int getRoomPrice(int roomID)
    {
        int roomPrice = 0;
        String sql = "SELECT price FROM rooms WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, roomID);
            ResultSet rs = pstmt.executeQuery();
            roomPrice = rs.getInt("price");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return roomPrice;
    }

    /* Update -----------------------------------------------------------------------*/
    public static void updateRoomsStatus(int roomID, int newValue) throws Exception
    {
        String sql = "UPDATE rooms SET status = ? WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, newValue);
            pstmt.setInt(2, roomID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public static void updateReservationsStatus(int reservationID, int newValue) throws Exception
    {
        String sql = "UPDATE reservations SET status = ? WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // set the corresponding param
            pstmt.setInt(1, newValue);
            pstmt.setInt(2, reservationID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    public static void updateExtras()
    {

    }

    public static void updateReservationExtras()
    {

    }

    public static void updateReservations()
    {

    }
}
