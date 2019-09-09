package com.example.department.bootstrap;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.department.dto.CompanyDTO;
import com.example.department.dto.DepartmentDTO;
import com.example.department.dto.PersonDTO;
import com.example.department.dto.ProductDTO;
import com.example.department.services.CompanyService;
import com.example.department.services.DepartmentService;
import com.example.department.services.PersonService;
import com.example.department.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class DepartmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CompanyService companyService;
	private final DepartmentService departmentService;
	private final PersonService personService;
	private final ProductService productService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		CompanyDTO companyDto = companyService.createCompany(getCompany());

		DepartmentDTO departmentDTO = departmentService.createDepartment(getDepartment(companyDto.getId()));
		DepartmentDTO departmentDTO1 = departmentService.createDepartment(getDepartment1(companyDto.getId()));
		DepartmentDTO departmentDTO2 = departmentService.createDepartment(getDepartment2(companyDto.getId()));

		personService.createPerson(getPerson(departmentDTO.getId()));
		personService.createPerson(getPerson1(departmentDTO1.getId()));
		personService.createPerson(getPerson2(departmentDTO2.getId()));
		personService.createPerson(getPerson3(departmentDTO.getId()));

		productService.createProduct(getProduct());
		productService.createProduct(getProduct1());
		productService.createProduct(getProduct2());
		productService.createProduct(getProduct3());
		productService.createProduct(getProduct4());
		productService.createProduct(getProduct5());
		productService.createProduct(getProduct6());

		log.debug("Loading Bootstrap data");

	}

	private CompanyDTO getCompany() {

		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setName("Fanap");
		return companyDTO;

	}

	private DepartmentDTO getDepartment(Long companyId) {

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		departmentDTO.setName("IT");
		return departmentDTO;

	}

	private DepartmentDTO getDepartment1(Long companyId) {

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		departmentDTO.setName("Sell");
		return departmentDTO;

	}

	private DepartmentDTO getDepartment2(Long companyId) {

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		departmentDTO.setName("Network");
		return departmentDTO;

	}

	private PersonDTO getPerson(Long departmentId) {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setAge(21);
		personDTO.setDepartmentId(departmentId);
		personDTO.setFamily("razi");
		personDTO.setName("mina");
		return personDTO;

	}

	private PersonDTO getPerson1(Long departmentId) {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setAge(23);
		personDTO.setDepartmentId(departmentId);
		personDTO.setFamily("hasani");
		personDTO.setName("niloofar");
		return personDTO;

	}

	private PersonDTO getPerson2(Long departmentId) {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setAge(25);
		personDTO.setDepartmentId(departmentId);
		personDTO.setFamily("khoran");
		personDTO.setName("zahra");
		return personDTO;

	}

	private PersonDTO getPerson3(Long departmentId) {

		PersonDTO personDTO = new PersonDTO();
		personDTO.setAge(20);
		personDTO.setDepartmentId(departmentId);
		personDTO.setFamily("nematZade");
		personDTO.setName("mohadeseh");
		return personDTO;

	}

	private ProductDTO getProduct() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(6);
		productDTO.setName("IPhone 6s");
		productDTO.setPrice(100);
		return productDTO;

	}

	private ProductDTO getProduct1() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(10);
		productDTO.setName("MacBook");
		productDTO.setPrice(2000);
		return productDTO;

	}

	private ProductDTO getProduct2() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(3);
		productDTO.setName("Ipad");
		productDTO.setPrice(60);
		return productDTO;

	}

	private ProductDTO getProduct3() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(1);
		productDTO.setName("BMW");
		productDTO.setPrice(6000);
		return productDTO;

	}

	private ProductDTO getProduct4() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(2);
		productDTO.setName("TV");
		productDTO.setPrice(250);
		return productDTO;

	}

	private ProductDTO getProduct5() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(3);
		productDTO.setName("samsung s8");
		productDTO.setPrice(200);
		return productDTO;

	}

	private ProductDTO getProduct6() {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(2);
		productDTO.setName("Cat");
		productDTO.setPrice(180);
		return productDTO;

	}

}
