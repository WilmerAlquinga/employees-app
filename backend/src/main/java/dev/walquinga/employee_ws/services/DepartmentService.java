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

    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        department.setStatus(Status.A);
        log.info("Creating Department {}", department);
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public void delete(Long departmentId) {
        Department department = this.getById(departmentId);
        if (Status.I.equals(department.getStatus())) {
            log.warn("Department with departmentId: {} is already inactive", departmentId);
            return;
        }
        department.setStatus(Status.I);
        log.info("Deleting Department with departmentId: {}", departmentId);
        departmentRepository.save(department);
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el departamento con ID: " + id));
    }

    public List<DepartmentDto> getAll() {
        return departmentMapper.toDtos(departmentRepository.findAll());
    }

    public List<DepartmentDto> getAllActive() {
        return departmentMapper.toDtos(departmentRepository.findByStatus(Status.A));
    }
}
