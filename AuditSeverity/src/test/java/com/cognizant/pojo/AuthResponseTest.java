package com.cognizant.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthResponseTest {

	@Test
	public void testNoArgsConstructor() {
		AuthResponse response = new AuthResponse();
		assertNotNull(response);
		assertNull(response.getUserId());
		assertFalse(response.isValid());
	}
	
	@Test
	public void testAllArgsContructor() {
		AuthResponse response = new AuthResponse("user",true);
		assertNotNull(response);
		assertEquals("user",response.getUserId());
		assertTrue(response.isValid());
	}
	
	@Test
	void testGettersAndSetters() {
		AuthResponse response = new AuthResponse();
		assertNotNull(response);
		assertNull(response.getUserId());
		response.setUserId("user");
		assertEquals("user",response.getUserId());
		assertFalse(response.isValid());
		response.setValid(true);
		assertTrue(response.isValid());
	}

}
