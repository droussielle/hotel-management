import com.hotelmanager.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hotelmanager.util.Storage.*;

public class testStorage
{
    public static void main(String[] args) throws SQLException
    {
        List<Customer> customerList = getCustomers();
        for(Customer cs : customerList)
        {
            System.out.println(cs.getName());
            System.out.println(cs.getPhoneNumber());
        }
        System.out.println("Hello, World!");

        List<Customer> csList = new ArrayList<Customer>();
        csList.add(new Customer(1, "Chuong", "02039020101", "20201929292"));
        csList.add(new Customer(2, "Rang", "0980988891", "219829232"));
        csList.add(new Customer(3, "Tuan", "09831933245", "29992392"));
    }
}
