package dev.walquinga.employee_ws.repositories;

import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByStatus(Status status);
}
