import connection.SimpleConnectionBuilder;
import dao.CustomerDAO;
import dao.MenuDAO;
import dao.OrderDAO;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tables.Customer;
import tables.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class ServiceTest {
    Connection con;

    @BeforeSuite
    void setUp() throws SQLException {
        SimpleConnectionBuilder c = new SimpleConnectionBuilder();
        con = c.getConnection();
    }

    //   @Parameters({"Trinity", "110", "0", "0"})
    @Test(testName = "Service_test1", description = "check method CustomerNew - creation of new entry into table customer")
    public void testCustomerNew() { //(String name,int card,float discount, float value) {
        String name = "Trinity";
        int card = 120;
        float discount = 0;
        float value = 0;

        CustomerDAO cDAO = new CustomerDAO(con);
        List<Customer> listBefore = new LinkedList<>(cDAO.getAll());
        Scanner sc = new Scanner(name + "\n" + card + "\n");  //"Trinity\n" + "120\n"
        Service ser = new Service(sc, con);
        ser.CustomerNew(sc, con);
        List<Customer> listAfter = new LinkedList<>(cDAO.getAll());
        int id = cDAO.IdLastEntry();

        Customer testedCustomer = new Customer();
        testedCustomer.setId(id);
        testedCustomer.setName(name);
        testedCustomer.setDiscount(discount);
        testedCustomer.setValue(value);
        testedCustomer.setCard(card);
        List<Customer> listExpected = new LinkedList<>();
        listExpected.addAll(listBefore);
        listExpected.add(testedCustomer);

        assertEquals(listAfter, listExpected);
        sc.close();
    }

    @Test(testName = "Service_test2", description = "check method CustomerUpdate - update of last entry from table customer")
    public void testCustomerUpdate() {
        float new_discount = 50;  //data for updating
        CustomerDAO cDAO = new CustomerDAO(con);
        int id = cDAO.IdLastEntry();                  //id contains id of last entry in customer table
        List<Customer> listBefore = new LinkedList<>(cDAO.getAll());
        Customer testedCustomer = cDAO.getEntryById(id);
        List<Customer> listExpected = new LinkedList<>();
        listExpected.addAll(listBefore);
        listExpected.remove(testedCustomer);
        testedCustomer.setDiscount(new_discount);
        listExpected.add(testedCustomer);

        Scanner sc = new Scanner(id + "\n" + "0\n" + "1\n" + new_discount + "\n" + "0\n" + "0\n");
        Service ser = new Service(sc, con);
        ser.CustomerUpdate(sc, con);
        List<Customer> listAfter = new LinkedList<>(cDAO.getAll());
        assertEquals(listAfter, listExpected);
        sc.close();
    }

    @Test(testName = "Service_test3", description = "check method CustomerDelete - deletion of last entry from table customer")
    public void testCustomerDelete() {
        CustomerDAO cDAO = new CustomerDAO(con);
        int id = cDAO.IdLastEntry();                  //id contains id of last entry in customer table
        List<Customer> listBefore = new LinkedList<>(cDAO.getAll());
        Customer testedCustomer = cDAO.getEntryById(id);
        List<Customer> listExpected = new LinkedList<>();
        listExpected.addAll(listBefore);
        listExpected.remove(testedCustomer);

        Scanner sc = new Scanner(id + "\n");
        Service ser = new Service(sc, con);
        ser.CustomerDelete(sc, con);
        List<Customer> listAfter = new LinkedList<>(cDAO.getAll());
        assertEquals(listAfter, listExpected);
        sc.close();
    }

    @Test(testName = "Service_test4", description = "check method OrderNew - correct calculation for field value (based on fields from others tables) during creation of new entry into table orders")
    public void testOrderAdd() { //(int id_cust,int id_menu,int qty, float discount, float value) {
        int qty = 5;  //for test

        OrderDAO oDAO = new OrderDAO(con);
        List<Order> listBefore = new LinkedList<>(oDAO.getAll()); // records from orders before test
        CustomerDAO cDAO = new CustomerDAO(con);
        int id_cust = cDAO.IdLastEntry();
        MenuDAO mDAO = new MenuDAO(con);
        int id_menu = mDAO.IdLastEntry();
        float discount = (cDAO.getEntryById(id_cust)).getDiscount();
        if (discount > 0) {
            discount = (100 - discount) / 100;
        } else {
            discount = 1;
        }
        float expectedValue = qty * discount * (mDAO.getEntryById(id_menu)).getPrice();

        Scanner sc = new Scanner(id_cust + "\n" + id_menu + "\n" + qty + "\n");
        Service ser = new Service(sc, con);
        ser.OrderAdd(sc, con);
        List<Order> listAfter = new LinkedList<>(oDAO.getAll());
        listAfter.removeAll(listBefore); //expected only one new record that tested
        Order testedOrder = listAfter.iterator().next();
        float actualValue = testedOrder.getValue();
        assertEquals(actualValue, expectedValue);
        sc.close();
    }

    @AfterSuite
    void closeConnection() throws SQLException {
        con.close();
    }

}
