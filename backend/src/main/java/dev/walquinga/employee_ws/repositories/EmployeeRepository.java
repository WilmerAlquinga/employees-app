package dev.walquinga.employee_ws.repositories;

import dev.walquinga.employee_ws.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
