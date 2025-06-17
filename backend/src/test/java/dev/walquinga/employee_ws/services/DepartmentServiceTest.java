package dev.walquinga.employee_ws.services;

import dev.walquinga.employee_ws.dtos.DepartmentDto;
import dev.walquinga.employee_ws.mappers.DepartmentMapper;
import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService service;

    @Mock
    private DepartmentRepository repository;

    @Mock
    private DepartmentMapper mapper;

    @Test
    void create_shouldSaveWithActiveStatus() {
        DepartmentDto departmentDto = new DepartmentDto(null, "TI", Status.A);
        Department department = new Department();
        DepartmentDto departmentDtoExpected = new DepartmentDto(1L, "TI", Status.A);

        when(mapper.toEntity(departmentDto)).thenReturn(department);
        when(repository.save(department)).thenReturn(department);
        when(mapper.toDto(department)).thenReturn(departmentDtoExpected);

        DepartmentDto departmentDtoResult = service.create(departmentDto);

        assertEquals(Status.A, department.getStatus());
        assertEquals(departmentDtoExpected, departmentDtoResult);
    }

    @Test
    void delete_shouldSetStatusInactive() {
        Department department = new Department();
        department.setStatus(Status.A);

        when(repository.findById(1L)).thenReturn(Optional.of(department));

        service.delete(1L);

        assertEquals(Status.I, department.getStatus());
        verify(repository).save(department);
    }

    @Test
    void getAll_shouldReturnDtos() {
        List<Department> departments = List.of(new Department());
        List<DepartmentDto> departmentDtos = List.of(new DepartmentDto(null, "TI", Status.A),
                new DepartmentDto(null, "HHRR", Status.I));

        when(repository.findAll()).thenReturn(departments);
        when(mapper.toDtos(departments)).thenReturn(departmentDtos);

        assertEquals(departmentDtos, service.getAll());
    }

    @Test
    void getAllActive_shouldFilterActive() {
        List<Department> list = List.of(new Department());
        List<DepartmentDto> dtos = List.of(new DepartmentDto(null, "TI", Status.A),
                new DepartmentDto(null, "HHRR", Status.I));

        when(repository.findByStatus(Status.A)).thenReturn(list);
        when(mapper.toDtos(list)).thenReturn(dtos);

        assertEquals(dtos, service.getAllActive());
    }
}
