package com.cognizant.feignclient;

import org.springframework.http.ResponseEntity;

import com.cognizant.pojo.AuthResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthClientFallback implements AuthClient{

	@Override
	public ResponseEntity<AuthResponse> getValidity(String token) {
		log.warn("Audit - authentication service down...!");
		return null;
	}

}
