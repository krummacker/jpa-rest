package de.krummacker.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
public class DeliveryNoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;
    private int articleNumber;
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINER_ID")
    @JsonProperty(access = WRITE_ONLY)
    private DeliveryNote container;

    protected DeliveryNoteItem() {
    }

    public DeliveryNoteItem(DeliveryNote container, int amount, int articleNumber, String productName) {
        this.amount = amount;
        this.articleNumber = articleNumber;
        this.productName = productName;
        setContainer(container);
    }

    @Override
    public String toString() {
        return String.format(
                "DeliveryNoteItem[id=%d, deliveryNoteNumber='%s', amount='%s', articleNumber='%s', articleName='%s']",
                id, container.getDeliveryNoteNumber(), amount, articleNumber, productName);
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getProductName() {
        return productName;
    }

    public DeliveryNote getContainer() {
        return container;
    }

    public void setContainer(DeliveryNote container) {
        this.container = container;
        // warning this may cause performance issues if you have a large data set since this operation is O(n)
        if (!container.getItems().contains(this)) {
            container.getItems().add(this);
        }
    }
}

