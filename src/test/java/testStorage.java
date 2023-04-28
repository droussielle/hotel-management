import com.hotelmanager.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import static com.hotelmanager.util.Storage.*;

public class testStorage
{
    public static void main(String[] args) throws SQLException
    {
        Connection conn = connectDatabase();
        LocalDateTime local = LocalDateTime.ofEpochSecond(System.currentTimeMillis()/1000, 0, OffsetDateTime.now().getOffset());
        System.out.println(local.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println("CUSTOMER LIST -----------------");
        List<Customer> customerList = getCustomers();
        for(Customer cs : customerList)
        {
            cs.printCustomer();
        }

        System.out.println("RESERVATIONS LIST -----------------");
        List<Reservation> rsList = getReservations();
        for(Reservation rp : rsList)
        {
            rp.printReservation();
        }

        System.out.println("LOGS LIST -----------------");
        List<Logs> logsList = getLogs();
        for(Logs ll : logsList)
        {
            ll.printLog();
        }

        System.out.println("EXTRA LIST -----------------");
        List<Extra> extraList = getExtras();
        for(Extra ex : extraList)
        {
            ex.printExtra();
        }

        System.out.println("ALL ROOMS LIST -----------------");
        List<Room> allRooms = getAllRooms();
        for(Room rm : allRooms)
        {
            rm.printRoom();
        }

        System.out.println("AVAILABLE ROOMS LIST -----------------");
        List<Room> availableRooms = getAvailableRooms();
        for(Room rm : availableRooms)
        {
            rm.printRoom();
        }

        System.out.println("RESERVATION EXTRAS LIST -----------------");
        List<ReservationExtras> reservationExtrasList = getReservationExtras(2);
        for(ReservationExtras rextra : reservationExtrasList)
        {
            rextra.printExtra();
        }
        System.out.println("Hello, World!");

//        List<Customer> csList = new ArrayList<Customer>();
//        csList.add(new Customer("Chuong", "02039020101"));
//        csList.add(new Customer("Rang", "0980988891"));
//        csList.add(new Customer("Tuan", "09831933245"));
    }
}
