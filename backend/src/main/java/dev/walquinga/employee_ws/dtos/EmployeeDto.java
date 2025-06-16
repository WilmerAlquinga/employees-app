package dev.walquinga.employee_ws.dtos;

import dev.walquinga.employee_ws.models.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        String role,
        BigDecimal salary,
        LocalDate startDate,
        LocalDate endDate,
        Status status) {
}
