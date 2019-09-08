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

import com.example.department.dto.ProductDTO;
import com.example.department.services.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductResource {
	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAllProduct() {
		return ResponseEntity.ok(productService.findAllProduct());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long idToDelet) {
		productService.deleteProductById(idToDelet);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findKeywordById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(productService.findProductById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateProduct(productDTO));
	}
}
