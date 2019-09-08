package com.example.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company ,Long >{

}
