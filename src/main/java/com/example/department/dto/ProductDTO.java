package com.example.department.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
	private Long id;
	private String name;
	private Integer price;
	private Integer amount;
}
