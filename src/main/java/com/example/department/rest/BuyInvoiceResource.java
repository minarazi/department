package com.example.department.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.dto.BuyInvoiceDTO;
import com.example.department.dto.DepartmentBuyInvoiceDTO;
import com.example.department.services.BuyInvoiceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/buy-invoices")
public class BuyInvoiceResource {

	private final BuyInvoiceService buyInvoiceService;

	@GetMapping
	public ResponseEntity<List<BuyInvoiceDTO>> findAllBuyInvoice() {
		return ResponseEntity.ok(buyInvoiceService.findAllBuyInvoice());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BuyInvoiceDTO> findBuyInvoiceById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(buyInvoiceService.findBuyInvoiceById(id));
	}

	@PostMapping
	public ResponseEntity<BuyInvoiceDTO> createBuyInvoice(@RequestBody BuyInvoiceDTO buyInvoiceDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(buyInvoiceService.createBuyInvoice(buyInvoiceDTO));
	}

	@GetMapping(value = "/person/{personId}")
	public ResponseEntity<List<BuyInvoiceDTO>> findByPersonId(@PathVariable("personId") Long personId) {
		return ResponseEntity.ok(buyInvoiceService.findByPersonId(personId));
	}

	@GetMapping(value = "/department/{departmentId}")
	public ResponseEntity<List<BuyInvoiceDTO>> findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
		return ResponseEntity.ok(buyInvoiceService.findByDepartmentId(departmentId));
	}

	@GetMapping(value = "/department/{departmentId}/report")
	public ResponseEntity<DepartmentBuyInvoiceDTO> getAllBuyInvoiceAmountByDepartmentId(
			@PathVariable("departmentId") Long departmentId) {
		return ResponseEntity.ok(buyInvoiceService.getAllBuyInvoiceAmountAndPriceByDepartmentId(departmentId));
	}
}
