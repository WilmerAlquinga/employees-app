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

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto create(Long departmentId, EmployeeDto employeeDto) {
        Department department = departmentService.getById(departmentId);
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);
        employee.setStatus(Status.A);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void delete(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el empleado con ID: " + employeeId));
        employee.setStatus(Status.I);
        employee.setEndDate(LocalDate.now());
        employeeRepository.save(employee);
    }

    public List<EmployeeDto> getAll() {
        return employeeMapper.toDtos(employeeRepository.findAll());
    }

    public EmployeeDto findEmployeeWithHighestSalary() {
        return employeeMapper.toDto(employeeRepository.findAll().stream()
                .filter(e -> e.getSalary() != null)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null));
    }

    public EmployeeDto findEmployeeWithLowerAge() {
        return employeeMapper.toDto(employeeRepository.findAll().stream()
                .min(Comparator.comparing(Employee::getAge))
                .orElse(null));
    }

    public long countEmployeesHiredLastMonth() {
        LocalDate date = LocalDate.now().minusMonths(1);
        return employeeRepository.findAll().stream()
                .filter(e -> e.getStartDate() != null && e.getStartDate().isAfter(date))
                .count();
    }
}
