package dev.walquinga.employee_ws.dtos;

import dev.walquinga.employee_ws.models.enums.Status;

public record DepartmentDto(Long id, String name, Status status) {
}
