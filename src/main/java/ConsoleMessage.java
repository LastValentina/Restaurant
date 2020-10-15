public class ConsoleMessage {

    void start() {
        System.out.println("just input one of these numbers:");
        System.out.println("1: make order from menu");
        System.out.println("2: add new customer");
        System.out.println("3: show all customers");
        System.out.println("4: show customer by id");
        System.out.println("5: edit customer's data");
        System.out.println("6: delete customer by id");
        System.out.println("7: add new meal to menu");
        System.out.println("8: show all menu");
        System.out.println("9: quit");
    }

    void input_id() {
        System.out.println("Input id");
    }

    void input_some(String name) {
        System.out.println("Input " + name);
    }

    void update_instruction() {
        System.out.println("some fields would be shown under line. every time input 1 - for updating or input 0 - to skip");
        System.out.println("----------------");
    }

    void output_value(String d, String val) {
        System.out.println(d + ": " + val);
    }

    void output_value(String d, float val) {
        System.out.println(d + ": " + val);
    }

    void hi() {
        System.out.println("Hello Mr Anderson,");
        System.out.println("Please make your choice");
    }

    void bye() {
        System.out.println("Follow white rabbit");
    }
}