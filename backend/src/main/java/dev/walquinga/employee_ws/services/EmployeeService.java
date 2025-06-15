package dev.walquinga.employee_ws.services;

import dev.walquinga.employee_ws.dtos.EmployeeDto;
import dev.walquinga.employee_ws.mappers.EmployeeMapper;
import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.models.Employee;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    public Employee create(Long departmentId, EmployeeDto employeeDto) {
        Department department = departmentService.getById(departmentId);
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);
        employee.setStatus(Status.A);

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el empleado con ID: " + id));
        emp.setStatus(Status.I);
        employeeRepository.save(emp);
    }

    public List<Employee> list() {
        return employeeRepository.findAll();
    }
}
