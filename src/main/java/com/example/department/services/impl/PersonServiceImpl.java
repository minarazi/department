package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.department.domain.Person;
import com.example.department.dto.PersonDTO;
import com.example.department.repositories.PersonRepository;
import com.example.department.services.PersonService;
import com.example.department.services.mapper.PersonMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;

	@Override
	public PersonDTO findPersonById(Long id) {
		Optional<Person> personOptional = personRepository.findById(id);

		if (!personOptional.isPresent()) {
			throw new RuntimeException("Person not found!");
		}
		Person person = personOptional.get();
		PersonDTO personDTO = personMapper.entityToDto(person);
		return personDTO;
	}

	@Override
	public void deletePersonById(Long idToDelete) {
		Optional<Person> personOptional = personRepository.findById(idToDelete);

		if (!personOptional.isPresent()) {
			throw new RuntimeException("Person not found!");
		}
		personRepository.deleteById(idToDelete);
		log.info("deleted PersonId" + idToDelete);
	}

	@Override
	public List<PersonDTO> findAllPerson() {

		return personRepository.findAll().stream().map(personMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) {

		Person person = personMapper.dtoToEntity(personDTO);
		person = personRepository.save(person);

		log.info("created PersonId" + person.getId());

		return personMapper.entityToDto(person);
	}

	@Override
	public PersonDTO updatePerson(PersonDTO personDTO) {
		Person person = personMapper.dtoToEntity(personDTO);

		Optional<Person> personOptional = personRepository.findById(personDTO.getId());
		if (!personOptional.isPresent()) {

			throw new RuntimeException("Person not found!");

		}

		person = personRepository.save(person);

		log.info("updated PersonId" + person.getId());

		return personMapper.entityToDto(person);
	}

}