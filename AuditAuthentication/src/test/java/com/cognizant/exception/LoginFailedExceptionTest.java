package com.cognizant.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class LoginFailedExceptionTest {

	@Mock
	Environment env;

	@Test
	public void testInvalidAuthorizationException() {
		LoginFailedException loginFailedException = new LoginFailedException(env.getProperty("string.not.valid"));
		assertNotNull(loginFailedException);
	}

}
