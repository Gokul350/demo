package com.spring.sample.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.demo.model.Division;
import com.spring.sample.demo.model.Employee;
import com.spring.sample.demo.model.EmployeeDto;
import com.spring.sample.demo.repository.DivisionRepository;
import com.spring.sample.demo.repository.EmployeeRepository;
import com.spring.sample.demo.service.EmployeeService;

@RestController
@RequestMapping("/emplyeemanagement")
public class CrudController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DivisionRepository divisionRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = "/hello")
	public String getName() {

		System.out.println("hello world");

		return "welcome";
	}

	@GetMapping("/divisions")
	public List<Division> getAllDivisions() {

		return divisionRepository.findAll();
	}
	
	

	@GetMapping("/employee")
	public List<Employee> getAllEmployee(@RequestParam Integer id) {
		
		
		List<Employee> employees  = employeeRepository.fetchEmployeeBasedOnDivision(id);
		
//		System.out.println(employees);
	
		return employees;
	}


	@PostMapping("/save")
	public void createEmployees(@RequestBody(required = false) Employee employee) {

		System.out.println(employee.getEmployeeName());

		employeeService.saveEmployee(employee);

	}

}
