package com.example.department.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

import com.example.department.domain.Buy;
import com.example.department.dto.BuyDTO;
import com.example.department.services.PersonService;
import com.example.department.services.ProductService;

@Mapper(componentModel = "spring", uses = { PersonService.class, ProductService.class })
public interface BuyMapper {

	@Mappings({ @Mapping(source = "personId", target = "person"), @Mapping(source = "productId", target = "product") })
	Buy dtoToEntity(BuyDTO buyDTO);

	@Mappings({ @Mapping(source = "person.id", target = "personId"),
			@Mapping(source = "product.id", target = "productId") })
	BuyDTO entityToDto(Buy buy);
}
