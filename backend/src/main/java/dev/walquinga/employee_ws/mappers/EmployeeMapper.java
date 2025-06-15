package dev.walquinga.employee_ws.mappers;

import dev.walquinga.employee_ws.dtos.EmployeeDto;
import dev.walquinga.employee_ws.models.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

    List<EmployeeDto> toDtos(List<Employee> entities);
}
