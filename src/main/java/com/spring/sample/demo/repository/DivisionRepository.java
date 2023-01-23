package com.spring.sample.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sample.demo.model.Division;

public interface DivisionRepository extends JpaRepository<Division, Integer> {

}
