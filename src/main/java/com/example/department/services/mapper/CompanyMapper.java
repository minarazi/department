package com.example.department.services.mapper;

import org.mapstruct.Mapper;

import com.example.department.domain.Company;
import com.example.department.dto.CompanyDTO;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	Company dtoToEntity(CompanyDTO companyDTO);

	CompanyDTO entityToDto(Company company);

}
