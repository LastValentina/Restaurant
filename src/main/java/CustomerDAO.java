//package ;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer, Integer> {
    public static final String SELECT_ALL_Customer = "SELECT * FROM customer";

    @Override
    public List<Customer> getAll() {
        List<Customer> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_Customer);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer cus = new Customer();
                cus.setId(rs.getInt(1));
                cus.setName(rs.getString(2));
                cus.setDiscount(rs.getFloat(3));
                cus.setValue(rs.getFloat(4));
                cus.setCard(rs.getInt(5));
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
    public Customer getEntryById(Integer id) {
        Customer cus1 = new Customer();
        try {
            PreparedStatement ps = getPrepareStatement("SELECT * FROM customer WHERE id= ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            } else {
                System.out.println("no such entry");
                return cus1;
            }
            ;
            cus1.setId(rs.getInt(1));
            cus1.setName(rs.getString(2));
            cus1.setDiscount(rs.getFloat(3));
            cus1.setValue(rs.getFloat(4));
            cus1.setCard(rs.getInt(5));
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
            PreparedStatement ps = getPrepareStatement("DELETE FROM customer WHERE id= ?");
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
    public boolean create(Customer entry) {
        try {
            String sql = "INSERT INTO customer (name, discount,value,cardNo) VALUES(?,?,?,?)";
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, entry.getName());
            ps.setFloat(2, entry.getDiscount());
            ps.setFloat(3, entry.getValue());
            ps.setInt(4, entry.getCard());
            ps.executeUpdate();
            closePrepareStatement(ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Integer id, Customer entry) {
        String sql = "SELECT * FROM customer WHERE id=  " + id;
        try {
            Statement st = getStatement();   // ResultSet.CONCUR_UPDATABLE!
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
            } else {
                System.out.println("no such entry to update");
                return false;
            }
            ;
            rs.updateString(2, entry.getName());
            rs.updateFloat(3, entry.getDiscount());
            rs.updateFloat(4, entry.getValue());
            rs.updateInt(5, entry.getCard());
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