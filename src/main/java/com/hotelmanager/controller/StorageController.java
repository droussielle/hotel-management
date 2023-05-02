package com.hotelmanager.controller;

import java.io.File;
import java.util.ArrayList;

import static com.hotelmanager.util.Storage.*;

public class StorageController
{
    public StorageController() throws Exception
    {
        System.out.println("Checking if database exists...");
        if (!checkDatabase())
        {
            System.out.println("Database isn't available, creating new database...");
            createDatabase();
        } else
        {
            System.out.println("Database found at fileDirectory/hotel.db.");
        }
    }

    public boolean checkDatabase()
    {
        File database = new File("./hotel.db");
        return database.isFile();
    }

    public void createDatabase() throws Exception
    {
        createNewDatabase();
        ArrayList<String> sqlBatch = new ArrayList<>();
        sqlBatch.add("CREATE TABLE IF NOT EXISTS \"rooms\" (\n" +
                "        \"id\"    INTEGER,\n" +
                "        \"type\"  TEXT NOT NULL,\n" +
                "        \"status\"        INT NOT NULL DEFAULT 1,\n" +
                "        \"price\" INT NOT NULL,\n" +
                "        PRIMARY KEY(\"id\")\n" +
                ");");
        sqlBatch.add("CREATE TABLE IF NOT EXISTS \"extras\" (\n" +
                "        \"id\"    INTEGER,\n" +
                "        \"type\"  TEXT NOT NULL,\n" +
                "        \"name\"  TEXT NOT NULL,\n" +
                "        \"price\" INT NOT NULL,\n" +
                "        PRIMARY KEY(\"id\")\n" +
                ");");
        sqlBatch.add("CREATE TABLE IF NOT EXISTS \"logs\" (\n" +
                "        \"id\"       INTEGER PRIMARY KEY,\n" +
                "        \"details\"       TEXT NOT NULL,\n" +
                "        \"time\"          INT NOT NULL,\n" +
                "        \"fileName\"      TEXT\n" +
                ");");
        sqlBatch.add("CREATE TABLE IF NOT EXISTS \"reservation_extras\" (\n" +
                "        \"reservationID\"     INTEGER NOT NULL,\n" +
                "        \"extraID\"       INTEGER NOT NULL,\n" +
                "        FOREIGN KEY(\"extraID\") REFERENCES \"extras\"(\"id\"),\n" +
                "        FOREIGN KEY(\"reservationID\") REFERENCES \"reservations\"(\"id\")\n" +
                ");");
        sqlBatch.add("CREATE TABLE IF NOT EXISTS \"reservations\" (\n" +
                "        \"id\"    \tINTEGER,\n" +
                "        \"name\"  \tTEXT NOT NULL,\n" +
                "        \"phoneNumber\"   TEXT NOT NULL,\n" +
                "        \"paymentMethod\" TEXT NOT NULL,\n" +
                "        \"cardNumber\"\tTEXT,\n" +
                "        \"roomID\"        INTEGER NOT NULL,\n" +
                "        \"duration\"      INTEGER NOT NULL,\n" +
                "        \"time\"  \tINT NOT NULL,\n" +
                "        \"status\"        INT NOT NULL DEFAULT 0,\n" +
                "        FOREIGN KEY(\"roomID\") REFERENCES \"rooms\"(\"id\"),\n" +
                "        PRIMARY KEY(\"id\")\n" +
                ");");
        sqlBatch.add("CREATE TRIGGER \"new_reservation\"\n" +
                "AFTER INSERT ON \"reservations\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('booked a room with reservationID = ' || NEW.\"id\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"checkout\"\n" +
                "BEFORE UPDATE OF \"status\" ON \"reservations\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('checked out with reservationID = ' || NEW.\"id\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"edit_reservation_room\"\n" +
                "BEFORE UPDATE OF \"roomID\" ON \"reservations\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('edited reservation of reservationID = ' || NEW.\"id\" || \n" +
                "                ' from room ' || OLD.\"roomID\" || ' to room ' || NEW.\"roomID\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"edit_customer_info\"\n" +
                "BEFORE UPDATE OF \"name\",\"phoneNumber\" ON \"reservations\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('edited customer info of reservationID = ' || NEW.\"id\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"edit_payment_info\"\n" +
                "BEFORE UPDATE OF \"paymentMethod\",\"cardNumber\" ON \"reservations\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('edited payment info of reservationID = ' || NEW.\"id\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"add_room\"\n" +
                "AFTER INSERT ON \"rooms\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('added room id = ' || NEW.\"id\", unixepoch());\n" +
                "END;");
        sqlBatch.add("CREATE TRIGGER \"delete_room\"\n" +
                "AFTER DELETE ON \"rooms\"\n" +
                "FOR EACH ROW\n" +
                "BEGIN\n" +
                "        INSERT INTO \"logs\" (\"details\", \"time\")\n" +
                "        VALUES ('deleted room id = ' || OLD.\"id\", unixepoch());\n" +
                "END;");
        createNewTableBatch(sqlBatch);
    }
}
