import java.io.Serializable;

public class Customer implements Serializable {
    private int id;
    private int card;
    private String name;
    private float discount;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "customer{" + "id=" + id +
                ", name='" + name + ", discount=" + discount + ", value=" + value + ", cardNo=" + card + '\'' + '}' + '\n';
    }
}