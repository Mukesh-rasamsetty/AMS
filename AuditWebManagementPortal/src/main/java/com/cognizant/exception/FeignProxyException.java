package com.cognizant.exception;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignProxyException extends Exception {

	private static final long serialVersionUID = 1L;
	@Autowired
	Environment env;

	public FeignProxyException() {
		super();
		log.info(env.getProperty("string.start"));
	}

}