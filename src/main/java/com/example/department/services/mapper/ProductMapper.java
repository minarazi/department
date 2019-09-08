package com.example.department.services.mapper;

import org.mapstruct.Mapper;

import com.example.department.domain.Product;
import com.example.department.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product dtoToEntity(ProductDTO productDTO);

	ProductDTO entityToDto(Product product);
}
