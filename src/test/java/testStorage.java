import com.hotelmanager.model.Customer;
import com.hotelmanager.model.Logs;
import com.hotelmanager.model.Reservation;

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

        List<Customer> customerList = getCustomers();
        for(Customer cs : customerList)
        {
            cs.printCustomer();
        }

        List<Reservation> rsList = getReservations();
        for(Reservation rp : rsList)
        {
            rp.printReservation();
        }

        List<Logs> logsList = getLogs();
        for(Logs ll : logsList)
        {
            ll.printLog();
        }
        System.out.println("Hello, World!");

//        List<Customer> csList = new ArrayList<Customer>();
//        csList.add(new Customer("Chuong", "02039020101"));
//        csList.add(new Customer("Rang", "0980988891"));
//        csList.add(new Customer("Tuan", "09831933245"));
    }
}
