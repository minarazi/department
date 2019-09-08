package com.example.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
