package com.cognizant.feignclients;

import org.springframework.http.ResponseEntity;

import com.cognizant.model.ProjectManager;
import com.cognizant.model.User;

public class AuthClientFallback implements AuthClient {

	@Override
	public ResponseEntity<ProjectManager> login(User userlogincredentials) {
		return null;
	}

	@Override
	public ResponseEntity<?> getValidity(String token) {
		return null;
	}

}
