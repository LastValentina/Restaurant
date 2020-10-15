import connection.SimpleConnectionBuilder;

import java.sql.Connection;
import java.sql.SQLException;
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
            ConsoleMessage m = new ConsoleMessage();
            m.hi();
            Service ser = new Service(sc, connection);
            do {
                m.start();
                n = Integer.parseInt(sc.next());
                switch (n) {
                    case 1:
                        ser.OrderAdd(sc, connection);
                        break;
                    case 2:                                 // add new entry to table customer
                        ser.CustomerNew(sc, connection);
                        break;
                    case 3:                                 //show all from customer
                        ser.CustomerShowAll(connection);
                        break;
                    case 4:                                 //show customer by id
                        ser.CustomerById(sc, connection);
                        break;
                    case 5:                                 //edit customer's data
                        ser.CustomerUpdate(sc, connection);
                        break;
                    case 6:                                 //delete customer by id
                        ser.CustomerDelete(sc, connection);
                        break;
                    case 7:                                 // insert entry into table menu
                        ser.MenuAdd(sc, connection);
                        break;
                    case 8:                                 // show all from table menu
                        ser.MenuShowAll(connection);
                        break;
                    case 9: // show all orders
                        ser.OrderShowAll(connection);
                        break;
                    case 10:  //  quit from application
                        break;
                    default:
                        System.out.println("unfortunately your input is out of scope");
                }
                if (n == 10) {
                    break;
                }
                System.out.println("would you like to continue: 1-yes/0-no");
                int ans = Integer.parseInt(sc.next());
                qrun = ans == 1;
            } while (qrun);
            m.bye();
        } catch (SQLException e) {
            e.printStackTrace(); // monitoring errors of DriverManager.getConnection
            System.out.println("SQL error");
        }
    }
}
