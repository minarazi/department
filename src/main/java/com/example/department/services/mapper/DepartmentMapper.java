package com.example.department.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.department.domain.Department;
import com.example.department.dto.DepartmentDTO;
import com.example.department.services.CompanyService;

@Mapper(componentModel = "spring", uses = { CompanyService.class })
public interface DepartmentMapper {

	@Mapping(source = "companyId", target = "company")
	Department dtoToEntity(DepartmentDTO departmentDTO);

	@Mapping(source = "company.id", target = "companyId")
	DepartmentDTO entityToDto(Department department);

}
