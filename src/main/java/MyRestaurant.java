
import connection.SimpleConnectionBuilder;
import dao.CustomerDAO;
import dao.MenuDAO;
import tables.Customer;
import tables.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MyRestaurant {
    public static void main(String[] args) {
        SimpleConnectionBuilder c = new SimpleConnectionBuilder();
        try (
                Connection connection = c.getConnection();
                Scanner sc = new Scanner(System.in)
        ) {
            int n;
            boolean qrun;
            ConsoleMenu m = new ConsoleMenu();
            m.hi();
            do {
                m.start();
                n = Integer.parseInt(sc.next());
                switch (n) {
                    case 1:
                        System.out.println("sorry, isn't adjusted yet");
                        break;
                    case 2: // add new entry to table customer
                        Customer p = new Customer();
                        m.i_some("name");
                        String name = sc.next();
                        p.setName(name);
                        p.setDiscount(0);
                        p.setValue(0);
                        m.i_some("cardNo - integer");
                        p.setCard(Integer.parseInt(sc.next()));
                        CustomerDAO q = new CustomerDAO(connection);
                        q.create(p);
                        break;
                    case 3:  //show all from customer
                        CustomerDAO q1 = new CustomerDAO(connection);
                        List<Customer> q2 = q1.getAll();
                        System.out.println(q2);
                        break;
                    case 4:  //show customer by id
                        CustomerDAO q3 = new CustomerDAO(connection);
                        m.i_id();
                        int n4 = Integer.parseInt(sc.next());
                        Customer q4 = q3.getEntryById(n4);
                        System.out.println(q4);
                        break;
                    case 5:  //edit customer's data
                        CustomerDAO q5 = new CustomerDAO(connection);
                        m.i_id();
                        int i = Integer.parseInt(sc.next());
                        Customer q6 = q5.getEntryById(i);
                        m.o_up();
                        m.o_f("name", q6.getName());
                        if (Integer.parseInt(sc.next()) == 1) {
                            m.i_n();
                            q6.setName(sc.next());
                        }
                        m.o_f("discount", q6.getDiscount());
                        if (Integer.parseInt(sc.next()) == 1) {
                            m.i_n();
                            q6.setDiscount(Float.parseFloat(sc.next()));
                            System.out.println(q6.getDiscount());
                        }
                        m.o_f("value", q6.getValue());
                        if (Integer.parseInt(sc.next()) == 1) {
                            m.i_n();
                            q6.setValue(Float.parseFloat(sc.next()));
                        }
                        m.o_f("card", q6.getCard());
                        if (Integer.parseInt(sc.next()) == 1) {
                            m.i_n();
                            q6.setCard(Integer.parseInt(sc.next()));
                        }
                        CustomerDAO q7 = new CustomerDAO(connection);
                        q7.update(i, q6);
                        break;
                    case 6:
                        //delete customer by id
                        CustomerDAO q9 = new CustomerDAO(connection);
                        m.i_id();
                        q9.delete(Integer.parseInt(sc.next()));
                        break;
                    case 7: // insert entry into table menu
                        Menu p7 = new Menu();
                        //     p7.setIdCat(1);
                        m.i_some("name");
                        p7.setName(sc.next());
                        m.i_some("price");
                        p7.setPrice(Float.parseFloat(sc.next()));
                        //     p7.setAvail(1);
                        MenuDAO q17 = new MenuDAO(connection);
                        q17.create(p7);
                        break;
                    case 8: // show all from menu
                        MenuDAO q10 = new MenuDAO(connection);
                        List<Menu> q12 = q10.getAll();
                        System.out.println(q12);
                        break;
                    case 9:
                        //  m.bye();
                        break;
                    default:
                        System.out.println("unfortunately your input is out of scope");
                }
                if (n == 9) {
                    break;
                }
                System.out.println("would you like to continue: 1-yes/0-no");
                int ans = Integer.parseInt(sc.next());
                qrun = ans == 1;
            } while (qrun);
            m.bye();
//            System.out.println("Соединение с СУБД выполнено.");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL!");
        }
    }
}
