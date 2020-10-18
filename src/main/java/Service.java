import dao.CustomerDAO;
import dao.MenuDAO;
import dao.OrderDAO;
import tables.Customer;
import tables.Menu;
import tables.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Service {
    ConsoleMessage mes = new ConsoleMessage();

    public Service(Scanner sc, Connection connection) {
    }

    public void MenuAdd(Scanner sc, Connection connection) {
        Menu menu = new Menu();
        mes.input_some("name");
        menu.setName(sc.next());
        mes.input_some("price");
        menu.setPrice(Float.parseFloat(sc.next()));
        MenuDAO mDAO = new MenuDAO(connection);
        mDAO.create(menu);
    }

    public void OrderAdd(Scanner sc, Connection connection) {
        Order ord = new Order();
        mes.input_some("customer id (integer) - from connected table"); //discount has to picked up from table customer by this id
        ord.setIdCust(Integer.parseInt(sc.next()));
        mes.input_some("menu id (integer) - from connected table"); //price has to picked up from table menu by this id
        ord.setIdMenu(Integer.parseInt(sc.next()));
        mes.input_some("quantity of meal for order");
        ord.setQty(Integer.parseInt(sc.next()));

        CustomerDAO cDAO = new CustomerDAO(connection);     //discount is picked up from table customer by customer_id
        Customer cus = cDAO.getEntryById(ord.getIdCust());
        ord.setDiscount(cus.getDiscount());

        MenuDAO mDAO = new MenuDAO(connection);             //price is picked up from table menu by menu_id
        Menu menu = mDAO.getEntryById(ord.getIdMenu());
        float price = menu.getPrice();
        float discount = 1;
        if (ord.getDiscount() > 0) {
            discount = (100 - ord.getDiscount()) / 100;
        }
        ord.setValue(price * ord.getQty() * discount); // calculation for value based on qty, price & discount

        OrderDAO oDAO = new OrderDAO(connection);
        oDAO.create(ord);
    }

    public void OrderShowAll(Connection connection) {
        OrderDAO oDAO = new OrderDAO(connection);
        List<Order> list = oDAO.getAll();
        System.out.println(list);
    }

    public void CustomerNew(Scanner sc, Connection connection) {
        Customer customer = new Customer();
        mes.input_some("name");
        String name = sc.next();
        customer.setName(name);
        customer.setDiscount(0);
        customer.setValue(0);
        mes.input_some("cardNo - integer");
        customer.setCard(Integer.parseInt(sc.next()));
        CustomerDAO cDAO = new CustomerDAO(connection);
        cDAO.create(customer);
    }

    public void CustomerShowAll(Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        List<Customer> list = cDAO.getAll();
        System.out.println(list);
    }

    public void CustomerById(Scanner sc, Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        mes.input_id();
        int n4 = Integer.parseInt(sc.next());
        Customer customer = cDAO.getEntryById(n4);
        System.out.println(customer);
    }

    public void CustomerUpdate(Scanner sc, Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        mes.input_id();
        int i = Integer.parseInt(sc.next());
        Customer cust = cDAO.getEntryById(i);
        mes.update_instruction();
        mes.output_value("name", cust.getName());
        if (Integer.parseInt(sc.next()) == 1) {
            mes.input_some("new value");
            cust.setName(sc.next());
        }
        mes.output_value("discount", cust.getDiscount());
        if (Integer.parseInt(sc.next()) == 1) {
            mes.input_some("new value");
            cust.setDiscount(Float.parseFloat(sc.next()));
            System.out.println(cust.getDiscount());
        }
        mes.output_value("value", cust.getValue());
        if (Integer.parseInt(sc.next()) == 1) {
            mes.input_some("new value");
            cust.setValue(Float.parseFloat(sc.next()));
        }
        mes.output_value("card", cust.getCard());
        if (Integer.parseInt(sc.next()) == 1) {
            mes.input_some("new value");
            cust.setCard(Integer.parseInt(sc.next()));
        }
        CustomerDAO c2DAO = new CustomerDAO(connection);
        c2DAO.update(i, cust);
    }

    public void CustomerDelete(Scanner sc, Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        mes.input_id();
        cDAO.delete(Integer.parseInt(sc.next()));
    }

    public void CustomerCount(Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        int count = cDAO.qtyEntry();
        System.out.println("Number of records in table customer: " + count);
    }

    public void CustomerIdLastEntry(Connection connection) {
        CustomerDAO cDAO = new CustomerDAO(connection);
        int id = cDAO.IdLastEntry();
        System.out.println("id of last entry in table customer: " + id);
    }

    public void MenuShowAll(Connection connection) {
        MenuDAO mDAO = new MenuDAO(connection);
        List<Menu> list = mDAO.getAll();
        System.out.println(list);
    }
}
