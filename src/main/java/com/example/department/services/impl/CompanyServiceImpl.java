package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.department.domain.Company;
import com.example.department.dto.CompanyDTO;
import com.example.department.repositories.CompanyRepository;
import com.example.department.services.CompanyService;
import com.example.department.services.mapper.CompanyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;

	@Override
	public CompanyDTO findCompanyById(Long id) {
		Optional<Company> companyOptional = companyRepository.findById(id);

		if (!companyOptional.isPresent()) {
			throw new RuntimeException("Company not found!");
		}
		Company company = companyOptional.get();
		return companyMapper.entityToDto(company);
	}

	@Override
	public void deleteCompanyById(Long idToDelete) {
		Optional<Company> companyOptional = companyRepository.findById(idToDelete);

		if (!companyOptional.isPresent()) {
			throw new RuntimeException("Company not found!");
		}
		companyRepository.deleteById(idToDelete);
		log.info("deleted CompanyId" + idToDelete);
	}

	@Override
	public List<CompanyDTO> findAllCompany() {

		// companyRepository.findAll().stream().map(company->{
		// return companyMapper.entityToDto(company);
		// });t =

		return companyRepository.findAll().stream().map(companyMapper::entityToDto).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public CompanyDTO createCompany(CompanyDTO companyDTO) {

		Company company = companyMapper.dtoToEntity(companyDTO);
		company = companyRepository.save(company);

		log.info("created CompanyId" + company.getId());

		return companyMapper.entityToDto(company);
	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDTO) {
		Company company = companyMapper.dtoToEntity(companyDTO);
		Optional<Company> companyOptional = companyRepository.findById(companyDTO.getId());

		if (!companyOptional.isPresent()) {
			throw new RuntimeException("Company not found!");
		}
		company = companyRepository.save(company);

		log.info("updated CompanyId" + company.getId());

		return companyMapper.entityToDto(company);

	}

	@Override
	public Company findById(Long id) {
		Optional<Company> companyOptional = companyRepository.findById(id);

		if (!companyOptional.isPresent()) {
			throw new RuntimeException("Company not found!");
		}
		return companyOptional.get();
	}

}
