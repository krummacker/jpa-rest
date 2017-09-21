package de.krummacker.jpa.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests the DeliveryNote data access object.
 */
public class DeliveryNoteTest {

    /**
     * Make sure that the constructor and the accessors work fine.
     */
    @Test
    public void testConstructorAndAccessors() {
        int deliveryNoteNumber = 4711;
        int shopNumber = 42;
        Date shippingDate = Calendar.getInstance().getTime();
        DeliveryNote deliveryNote = new DeliveryNote(deliveryNoteNumber, shopNumber, shippingDate);
        Assert.assertNull(deliveryNote.getId());
        Assert.assertEquals(deliveryNote.getDeliveryNoteNumber(), deliveryNoteNumber);
        Assert.assertEquals(deliveryNote.getShopNumber(), shopNumber);
        Assert.assertEquals(deliveryNote.getShippingDate(), shippingDate);
    }

    /**
     * Make sure that items can be added to this DeliveryNote.
     */
    @Test
    public void testAddAndGetItems() {
        DeliveryNoteItem a = new DeliveryNoteItem(null, 1, 4, "7");
        DeliveryNoteItem b = new DeliveryNoteItem(null, 2, 5, "8");
        DeliveryNoteItem c = new DeliveryNoteItem(null, 3, 6, "9");
        List<DeliveryNoteItem> expected = Arrays.asList(a, b, c);
        DeliveryNote deliveryNote = new DeliveryNote(0, 0, Calendar.getInstance().getTime());
        for (DeliveryNoteItem item : expected) {
            deliveryNote.addItem(item);
        }
        Assert.assertEquals(deliveryNote.getItems(), expected);
    }
}
