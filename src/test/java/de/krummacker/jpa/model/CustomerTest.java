package de.krummacker.jpa.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the Customer data access object.
 */
public class CustomerTest {

    /**
     * Make sure that the ratings are stored correctly.
     */
    @Test
    public void testGetRating() {
        for (Customer.Rating rating :
                Customer.Rating.values()) {
            Customer customer = new Customer("Jane", "Doe", rating);
            Assert.assertEquals(customer.getRating(), rating);
        }
    }

    /**
     * Make sure that the string representation of a Customer is correct.
     */
    @Test
    public void testToString() {
        Customer customer = new Customer("John", "Doe", Customer.Rating.NEW);
        Assert.assertEquals("Customer[id=null, firstName='John', lastName='Doe', rating='NEW']", customer.toString());

    }
}
