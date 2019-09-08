package com.example.department.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.dto.CompanyDTO;
import com.example.department.services.CompanyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyResource {

	private final CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<CompanyDTO>> findAllCompany() {
		return ResponseEntity.ok(companyService.findAllCompany());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCompanyById(@PathVariable("id") Long id) {
		companyService.deleteCompanyById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(companyService.findCompanyById(id));
	}

	@PostMapping
	public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
		return new ResponseEntity<>(companyService.createCompany(companyDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDTO) {
		return ResponseEntity.ok(companyService.updateCompany(companyDTO));
	}
}
