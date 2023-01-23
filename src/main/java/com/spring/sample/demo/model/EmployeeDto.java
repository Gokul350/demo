package com.spring.sample.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EmployeeDto {
	
	private Integer id;
	private String employeeName;
	
}
