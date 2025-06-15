package dev.walquinga.employee_ws.controllers;

import dev.walquinga.employee_ws.dtos.DepartmentDto;
import dev.walquinga.employee_ws.models.Department;
import dev.walquinga.employee_ws.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department> create(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.create(departmentDto));
    }

    @PostMapping("/delete/{departmentId}}")
    public ResponseEntity<Void> delete(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Department>> list() {
        return ResponseEntity.ok(departmentService.list());
    }
}
