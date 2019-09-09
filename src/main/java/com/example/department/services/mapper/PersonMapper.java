package com.example.department.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.department.domain.Person;
import com.example.department.dto.PersonDTO;
import com.example.department.services.DepartmentService;

@Mapper(componentModel = "spring", uses = { DepartmentService.class })
public interface PersonMapper {

	@Mapping(source = "departmentId", target = "department")
	Person dtoToEntity(PersonDTO personDTO);

	@Mapping(source = "department.id", target = "departmentId")
	PersonDTO entityToDto(Person person);
}
