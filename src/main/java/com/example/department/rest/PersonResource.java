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

import com.example.department.dto.PersonDTO;
import com.example.department.services.PersonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonResource {
	private final PersonService personService;

	@GetMapping
	public ResponseEntity<List<PersonDTO>> findAllPerson() {
		return ResponseEntity.ok(personService.findAllPerson());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePersonById(@PathVariable("id") Long idToDelet) {
		personService.deletePersonById(idToDelet);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(personService.findPersonById(id));
	}

	@PostMapping
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(personDTO));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDTO personDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.updatePerson(personDTO));
	}
}
