package dev.walquinga.employee_ws.repositories;

import dev.walquinga.employee_ws.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
