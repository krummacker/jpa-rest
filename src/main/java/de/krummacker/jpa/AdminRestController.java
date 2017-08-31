package de.krummacker.jpa;

import de.krummacker.jpa.model.*;
import de.krummacker.jpa.repositories.CustomerRepository;
import de.krummacker.jpa.repositories.DeliveryNoteRepository;
import de.krummacker.jpa.repositories.DepartmentRepository;
import de.krummacker.jpa.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.UUID;

@RestController
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

    @Value("${setup.users}")
    private int setupUsers;

    @RequestMapping(value = "/admin/shutdown", method = RequestMethod.PUT)
    public void shutdown() {
        log.info("Received shutdown request. Exiting.");
        System.exit(0);
    }

    @RequestMapping(value = "/admin/setup", method = RequestMethod.PUT)
    public void setup() {

        customerRepository.save(new Customer("Jack", "Bauer", Customer.Rating.AVOID));
        customerRepository.save(new Customer("Chloe", "O'Brian", Customer.Rating.NEW));
        customerRepository.save(new Customer("Kim", "Bauer", Customer.Rating.TRUSTED));
        customerRepository.save(new Customer("David", "Palmer", Customer.Rating.AVOID));
        customerRepository.save(new Customer("Michelle", "Dessler", Customer.Rating.NEW));
        customerRepository.save(new Customer("Dirk", "Krummacker", Customer.Rating.TRUSTED));

        for (int i = 0; i < setupUsers; ++i) {
            String firstName = UUID.randomUUID().toString();
            String lastName = UUID.randomUUID().toString();
            customerRepository.save(new Customer(firstName, lastName, Customer.Rating.NEW));
        }

        DeliveryNote deliveryNote = new DeliveryNote(123456, 122, Calendar.getInstance().getTime());
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 123456, "Bracelet"));
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 3, 532734, "Earring"));
        deliveryNote.addItem(new DeliveryNoteItem(deliveryNote, 2, 343423, "Ring"));
        deliveryNoteRepository.save(deliveryNote);

        Employee rafferty = new Employee("Jacob", "Rafferty");
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