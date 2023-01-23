package com.spring.sample.demo.service;

import java.util.List;

import com.spring.sample.demo.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployee();
	
	public void saveEmployee(Employee employee);

}
