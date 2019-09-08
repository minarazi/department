package com.example.department.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
