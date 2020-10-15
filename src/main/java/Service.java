import dao.MenuDAO;
import dao.OrderDAO;
import tables.Menu;
import tables.Order;

import java.sql.Connection;
import java.util.Scanner;

public class Service {
    //   Scanner sc;
    ConsoleMessage mes = new ConsoleMessage();

    public Service(Scanner sc, Connection connection) {
    }

    public void MenuAdd(Scanner sc, Connection connection) {
        Menu menu = new Menu();
        mes.input_some("name");
        menu.setName(sc.next());
        mes.input_some("price");
        menu.setPrice(Float.parseFloat(sc.next()));
        MenuDAO mDAO = new MenuDAO(connection);
        mDAO.create(menu);
    }

    public void OrderAdd(Scanner sc, Connection connection) {
        Order ord = new Order();
        mes.input_some("customer id (integer) - from connected table"); //discount has to picked up from table customer by this id
        ord.setIdCust(Integer.parseInt(sc.next()));
        mes.input_some("menu id (integer) - from connected table"); //price has to picked up from table menu by this id
        ord.setIdMenu(Integer.parseInt(sc.next()));
        mes.input_some("quantity of meal for order");
        ord.setQty(Integer.parseInt(sc.next()));

//        Customer cus=new Customer();                    //discount is picked up from table customer by customer_id
        //      CustomerDAO cDAO=new CustomerDAO(connection);
//        cus=cDAO.getEntryById(ord.getIdCust());
//        ord.setDiscount(cus.getDiscount());

        //       Menu menu =new Menu();                    //price is picked up from table menu by menu_id
        //       MenuDAO mDAO=new MenuDAO(connection);
        //       menu=mDAO.getEntryById(ord.getIdCust());
        //       float price=menu.getPrice();
//        float discount=1;
//        if (ord.getDiscount()>0) {discount=ord.getDiscount()/100;}
        //       ord.setValue(price*ord.getQty()*discount); // calculation for value based on qty, price & discount

        OrderDAO oDAO = new OrderDAO(connection);
        oDAO.create(ord);
    }
}
