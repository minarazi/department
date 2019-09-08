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

import com.example.department.dto.DepartmentDTO;
import com.example.department.services.DepartmentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentResource {
	private final DepartmentService departmentService;

	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> findAllDepartment() {
		return ResponseEntity.ok(departmentService.findAllDepartment());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDepartmentById(@PathVariable("id") Long id) {
		departmentService.deleteDepartmentById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> findDepartmentById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(departmentService.findDepartmentById(id));
	}

	@PostMapping
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
		return new ResponseEntity<>(departmentService.createDepartment(departmentDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id") Long id,
			@RequestBody DepartmentDTO departmentDTO) {
		return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO));
	}
}
