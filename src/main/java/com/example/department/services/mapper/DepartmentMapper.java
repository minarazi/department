package com.example.department.services.mapper;

import org.mapstruct.Mapper;

import com.example.department.domain.Department;
import com.example.department.dto.DepartmentDTO;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

	Department dtoToEntity(DepartmentDTO departmentDTO);
	DepartmentDTO entityToDto(Department department);
	
}
