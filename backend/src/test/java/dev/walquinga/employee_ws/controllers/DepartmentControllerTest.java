package dev.walquinga.employee_ws.controllers;

import dev.walquinga.employee_ws.dtos.DepartmentDto;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    @InjectMocks
    private DepartmentController controller;

    @Mock
    private DepartmentService service;

    @Test
    void create_shouldReturnDepartmentDto() {
        DepartmentDto departmentDto = new DepartmentDto(null, "TI", Status.A);
        DepartmentDto departmentDtoResult = new DepartmentDto(1L, "TI", Status.A);

        when(service.create(departmentDto)).thenReturn(departmentDtoResult);

        ResponseEntity<DepartmentDto> response = controller.create(departmentDto);

        assertEquals(departmentDtoResult, response.getBody());
    }

    @Test
    void delete_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service).delete(1L);
    }

    @Test
    void getAll_shouldReturnList() {
        List<DepartmentDto> departments = List.of(new DepartmentDto(1L, "TI", Status.A),
                new DepartmentDto(1L, "HHRR", Status.I));
        when(service.getAll()).thenReturn(departments);
        assertEquals(departments, controller.getAll().getBody());
    }

    @Test
    void getAllActive_shouldReturnList() {
        List<DepartmentDto> departments = List.of(new DepartmentDto(1L, "TI", Status.A));
        when(service.getAllActive()).thenReturn(departments);
        assertEquals(departments, controller.getAllActive().getBody());
    }
}
