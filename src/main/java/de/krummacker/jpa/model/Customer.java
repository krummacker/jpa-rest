package de.krummacker.jpa.model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Person {

    public enum Rating {
        TRUSTED, NEW, AVOID
    }

    private Rating rating;

    protected Customer() {
    }

    public Customer(String firstName, String lastName, Rating rating) {
        super(firstName, lastName);
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', rating='%s']",
                getId(), getFirstName(), getLastName(), rating);
    }
}

