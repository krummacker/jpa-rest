package de.krummacker.jpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SuppressWarnings("all")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Employee> members = new ArrayList<>();

    /**
     * This constructor is used by the JPA framework.
     */
    @SuppressWarnings("all")
    protected Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Department[id=%d, name='%s']",
                id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void addMember(Employee member) {
        if (member.getDepartment() != this) {
            this.members.add(member);
            member.setDepartment(this);
        }
    }
}

