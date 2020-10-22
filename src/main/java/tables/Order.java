package tables;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private int id_cust;
    private int id_menu;
    private int qty;
    private float discount;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCust() {
        return id_cust;
    }

    public void setIdCust(int id_cust) {
        this.id_cust = id_cust;
    }

    public int getIdMenu() {
        return id_menu;
    }

    public void setIdMenu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                id_cust == order.id_cust &&
                id_menu == order.id_menu &&
                qty == order.qty &&
                Float.compare(order.discount, discount) == 0 &&
                Float.compare(order.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_cust, id_menu, qty, discount, value);
    }

    @Override
    public String toString() {
        return "order{" + "id=" + id + ", customer_id=" + id_cust + ", menu_id=" + id_menu + ", quantity=" + qty +
                ", discount=" + discount + ", value=" + value + '}' + '\n';
    }
}