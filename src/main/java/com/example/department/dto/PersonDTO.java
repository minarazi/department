package com.example.department.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonDTO {
	private Long id;
	private String name;
	private String family;
	private Integer age;
	private Long departmentId;
}
