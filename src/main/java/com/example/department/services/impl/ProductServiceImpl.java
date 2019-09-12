package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.department.domain.Product;
import com.example.department.dto.ProductDTO;
import com.example.department.repositories.ProductRepository;
import com.example.department.services.ProductService;
import com.example.department.services.mapper.ProductMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private static final String PRODUCT_NOT_FOUND = "Product not found!";

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public ProductDTO findProductById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
		}
		Product product = productOptional.get();

		return productMapper.entityToDto(product);
	}

	@Override
	public void deleteProductById(Long idToDelete) {
		Optional<Product> productOptional = productRepository.findById(idToDelete);

		if (!productOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
		}
		productRepository.deleteById(idToDelete);
		log.info("deleted ProductId" + idToDelete);
	}

	@Override
	public List<ProductDTO> findAllProduct() {

		return productRepository.findAll().stream().map(productMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {

		Product product = productMapper.dtoToEntity(productDTO);
		product = productRepository.save(product);

		log.info("created ProductId" + product.getId());

		return productMapper.entityToDto(product);
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {

		Product product = productMapper.dtoToEntity(productDTO);

		Optional<Product> productOptional = productRepository.findById(productDTO.getId());
		if (!productOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
		}

		product = productRepository.save(product);

		log.info("updated ProductId" + product.getId());

		return productMapper.entityToDto(product);

	}

	@Override
	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
		}
		return productOptional.get();
	}

	@Override
	public Product updateProduct(Product product) {

		log.info("updated ProductId" + product.getId());

		return productRepository.save(product);
	}

}
