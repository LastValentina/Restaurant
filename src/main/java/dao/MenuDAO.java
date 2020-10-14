package dao;

import tables.Menu;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MenuDAO extends AbstractDAO<Menu, Integer> {

    public MenuDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement("SELECT * FROM menu");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu cus = new Menu();
                cus.setId(rs.getInt(1));
                cus.setName(rs.getString(3));
                cus.setPrice(rs.getFloat(4));
                cus.setAvail(rs.getInt(5));
                cus.setIdCat(rs.getInt(2));
                lst.add(cus);
            }
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public Menu getEntryById(Integer id) {
        Menu cus1 = new Menu();
        try {
            PreparedStatement ps = getPrepareStatement("SELECT * FROM menu WHERE id= ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("no such entry");
                return cus1;
            }
            cus1.setId(rs.getInt(1));
            cus1.setName(rs.getString(3));
            cus1.setPrice(rs.getFloat(4));
            cus1.setAvail(rs.getInt(5));
            cus1.setIdCat(rs.getInt(2));
            closeResSet(rs);
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cus1;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement ps = getPrepareStatement("DELETE FROM menu WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Menu entry) {
        try {
            String sql = "INSERT INTO menu (name, price, available) VALUES(?,?,?)";
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, entry.getName());
            ps.setFloat(2, entry.getPrice());
            ps.setInt(3, entry.getAvail());
            //      ps.setInt(1, entry.getIdCat());
            ps.executeUpdate();
            closePrepareStatement(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Menu entry) {
        String sql = "SELECT * FROM menu WHERE id=  " + id;
        try {
            Statement st = getStatement();   // ResultSet.CONCUR_UPDATABLE!
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("no such entry to update");
                return;
            }
            rs.updateString(3, entry.getName());
            rs.updateFloat(4, entry.getPrice());
            rs.updateInt(2, entry.getIdCat());
            rs.updateInt(5, entry.getAvail());
            rs.updateRow();
            rs.close();
            closeStatement(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}