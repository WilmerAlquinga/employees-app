package dev.walquinga.employee_ws.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
        String firstName,
        String lastName,
        Integer age,
        String role,
        BigDecimal salary,
        LocalDate startDate,
        LocalDate endDate
) {
}
