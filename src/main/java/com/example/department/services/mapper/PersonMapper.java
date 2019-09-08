package com.example.department.services.mapper;

import org.mapstruct.Mapper;

import com.example.department.domain.Person;
import com.example.department.dto.PersonDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	Person dtoToEntity(PersonDTO personDTO);
	PersonDTO entityToDto(Person person);
}
