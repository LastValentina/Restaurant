import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MenuDAO extends AbstractDAO<Menu, Integer> {

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
            connection.close();
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
            if (rs.next()) {
            } else {
                System.out.println("no such entry");
                return cus1;
            }
            ;
            cus1.setId(rs.getInt(1));
            cus1.setName(rs.getString(3));
            cus1.setPrice(rs.getFloat(4));
            cus1.setAvail(rs.getInt(5));
            cus1.setIdCat(rs.getInt(2));
            closeResSet(rs);
            closePrepareStatement(ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cus1;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            PreparedStatement ps = getPrepareStatement("DELETE FROM menu WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            closePrepareStatement(ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean create(Menu entry) {
        try {
            String sql = "INSERT INTO menu (name, price, available) VALUES(?,?,?)";
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, entry.getName());
            ps.setFloat(2, entry.getPrice());
            ps.setInt(3, entry.getAvail());
            //      ps.setInt(1, entry.getIdCat());
            ps.executeUpdate();
            closePrepareStatement(ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Integer id, Menu entry) {
        String sql = "SELECT * FROM menu WHERE id=  " + id;
        try {
            Statement st = getStatement();   // ResultSet.CONCUR_UPDATABLE!
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
            } else {
                System.out.println("no such entry to update");
                return false;
            }
            ;
            rs.updateString(3, entry.getName());
            rs.updateFloat(4, entry.getPrice());
            rs.updateInt(2, entry.getIdCat());
            rs.updateInt(5, entry.getAvail());
            rs.updateRow();
            rs.close();
            closeStatement(st);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}