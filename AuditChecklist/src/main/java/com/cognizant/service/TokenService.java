package com.cognizant.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {

	Boolean checkTokenValidity(String token);
	
}