package com.example.department.services;

import java.util.List;

import com.example.department.dto.DepartmentDTO;

public interface DepartmentService {
	public DepartmentDTO findDepartmentById(Long id);

	public void deleteDepartmentById(Long idToDelete);

	public	List<DepartmentDTO> findAllDepartment();

	public	DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

	public	DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

}
