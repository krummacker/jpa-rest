package de.krummacker.jpa.repositories;

import de.krummacker.jpa.model.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
