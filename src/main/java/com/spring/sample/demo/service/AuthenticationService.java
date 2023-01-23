package com.spring.sample.demo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.sample.demo.config.JwtService;
import com.spring.sample.demo.model.AuthenticationResponse;
import com.spring.sample.demo.model.RegisterRequest;
import com.spring.sample.demo.model.User;
import com.spring.sample.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest registerRequest) {

		var user = User.builder().email(registerRequest.getEmail())
				.password(passwordEncoder.encode(registerRequest.getPassword())).build();

		userRepository.save(user);

		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public AuthenticationResponse authenticate(RegisterRequest registerRequest) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword())

		);

		var user = userRepository.findByEmail(registerRequest.getEmail());

		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();

	}

}
