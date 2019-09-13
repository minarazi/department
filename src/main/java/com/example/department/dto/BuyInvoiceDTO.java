package com.example.department.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BuyInvoiceDTO {
	
	private Long id;
	private int amount;
	private int totalPrice;
	private Long productId;
	private Long personId;

}
