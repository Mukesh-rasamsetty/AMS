package com.cognizant.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ContextConfiguration
@Slf4j
public class CustomErrorResponseTest {

	@Mock
	Environment env;

	@Test
	public void testCustomErrorResponse() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		assertNotNull(customErrorResponse);
	}

	@Test
	public void testCustomErrorResponseParameterized() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.OK, "Message",
				"reason");
		assertNotNull(customErrorResponse);
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testToStringMethod() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.OK, "Message",
				"reason");
		assertEquals(customErrorResponse.toString(), customErrorResponse.toString());
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testGetStatus() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, customErrorResponse.getStatus());
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testGetMessage() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");
		assertEquals("Message", customErrorResponse.getMessage());
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testSetLocalDateTime() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setTimestamp(LocalDateTime.now());
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testSetStatus() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);
		log.info(env.getProperty("string.end"));

	}

	@Test
	public void testSetMessage() {
		log.info(env.getProperty("string.start"));

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");
		log.info(env.getProperty("string.end"));

	}

}
