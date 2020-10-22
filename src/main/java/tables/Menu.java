package tables;

import java.io.Serializable;
import java.util.Objects;

public class Menu implements Serializable {
    private int id;
    private int id_cat;
    private String name;
    private float price;
    private int avail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCat() {
        return id_cat;
    }

    public void setIdCat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                id_cat == menu.id_cat &&
                Float.compare(menu.price, price) == 0 &&
                avail == menu.avail &&
                Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_cat, name, price, avail);
    }

    @Override
    public String toString() {
        return "menu{" + "id=" + id +
                ", name=" + name + ", price=" + price + '}' + '\n';  //"menu{" + "id=" + id +", name='" + name + ", price="+price+ '\'' + '}'+'\n'
    }
}