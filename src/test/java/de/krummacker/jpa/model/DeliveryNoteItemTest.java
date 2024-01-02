package de.krummacker.jpa.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Tests the DeliveryNoteItem data access object.
 */
public class DeliveryNoteItemTest {

    /**
     * Make sure that the constructor and the accessors work fine.
     */
    @Test
    public void testConstructorAndAccessors() {
        DeliveryNote container = new DeliveryNote(4711, 42, Calendar.getInstance().getTime());
        int amount = 100;
        int articleNumber = 123456789;
        String productName = "ACME Product";
        DeliveryNoteItem item = new DeliveryNoteItem(container, amount, articleNumber, productName);

        Assert.assertNull(item.getId());
        Assert.assertEquals(item.getAmount(), amount);
        Assert.assertEquals(item.getContainer(), container);
        Assert.assertEquals(item.getArticleNumber(), articleNumber);
        Assert.assertEquals(item.getProductName(), productName);
    }
}
