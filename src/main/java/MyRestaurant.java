import connection.SimpleConnectionBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MyRestaurant {
    public static void main(String[] args) {
        SimpleConnectionBuilder c = new SimpleConnectionBuilder();
        ConsoleMessage m = new ConsoleMessage();
        try (
                Connection connection = c.getConnection();
                Scanner sc = new Scanner(System.in)
        ) {
            int n;
            boolean qrun;
            m.hi();
            Service ser = new Service(sc, connection);
            do {
                m.start();
                n = Integer.parseInt(sc.next());
                switch (n) {
                    case 1:
                        ser.OrderAdd(sc, connection);
                        break;
                    case 2:
                        ser.CustomerNew(sc, connection);
                        break;
                    case 3:
                        ser.CustomerShowAll(connection);
                        break;
                    case 4:
                        ser.CustomerById(sc, connection);
                        break;
                    case 5:
                        ser.CustomerUpdate(sc, connection);
                        break;
                    case 6:
                        ser.CustomerDelete(sc, connection);
                        break;
                    case 7:
                        ser.MenuAdd(sc, connection);
                        break;
                    case 8:
                        ser.MenuShowAll(connection);
                        break;
                    case 9:
                        ser.OrderShowAll(connection);
                        break;
                    case 10:
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
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error");
        } finally {
            m.bye();
        }
    }
}
