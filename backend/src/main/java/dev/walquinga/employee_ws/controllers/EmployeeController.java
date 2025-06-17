package dev.walquinga.employee_ws.controllers;

import dev.walquinga.employee_ws.dtos.EmployeeDto;
import dev.walquinga.employee_ws.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<EmployeeDto> create(
            @PathVariable Long departmentId,
            @RequestBody EmployeeDto employeeDto
    ) {
        return ResponseEntity.ok(employeeService.create(departmentId, employeeDto));
    }

    @PostMapping("/delete/{employeeId}")
    public ResponseEntity<Void> delete(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> list() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<EmployeeDto> highestSalary() {
        return ResponseEntity.ok(employeeService.findEmployeeWithHighestSalary());
    }

    @GetMapping("/lowerAge")
    public ResponseEntity<EmployeeDto> lowerAge() {
        return ResponseEntity.ok(employeeService.findEmployeeWithLowerAge());
    }

    @GetMapping("/countLastMonth")
    public ResponseEntity<Map<String, Long>> countLastMonth() {
        return ResponseEntity.ok(Map.of("count", employeeService.countEmployeesHiredLastMonth()));
    }
}
