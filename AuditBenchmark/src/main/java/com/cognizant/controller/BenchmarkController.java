package com.cognizant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.AuditBenchmark;
import com.cognizant.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BenchmarkController {

	@Autowired
	TokenService tokenService;

	@Autowired
	Environment env;

	@GetMapping("/AuditBenchmark")
	public ResponseEntity<?> getBenchmarkMap(@RequestHeader("Authorization") String token) {
		List<AuditBenchmark> auditBenchmarkList = new ArrayList<AuditBenchmark>();
		ResponseEntity<?> responseEntity = null;
		/*
		 * if(token == null) { responseEntity = new ResponseEntity<String>("Token Null",
		 * HttpStatus.FORBIDDEN); return responseEntity; }
		 */
		auditBenchmarkList.add(new AuditBenchmark("Internal", 3));
		auditBenchmarkList.add(new AuditBenchmark("SOX", 1));
		if (tokenService.checkTokenValidity(token)) {
			responseEntity = new ResponseEntity<List<AuditBenchmark>>(auditBenchmarkList, HttpStatus.OK);
		} else {
			log.error(env.getProperty("string.token.exp"));
			log.info(env.getProperty("string.end"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}
		return responseEntity;
	}

}