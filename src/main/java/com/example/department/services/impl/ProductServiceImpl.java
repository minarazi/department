package com.example.department.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public ProductDTO findProductById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new RuntimeException("Product not found!");
		}
		Product product = productOptional.get();
		ProductDTO productDTO = productMapper.entityToDto(product);
		return productDTO;
	}

	@Override
	public void deleteProductById(Long idToDelete) {
		Optional<Product> productOptional = productRepository.findById(idToDelete);

		if (!productOptional.isPresent()) {
			throw new RuntimeException("Product not found!");
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
			throw new RuntimeException("Product Not Found!");
		}

		product = productRepository.save(product);

		log.info("updated ProductId" + product.getId());

		return productMapper.entityToDto(product);

	}

	@Override
	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new RuntimeException("Product not found!");
		}
		return productOptional.get();
	}

}
