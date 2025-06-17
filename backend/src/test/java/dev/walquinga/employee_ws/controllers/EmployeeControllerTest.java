package dev.walquinga.employee_ws.controllers;

import dev.walquinga.employee_ws.dtos.EmployeeDto;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    @Test
    void create_shouldReturnEmployeeDto() {
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
        when(service.create(1L, employeeDto)).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = controller.create(1L, employeeDto);
        assertEquals(employeeDto, response.getBody());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service).delete(1L);
    }

    @Test
    void list_shouldReturnList() {
        List<EmployeeDto> employees = List.of(new EmployeeDto(
                1L,
                "John",
                "Doe",
                30,
                "Desarrollador",
                BigDecimal.valueOf(1000L),
                LocalDate.now(),
                null,
                Status.A));
        when(service.getAll()).thenReturn(employees);
        assertEquals(employees, controller.list().getBody());
    }

    @Test
    void highestSalary_shouldReturnEmployee() {
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
        when(service.findEmployeeWithHighestSalary()).thenReturn(employeeDto);
        assertEquals(employeeDto, controller.highestSalary().getBody());
    }

    @Test
    void lowerAge_shouldReturnEmployee() {
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
        when(service.findEmployeeWithLowerAge()).thenReturn(employeeDto);
        assertEquals(employeeDto, controller.lowerAge().getBody());
    }

    @Test
    void countLastMonth_shouldReturnMap() {
        when(service.countEmployeesHiredLastMonth()).thenReturn(2L);
        Map<String, Long> expected = Map.of("count", 2L);
        assertEquals(expected, controller.countLastMonth().getBody());
    }
}
