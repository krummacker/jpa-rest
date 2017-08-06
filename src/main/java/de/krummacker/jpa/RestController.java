package de.krummacker.jpa;

import de.krummacker.jpa.model.*;
import de.krummacker.jpa.repositories.CustomerRepository;
import de.krummacker.jpa.repositories.DeliveryNoteRepository;
import de.krummacker.jpa.repositories.DepartmentRepository;
import de.krummacker.jpa.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/admin/shutdown", method = RequestMethod.PUT)
    public void shutdown() {
        log.info("Received shutdown request. Exiting.");
        System.exit(0);
    }

    @RequestMapping(value = "/admin/setup", method = RequestMethod.PUT)
    public void setup() {
        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer", Customer.Rating.AVOID));
        customerRepository.save(new Customer("Chloe", "O'Brian", Customer.Rating.NEW));
        customerRepository.save(new Customer("Kim", "Bauer", Customer.Rating.TRUSTED));
        customerRepository.save(new Customer("David", "Palmer", Customer.Rating.AVOID));
        customerRepository.save(new Customer("Michelle", "Dessler", Customer.Rating.NEW));
        customerRepository.save(new Customer("Dirk", "Krummacker", Customer.Rating.TRUSTED));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        Customer customer = customerRepository.findOne(1L);
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

        // fetch customers by last name
        log.info("Customer found with findByLastName('Bauer'):");
        log.info("--------------------------------------------");
        for (Customer bauer : customerRepository.findByLastName("Bauer")) {
            log.info(bauer.toString());
        }
        log.info("");

        // save a delivery note and a few items
        DeliveryNote deliveryNote = new DeliveryNote(123456, 122, Calendar.getInstance().getTime());
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 123456, "Bracelet"));
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 3, 532734, "Earring"));
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 343423, "Ring"));
        deliveryNoteRepository.save(deliveryNote);

        for (DeliveryNote note : deliveryNoteRepository.findAll()) {
            log.info(note.toString());
            for (DeliveryNoteItem item : note.getItems()) {
                log.info(item.toString());
            }
            log.info("");
        }
        log.info("");

        // save an employee and a few phone numbers
        Employee rafferty = new Employee("Jacob", "Rafferty");
        rafferty.addPhone(new Phone(rafferty, "123456"));
        rafferty.addPhone(new Phone(rafferty, "647632"));
        rafferty.addPhone(new Phone(rafferty, "987309"));

        // create a few more employees
        Employee jones = new Employee("Bill", "Jones");
        Employee heisenberg = new Employee("Werner", "Heisenberg");
        Employee robinson = new Employee("Herbert", "Robinson");
        Employee smith = new Employee("Jane", "Smith");
        Employee williams = new Employee("Linda", "Williams");

        // save a few departments and add a few employees from above
        Department sales = new Department("Sales");
        Department engineering = new Department("Engineering");
        Department clerical = new Department("Clerical");
        Department marketing = new Department("Marketing");

        sales.addMember(rafferty);
        engineering.addMember(jones);
        engineering.addMember(heisenberg);
        clerical.addMember(robinson);
        clerical.addMember(smith);

        departmentRepository.save(sales);
        departmentRepository.save(engineering);
        departmentRepository.save(clerical);
        departmentRepository.save(marketing);

        employeeRepository.save(rafferty);
        employeeRepository.save(jones);
        employeeRepository.save(heisenberg);
        employeeRepository.save(robinson);
        employeeRepository.save(smith);
        employeeRepository.save(williams);

        for (Employee emp : employeeRepository.findAll()) {
            log.info(emp.toString());
        }
        log.info("");
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Customer customer(@RequestParam(value = "id", defaultValue = "1") Long id) throws ResourceNotFoundException {

        Customer result = customerRepository.findOne(id);

        if (result == null) {
            throw new ResourceNotFoundException(String.format("no customer found with id '%s'", id));
        }

        return result;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee employee(@RequestParam(value = "id", defaultValue = "1") Long id) throws ResourceNotFoundException {

        Employee result = employeeRepository.findOne(id);

        if (result == null) {
            throw new ResourceNotFoundException(String.format("no employee found with id '%s'", id));
        }

        return result;
    }

    @RequestMapping(value = "/deliverynote", method = RequestMethod.GET)
    public DeliveryNote deliverynote(@RequestParam(value = "id", defaultValue = "1") Long id) throws ResourceNotFoundException {

        DeliveryNote result = deliveryNoteRepository.findOne(id);

        if (result == null) {
            throw new ResourceNotFoundException(String.format("no deliverynote found with id '%s'", id));
        }

        return result;
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public Department department(@RequestParam(value = "id", defaultValue = "1") Long id) throws ResourceNotFoundException {

        Department result = departmentRepository.findOne(id);

        if (result == null) {
            throw new ResourceNotFoundException(String.format("no department found with id '%s'", id));
        }

        return result;
    }
}