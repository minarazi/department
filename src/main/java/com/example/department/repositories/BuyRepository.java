package com.example.department.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.domain.Buy;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Long> {

	List<Buy> findByPerson_Id(Long personId);
}
