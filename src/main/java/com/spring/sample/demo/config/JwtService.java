package com.spring.sample.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "7A24432646294A404D635166546A576E5A7234753778214125442A472D4B6150";

	public String extractUsername(String token) {

		return extractclaim(token, Claims::getSubject);
	}

	public <T> T extractclaim(String token, Function<Claims, T> claimResolver) {

		final Claims claims = extractAllCliams(token);

		return claimResolver.apply(claims);

	}

	
	//starting point 
	
	public String generateToken(UserDetails userDetails) {

		return generateToken(new HashMap<>(), userDetails);
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {

		final String userName = extractUsername(token);

		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

	private Boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractclaim(token, Claims::getExpiration);
	}

	public String generateToken(Map<String, Object> extractCliams, UserDetails userDetails) {

		return Jwts.builder().setClaims(extractCliams).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	private Claims extractAllCliams(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {

		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
