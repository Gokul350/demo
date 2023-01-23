package com.spring.sample.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.sample.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	UserDetails findByEmail(String username);

}
