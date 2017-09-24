package de.krummacker.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonProperty(access = WRITE_ONLY)
    private Employee owner;

    protected Phone() {
    }

    public Phone(Employee owner, String number) {
        this.number = number;
        setOwner(owner);
    }

    @Override
    public String toString() {
        return number;
    }

    public Long getId() {
        return id;
    }

    public Employee getOwner() {
        return owner;
    }

    public String getNumber() {
        return number;
    }

    public void setOwner(Employee employee) {
        this.owner = employee;
        // warning this may cause performance issues if you have a large data set since this operation is O(n)
        if (employee != null && !employee.getPhones().contains(this)) {
            employee.getPhones().add(this);
        }
    }
}

