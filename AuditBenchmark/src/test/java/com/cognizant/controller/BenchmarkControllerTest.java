package com.cognizant.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.entity.AuditBenchmark;
import com.cognizant.feignclient.AuthClient;
import com.cognizant.service.TokenService;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class BenchmarkControllerTest {

	@Mock
	AuthClient authClient;

	@Mock
	TokenService tokenService;

	@Mock
	Environment env;

	@Mock
	AuditBenchmark auditBenchmark;
	
	@InjectMocks
	BenchmarkController controller;

	@Test
	public void testGetBenchmarkMap() {
		List<AuditBenchmark> auditBenchmarkList = new ArrayList<>();
		auditBenchmarkList.add(new AuditBenchmark("Internal", 3));
		auditBenchmarkList.add(new AuditBenchmark("SOX", 1));
		when(tokenService.checkTokenValidity("token")).thenReturn(true);
		assertEquals(auditBenchmarkList, controller.getBenchmarkMap("token").getBody());
	}

	@Test
	public void testTokenInvalid() {
		when(tokenService.checkTokenValidity("token")).thenReturn(false);
		assertEquals(HttpStatus.FORBIDDEN, controller.getBenchmarkMap("token").getStatusCode());
	}
}
