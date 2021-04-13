package com.cognizant.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author POD5
 * @version 1.8
 * @apiNote This class is used to hold the login credentials which will be sent
 *          by the user in the request body for getting the token
 *
 */
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ContextConfiguration
@Slf4j
public class UserCredentialsTest {

	UserCredentials loginCredential = new UserCredentials();

	@Mock
	Environment env;

	@Test
	public void testUserLoginCredentialAllConstructor() {
		log.info(env.getProperty("string.start"));
		UserCredentials credential = new UserCredentials("abc", "abc");
		assertEquals(credential.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}

	@Test
	public void testGetUid() {
		log.info(env.getProperty("string.start"));
		loginCredential.setUserId("abc");
		assertEquals(loginCredential.getUserId(), "abc");
		log.info(env.getProperty("string.end"));
	}

	@Test
	public void testUserPassword() {
		log.info(env.getProperty("string.start"));
		loginCredential.setPassword("abc");
		assertEquals(loginCredential.getPassword(), "abc");
		log.info(env.getProperty("string.end"));
	}

	@Test
	public void testoString() {
		log.info(env.getProperty("string.start"));
		String string = loginCredential.toString();
		assertEquals(loginCredential.toString(), string);
		log.info(env.getProperty("string.end"));
	}

}
