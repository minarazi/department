package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.department.domain.Department;
import com.example.department.dto.DepartmentDTO;
import com.example.department.repositories.DepartmentRepository;
import com.example.department.services.DepartmentService;
import com.example.department.services.mapper.DepartmentMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final DepartmentMapper departmentMapper;

	@Override
	public DepartmentDTO findDepartmentById(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		if (!departmentOptional.isPresent()) {
			throw new RuntimeException("Department not found!");
		}
		Department department = departmentOptional.get();
		DepartmentDTO departmentDTO = departmentMapper.entityToDto(department);
		return departmentDTO;
	}

	@Override
	public void deleteDepartmentById(Long idToDelete) {
		Optional<Department> departmentOptional = departmentRepository.findById(idToDelete);

		if (!departmentOptional.isPresent()) {
			throw new RuntimeException("Department not found!");
		}
		departmentRepository.deleteById(idToDelete);
		log.info("deleted DepartmentId" + idToDelete);
	}

	@Override
	public List<DepartmentDTO> findAllDepartment() {
		return departmentRepository.findAll().stream().map(departmentMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

		Department department = departmentMapper.dtoToEntity(departmentDTO);
		department = departmentRepository.save(department);

		log.info("created DepartmentId" + department.getId());

		return departmentMapper.entityToDto(department);
	}

	@Override
	public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
		Department department = departmentMapper.dtoToEntity(departmentDTO);

		Optional<Department> departmentOptional = departmentRepository.findById(departmentDTO.getId());

		if (!departmentOptional.isPresent()) {
			throw new RuntimeException("department not found!");
		}
		department = departmentRepository.save(department);

		log.info("updated DepartmentId" + department.getId());

		return departmentMapper.entityToDto(department);
	}

	@Override
	public Department findById(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		if (!departmentOptional.isPresent()) {
			throw new RuntimeException("Department not found!");
		}
		return departmentOptional.get();
	}

}
