package com.example.department.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DepartmentBuyDTO {

	private Long departmentId;
	private Integer totalBuyAmount;
	private Integer totalBuyPrice;

}
