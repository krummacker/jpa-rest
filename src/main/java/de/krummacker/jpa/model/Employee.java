package de.krummacker.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
public class Employee extends Person {

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Phone> phones = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonProperty(access = WRITE_ONLY)
    private Department department;

    protected Employee() {
    }

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, firstName='%s', lastName='%s', department='%s', phones='%s']",
                getId(), getFirstName(), getLastName(), getDepartment(), phones);
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        if (phone.getOwner() != this) {
            phone.setOwner(this);
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

