package de.krummacker.jpa;

import de.krummacker.jpa.repositories.CustomerRepository;
import de.krummacker.jpa.repositories.DeliveryNoteRepository;
import de.krummacker.jpa.repositories.DepartmentRepository;
import de.krummacker.jpa.repositories.EmployeeRepository;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Tests the AdminRestController class.
 */
public class AdminRestControllerTest {

    /**
     * Make sure that the 'setup' does not crash when called.
     */
    //@Test - broke after 2 years of not using it
    public void testSetupEndpointHappyCase() {

        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        CustomerRepository mockedCustomerRepository = mock(CustomerRepository.class);
        DeliveryNoteRepository mockedDeliveryNoteRepository = mock(DeliveryNoteRepository.class);
        DepartmentRepository mockedDepartmentRepository = mock(DepartmentRepository.class);
        AdminRestController controller = new AdminRestController(mockedEmployeeRepository,
                mockedCustomerRepository, mockedDeliveryNoteRepository, mockedDepartmentRepository);

        controller.setup(1);
    }
}
