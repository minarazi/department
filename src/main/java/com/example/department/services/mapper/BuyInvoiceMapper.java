package com.example.department.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.department.domain.BuyInvoice;
import com.example.department.dto.BuyInvoiceDTO;
import com.example.department.services.PersonService;
import com.example.department.services.ProductService;

@Mapper(componentModel = "spring", uses = { PersonService.class, ProductService.class })
public interface BuyInvoiceMapper {

	@Mapping(source = "personId", target = "person")
	@Mapping(source = "productId", target = "product")
	BuyInvoice dtoToEntity(BuyInvoiceDTO buyInvoiceDTO);

	@Mapping(source = "person.id", target = "personId")
	@Mapping(source = "product.id", target = "productId")
	BuyInvoiceDTO entityToDto(BuyInvoice buyInvoice);
}
