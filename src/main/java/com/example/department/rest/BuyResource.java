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

import com.example.department.dto.BuyDTO;
import com.example.department.services.BuyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/buy")
public class BuyResource {

	private final BuyService buyService;

	@GetMapping
	public ResponseEntity<List<BuyDTO>> findAllBuy() {
		return ResponseEntity.ok(buyService.findAllBuy());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BuyDTO> findBuyById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(buyService.findBuyById(id));
	}

	@PostMapping
	public ResponseEntity<BuyDTO> createBuy(@RequestBody BuyDTO buyDTO) {
		return new ResponseEntity<>(buyService.createBuy(buyDTO), HttpStatus.CREATED);
	}
}
