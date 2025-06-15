package dev.walquinga.employee_ws.mappers;

import dev.walquinga.employee_ws.dtos.DepartmentDto;
import dev.walquinga.employee_ws.models.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentDto dto);

    DepartmentDto toDto(Department entity);

    List<DepartmentDto> toDtos(List<Department> entities);
}
