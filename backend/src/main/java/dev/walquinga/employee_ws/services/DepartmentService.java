package dev.walquinga.employee_ws.services;

import dev.walquinga.employee_ws.dtos.DepartmentDto;
import dev.walquinga.employee_ws.mappers.DepartmentMapper;
import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.models.enums.Status;
import dev.walquinga.employee_ws.repositories.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public Department create(DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        department.setStatus(Status.A);
        log.info("Creating Department {}", department);
        return departmentRepository.save(department);
    }

    public void delete(Long id) {
        Department department = this.getById(id);
        department.setStatus(Status.I);
        log.info("Deleting Department with id: {}", id);
        departmentRepository.save(department);
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el departamento con ID: " + id));
    }

    public List<Department> list() {
        return departmentRepository.findAll();
    }
}
