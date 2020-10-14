//package ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionBuilder implements ConnectionBuilder {
    //    public static final String DB_URL ="jdbc:mysql://127.0.0.1/dbrestaurant";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1/dbrestaurant?autoReconnect=true&useSSL=false";
    //   public static final String DB_URL ="jdbc:mysql://localhost:3306/dbrestaurant?autoReconnect=true&useSSL=false";
    public static final String DB_Driver = "com.mysql.jdbc.Driver";
    static final String user = "root";
    static final String password = "12345";

    public SimpleConnectionBuilder() {
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC-driver for DB was not found!");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, user, password);
        //   catch (SQLException e) {
        //         e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
        //          System.out.println("Ошибка SQL!");
        //      }
    }
}