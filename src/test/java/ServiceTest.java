import connection.SimpleConnectionBuilder;
import dao.CustomerDAO;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tables.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class ServiceTest {
    Connection con;

    @BeforeSuite
    void setUp() throws SQLException {
        SimpleConnectionBuilder c = new SimpleConnectionBuilder();
        con = c.getConnection();
    }

    @Test(testName = "Service_test1", description = "check creation of new entry into table customer")
    public void testCustomerNew() {

        CustomerDAO cDAO = new CustomerDAO(con);
        int count = cDAO.qtyEntry();                  //count contains number of entries in customer table

        Scanner sc = new Scanner("Oracle\n" + "109\n");
        Service ser = new Service(sc, con);
        ser.CustomerNew(sc, con);
        count = cDAO.qtyEntry() - count;            //difference between number of entries in table "custom" before Service method "Customer new
        assertEquals(count, 1);
        sc.close();
    }

    @Test
    public void testCustomerDelete() {
        CustomerDAO cDAO = new CustomerDAO(con);

        int id = cDAO.IdLastEntry();                  //id contains id of last entry in customer table
        Scanner sc = new Scanner(id + "\n");
        Service ser = new Service(sc, con);
        ser.CustomerDelete(sc, con);

        Customer cus_actual = cDAO.getEntryById(id); //empty record: customer{id=0, name='null, discount=0.0, value=0.0, cardNo=0'}
        int actual = cus_actual.getId();
        assertEquals(actual, 0);

        sc.close();

    }

    @AfterSuite
    void closeConnection() throws SQLException {
        con.close();
    }

}
