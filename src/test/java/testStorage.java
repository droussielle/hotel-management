import com.hotelmanager.model.Customer;

import java.sql.SQLException;
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
    }
}
