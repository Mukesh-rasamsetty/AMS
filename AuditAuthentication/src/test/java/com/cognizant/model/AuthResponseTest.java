package com.cognizant.model;

import static org.junit.Assert.*;

/**
 * @author POD5
 *  For testing the AuthResponse 
 * 
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthResponseTest {

	AuthResponse authResponse = new AuthResponse();

	@Test
	public void testAuthResponseConstructor() {
		AuthResponse response = new AuthResponse("abc", true);
		assertEquals("abc", response.getUserId());
	}

	@Test
	public void testUid() {
		authResponse.setUserId("abc");
		assertEquals("abc", authResponse.getUserId());
	}

	@Test
	public void testIsValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

	@Test
	public void testtoString() {
		String s = authResponse.toString();
		assertEquals(s, authResponse.toString());
	}

}
