package com.example.department.services;

import java.util.List;

import com.example.department.dto.ProductDTO;

public interface ProductService {
	public ProductDTO findProductById(Long id);

	public void deleteProductById(Long idToDelete);

	public List<ProductDTO> findAllProduct();

	public ProductDTO createProduct(ProductDTO productDTO);

	public ProductDTO updateProduct(ProductDTO productDTO);

}