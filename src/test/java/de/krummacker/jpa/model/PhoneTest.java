package de.krummacker.jpa.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the Phone data access object.
 */
public class PhoneTest {

    /**
     * Make sure that the default constructor works.
     */
    @Test
    public void testDefaultConstructor() {
        Phone phone = new Phone();
        Assert.assertNull(phone.getId());
        Assert.assertNull(phone.getOwner());
        Assert.assertNull(phone.getNumber());
        Assert.assertNull(phone.toString());
    }

    /**
     * Make sure that the constructor with the arguments works.
     */
    @Test
    public void testArgConstructor() {
        Employee employee = new Employee();
        Phone phone = new Phone(employee, "81970");
        Assert.assertNull(phone.getId());
        Assert.assertEquals(phone.getOwner(), employee);
        Assert.assertEquals(phone.getNumber(), "81970");
        Assert.assertEquals(phone.toString(), "81970");
    }

    /**
     * Make sure that we can change the owner.
     */
    @Test
    public void testSetOwner() {
        Phone phone = new Phone(null, "81970");
        Assert.assertNull(phone.getOwner());
        Employee employee = new Employee();
        phone.setOwner(employee);
        Assert.assertEquals(phone.getOwner(), employee);
    }
}
