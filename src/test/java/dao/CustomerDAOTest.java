package dao;

import connection.SimpleConnectionBuilder;
import org.testng.annotations.Test;
import tables.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class CustomerDAOTest {
    @Test(testName = "Service_test3", description = "check DAO-level selection of entry by ID from table customer")
    public void testDAOCustomerByID() throws SQLException {
        SimpleConnectionBuilder c = new SimpleConnectionBuilder();
        Connection con = c.getConnection();

        CustomerDAO cDAO = new CustomerDAO(con);

        int id = cDAO.IdLastEntry();                  //id contains id of last entry in customer table
        Scanner sc = new Scanner(id + "\n");

        Customer cus_actual = cDAO.getEntryById(id); //empty record: customer{id=0, name='null, discount=0.0, value=0.0, cardNo=0'}
        int actual = cus_actual.getId();
        assertEquals(actual, id);
        sc.close();
        con.close();
    }

}
