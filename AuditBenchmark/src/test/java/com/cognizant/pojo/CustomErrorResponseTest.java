package com.cognizant.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CustomErrorResponseTest {

	@Test
	public void testNoArgsConstructor() {
		CustomErrorResponse response = new CustomErrorResponse();
		assertNotNull(response);

		assertNull(response.getMessage());
		assertNull(response.getReason());
		assertNull(response.getTimestamp());
		assertNull(response.getStatus());
	}

	@Test
	public void testAllArgsContructor() {
		CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.ACCEPTED, "Reason",
				"message");
		assertNotNull(response);

		assertNotNull(response.getMessage());
		assertNotNull(response.getReason());
		assertNotNull(response.getTimestamp());
		assertNotNull(response.getStatus());
	}

	@Test
	public void testGetStatus() {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, customErrorResponse.getStatus());
	}

	@Test
	public void testGetMessage() {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");
		assertEquals("Message", customErrorResponse.getMessage());
	}

	@Test
	public void testSetLocalDateTime() {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setTimestamp(LocalDateTime.now());
	}

	@Test
	public void testSetStatus() {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);
	}

	@Test
	public void testSetMessage() {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");
	}

}
