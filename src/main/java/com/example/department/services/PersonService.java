package com.example.department.services;

import java.util.List;

import com.example.department.domain.Person;
import com.example.department.dto.PersonDTO;

public interface PersonService {

	public PersonDTO findPersonById(Long id);

	public Person findById(Long id);

	public void deletePersonById(Long idToDelete);

	public List<PersonDTO> findAllPerson();

	public PersonDTO createPerson(PersonDTO personDTO);

	public PersonDTO updatePerson(PersonDTO personDTO);

}
