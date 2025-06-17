package dev.walquinga.employee_ws.services;

import dev.walquinga.employee_ws.dtos.EmployeeDto;
import dev.walquinga.employee_ws.mappers.EmployeeMapper;
import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.models.Employee;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private EmployeeMapper mapper;

    @Test
    void create_shouldPersistEmployeeWithDepartment() {
        EmployeeDto employeeDto = new EmployeeDto(
                1L,
                "John",
                "Doe",
                30,
                "Desarrollador",
                BigDecimal.valueOf(1000L),
                LocalDate.now(),
                null,
                Status.A);
        Department department = new Department();
        Employee employee = new Employee();
        when(departmentService.getById(1L)).thenReturn(department);
        when(mapper.toEntity(employeeDto)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);
        when(mapper.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = service.create(1L, employeeDto);

        assertEquals(employeeDto, result);
        assertEquals(department, employee.getDepartment());
        assertEquals(Status.A, employee.getStatus());
    }

    @Test
    void delete_shouldSetInactiveStatusAndEndDate() {
        Employee employee = new Employee();
        employee.setStatus(Status.A);
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        service.delete(1L);

        assertEquals(Status.I, employee.getStatus());
        assertNotNull(employee.getEndDate());
        verify(repository).save(employee);
    }

    @Test
    void findHighestSalary_shouldReturnCorrectEmployee() {
        Employee employee = new Employee();
        employee.setSalary(new BigDecimal("2000"));
        Employee employee1 = new Employee();
        employee1.setSalary(new BigDecimal("3000"));
        when(repository.findAll()).thenReturn(List.of(employee, employee1));
        when(mapper.toDto(employee1)).thenReturn(new EmployeeDto(
                1L,
                "John",
                "Doe",
                30,
                "Desarrollador",
                BigDecimal.valueOf(1000L),
                LocalDate.now(),
                null,
                Status.A));

        EmployeeDto result = service.findEmployeeWithHighestSalary();
        assertNotNull(result);
    }

    @Test
    void countEmployeesHiredLastMonth_shouldReturnCount() {
        LocalDate now = LocalDate.now();
        Employee employee = new Employee();
        employee.setStartDate(now.minusDays(10));
        Employee employee1 = new Employee();
        employee1.setStartDate(now.minusMonths(2));
        when(repository.findAll()).thenReturn(List.of(employee, employee1));

        long count = service.countEmployeesHiredLastMonth();
        assertEquals(1L, count);
    }
}
