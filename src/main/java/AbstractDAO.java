//package ;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<E, K> {
    Connection connection;
    //   private ConnectionPool connectionPool;

    public AbstractDAO() {
        //      connectionPool = ConnectionPool.getConnectionPool();
        //       connection = connectionPool.getConnection();
        try {
            SimpleConnectionBuilder c = new SimpleConnectionBuilder();
            connection = c.getConnection();
//            System.out.println("Соединение с СУБД выполнено.");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL!");
        }
    }

    public abstract List<E> getAll();

    public abstract E getEntryById(K id);

    public abstract boolean update(K id, E entry);

    public abstract boolean delete(K id);

    public abstract boolean create(E entry);

    // Возвращения экземпляра Connection в пул соединений
//    public void returnConnectionInPool() {
    //       connectionPool.returnConnection(connection);
    //   }

    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Получение экземпляра Statement
    public Statement getStatement() {
        Statement st = null;
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
