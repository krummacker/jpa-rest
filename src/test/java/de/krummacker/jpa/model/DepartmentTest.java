package de.krummacker.jpa.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Tests the Department data access object.
 */
public class DepartmentTest {

    /**
     * Make sure that the default constructor works.
     */
    @Test
    public void testDefaultConstructor() {
        Department department = new Department();
        Assert.assertEquals(department.toString(), "Department[id=null, name='null']");
        Assert.assertNull(department.getId());
        Assert.assertNull(department.getName());
    }

    /**
     * Make sure that the constructor with the name argument works.
     */
    @Test
    public void testArgConstructor() {
        Department department = new Department("Marketing");
        Assert.assertEquals(department.toString(), "Department[id=null, name='Marketing']");
        Assert.assertNull(department.getId());
        Assert.assertEquals(department.getName(), "Marketing");
    }

    /**
     * Make sure that we can add employees.
     */
    @Test
    public void testAddAndGetMembers() {
        Department department = new Department("Marketing");
        Employee employee = new Employee();
        department.addMember(employee);
        department.addMember(employee);
        List<Employee> employees = department.getMembers();
        Assert.assertEquals(employees.size(), 1);
        Assert.assertTrue(employees.contains(employee));
    }
}
