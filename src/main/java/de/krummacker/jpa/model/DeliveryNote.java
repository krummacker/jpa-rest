package de.krummacker.jpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SuppressWarnings("all")
    private Long id;

    private int deliveryNoteNumber;
    private int shopNumber;
    private Date shippingDate;

    @OneToMany(mappedBy = "container", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<DeliveryNoteItem> items = new ArrayList<>();

    /**
     * This constructor is used by the JPA framework.
     */
    @SuppressWarnings("all")
    protected DeliveryNote() {
    }

    public DeliveryNote(int deliveryNoteNumber, int shopNumber, Date shippingDate) {
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.shopNumber = shopNumber;
        this.shippingDate = shippingDate;
    }

    @Override
    public String toString() {
        return String.format(
                "DeliveryNote[id=%d, deliveryNoteNumber='%s', shopNumber='%s', shippingDate='%s']",
                id, deliveryNoteNumber, shopNumber, shippingDate);
    }

    public Long getId() {
        return id;
    }

    public int getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public int getShopNumber() {
        return shopNumber;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public List<DeliveryNoteItem> getItems() {
        return items;
    }

    public void addItem(DeliveryNoteItem item) {
        this.items.add(item);
        if (item.getContainer() != this) {
            item.setContainer(this);
        }
    }
}

