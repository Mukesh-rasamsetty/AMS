package com.cognizant.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler handler;

	@Mock
	Environment env;

	@Test
	public void contextLoads() {
		assertNotNull(handler);
	}

	@Test
	public void testhandelWrongDateFormateException() {
		assertNotNull(handler.handelFeignProxyException(new FeignProxyException()));
	}

}
