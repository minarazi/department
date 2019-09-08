package com.example.department.services;

import java.util.List;

import com.example.department.dto.CompanyDTO;

public interface CompanyService {

	public CompanyDTO findCompanyById(Long id);

	public void deleteCompanyById(Long idToDelete);

	public List<CompanyDTO> findAllCompany();

	public CompanyDTO createCompany(CompanyDTO companyDTO);

	public CompanyDTO updateCompany(CompanyDTO companyDTO);

}
