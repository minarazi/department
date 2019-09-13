package com.example.department.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.domain.BuyInvoice;

@Repository
public interface BuyInvoiceRepository extends JpaRepository<BuyInvoice, Long> {

	List<BuyInvoice> findByPerson_Id(Long personId);

	List<BuyInvoice> findByPerson_Department_Id(Long departmentId);
}
