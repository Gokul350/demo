package com.spring.sample.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.demo.model.AuthenticationResponse;
import com.spring.sample.demo.model.RegisterRequest;
import com.spring.sample.demo.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(

			@RequestBody RegisterRequest request) {

		return ResponseEntity.ok(authenticationService.register(request));

	}

	@PostMapping("/authenticate")

	public ResponseEntity<AuthenticationResponse> authenticate(

			@RequestBody RegisterRequest request) {

		return ResponseEntity.ok(authenticationService.authenticate(request));

	}

}
