package com.spring.sample.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.sample.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e where e.employeeDivisionId.id=:id")
	List<Employee> fetchEmployeeBasedOnDivision(@Param("id") Integer id);

}
