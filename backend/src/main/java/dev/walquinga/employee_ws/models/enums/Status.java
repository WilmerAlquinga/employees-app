package dev.walquinga.employee_ws.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    A("Activo"),
    I("Inactivo");

    private final String label;
}
