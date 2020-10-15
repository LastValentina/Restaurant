package dao;

import tables.Order;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order, Integer> {

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> getAll() {
        List<Order> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement("SELECT * FROM order");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order ord = new Order();
                ord.setId(rs.getInt(1));
                ord.setIdCust(rs.getInt(2));
                ord.setIdMenu(rs.getInt(3));
                ord.setQty(rs.getInt(4));
                ord.setDiscount(rs.getFloat(5));
                ord.setValue(rs.getFloat(6));
                lst.add(ord);
            }
            closeResSet(rs);
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public Order getEntryById(Integer id) {
        Order ord1 = new Order();
        try {
            PreparedStatement ps = getPrepareStatement("SELECT * FROM order WHERE id= ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("no such entry");
                return ord1;
            }
            ord1.setId(rs.getInt(1));
            ord1.setIdCust(rs.getInt(2));
            ord1.setIdMenu(rs.getInt(3));
            ord1.setQty(rs.getInt(4));
            ord1.setDiscount(rs.getFloat(5));
            ord1.setValue(rs.getFloat(6));
            closeResSet(rs);
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ord1;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement ps = getPrepareStatement("DELETE FROM order WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Order entry) {
        try {
            String sql = "INSERT INTO order (customer_id, menu_id, qty, discount, value) VALUES(?,?,?,?,?)";
            //           String sql = "INSERT INTO order (customer_id, menu_id, qty) VALUES(?,?,?)";
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, entry.getIdCust());
            ps.setInt(2, entry.getIdMenu());
            ps.setInt(3, entry.getQty());
            ps.setFloat(4, entry.getDiscount());
            ps.setFloat(5, entry.getValue());
            ps.executeUpdate();
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Order entry) {
        String sql = "SELECT * FROM order WHERE id=  " + id;
        try {
            Statement st = getStatement();   // ResultSet.CONCUR_UPDATABLE!
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("no such entry to update");
                return;
            }
            rs.updateInt(2, entry.getIdCust());
            rs.updateInt(3, entry.getIdMenu());
            rs.updateInt(4, entry.getQty());
            rs.updateFloat(5, entry.getDiscount());
            rs.updateFloat(6, entry.getValue());
            rs.updateRow();
            closeResSet(rs);
            closeStatement(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}