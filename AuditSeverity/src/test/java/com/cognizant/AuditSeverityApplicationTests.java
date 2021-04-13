package com.cognizant;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
class AuditSeverityApplicationTests {

	@Mock
	AuditSeverityApplication application;

	@Test
	void contextLoads() {
		assertNotNull(application);
	}

}
