package de.krummacker.jpa;

import de.krummacker.jpa.model.*;
import de.krummacker.jpa.repositories.CustomerRepository;
import de.krummacker.jpa.repositories.DeliveryNoteRepository;
import de.krummacker.jpa.repositories.DepartmentRepository;
import de.krummacker.jpa.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.UUID;

/**
 * Default constructor, to be used by Spring.
 */
@RestController
@SuppressWarnings("all")
public class AdminRestController {

    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Creates a new instance of this class with all member fields set by the constructor's arguments. To be used in
     * unit tests.
     */
    AdminRestController(EmployeeRepository employeeRepository,
                        CustomerRepository customerRepository,
                        DeliveryNoteRepository deliveryNoteRepository,
                        DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Default constructor, to be used by Spring.
     */
    public AdminRestController() {
        // Intentionally left empty
    }

    @RequestMapping(value = "/admin/setup/{count}", method = RequestMethod.PUT)
    public void setup(@PathVariable Integer count) {
        log.info("Endpoint 'setup' called with parameter {}", count);

        for (int i = 0; i < count; ++i) {

            customerRepository.save(new Customer("Jack", UUID.randomUUID().toString(), Customer.Rating.AVOID));
            customerRepository.save(new Customer("Chloe", UUID.randomUUID().toString(), Customer.Rating.NEW));
            customerRepository.save(new Customer("Kim", UUID.randomUUID().toString(), Customer.Rating.TRUSTED));
            customerRepository.save(new Customer("David", UUID.randomUUID().toString(), Customer.Rating.AVOID));
            customerRepository.save(new Customer("Michelle", UUID.randomUUID().toString(), Customer.Rating.NEW));
            customerRepository.save(new Customer("Dirk", UUID.randomUUID().toString(), Customer.Rating.TRUSTED));

            DeliveryNote deliveryNote = new DeliveryNote(123456, 122, Calendar.getInstance().getTime());
            deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 123456, "Bracelet"));
            deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 3, 532734, "Earring"));
            deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 343423, "Ring"));
            deliveryNoteRepository.save(deliveryNote);

            Employee rafferty = new Employee("Jacob", UUID.randomUUID().toString());
            rafferty.addPhone(new Phone(rafferty, "123456"));
            rafferty.addPhone(new Phone(rafferty, "647632"));
            rafferty.addPhone(new Phone(rafferty, "987309"));

            Employee jones = new Employee("Bill", "Jones");
            Employee heisenberg = new Employee("Werner", "Heisenberg");
            Employee robinson = new Employee("Herbert", "Robinson");
            Employee smith = new Employee("Jane", "Smith");
            Employee williams = new Employee("Linda", "Williams");

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
        }
    }
}