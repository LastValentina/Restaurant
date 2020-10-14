package tables;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testToString() {
        String expected = "customer{id=0, name='null, discount=0.0, value=0.0, cardNo=0'}\n";
        Customer customer = new Customer();
        String actual = customer.toString();
        assertEquals(actual, expected);
    }
}