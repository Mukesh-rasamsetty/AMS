package com.cognizant.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.feignclient.AuditBenchmarkFeignclient;
import com.cognizant.feignclient.AuditCheckListProxy;
import com.cognizant.feignclient.AuthClient;
import com.cognizant.pojo.AuditBenchmark;
import com.cognizant.pojo.AuditDetails;
import com.cognizant.pojo.AuditRequest;
import com.cognizant.pojo.AuditType;
import com.cognizant.pojo.QuestionsEntity;
import com.cognizant.service.AuditRequestResponse;
import com.cognizant.service.TokenService;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SeverityControllerTest {

	@Mock
	private AuditRequestResponse service;

	@Mock
	AuthClient authClient;

	@Mock
	AuditCheckListProxy checklistProxy;

	@Mock
	TokenService tokenService;

	@Mock
	AuditBenchmarkFeignclient auditBenchmarkFeignclient;

	@Mock
	Environment env;

	@InjectMocks
	SeverityController severityController;

	@Test
	public void contextLoad() {
		assertNotNull(severityController);
	}

	@Test
	public void testAuditSeverity() {
		List<QuestionsEntity> questionsEntity = new ArrayList<>();
		questionsEntity.add(new QuestionsEntity(1, "Internal", "ABC", "No"));
		questionsEntity.add(new QuestionsEntity(1, "Internal", "DEF", "No"));
		questionsEntity.add(new QuestionsEntity(1, "Internal", "PQR", "Yes"));
		questionsEntity.add(new QuestionsEntity(1, "Internal", "STU", "Yes"));
		questionsEntity.add(new QuestionsEntity(1, "Internal", "QWF", "Yes"));

		ResponseEntity<List<QuestionsEntity>> questionsList = new ResponseEntity<List<QuestionsEntity>>(questionsEntity,
				HttpStatus.OK);
		List<AuditBenchmark> auditBenchmarkList = new ArrayList<AuditBenchmark>();
		auditBenchmarkList.add(new AuditBenchmark("Internal", 3));
		auditBenchmarkList.add(new AuditBenchmark("SOX", 1));
		ResponseEntity<List<AuditBenchmark>> responseEntity = new ResponseEntity<List<AuditBenchmark>>(
				auditBenchmarkList, HttpStatus.OK);
		AuditType auditType = new AuditType("Internal");
		when(tokenService.checkTokenValidity("token")).thenReturn(true);
		when(auditBenchmarkFeignclient.getBenchmarkMap("token")).thenReturn(responseEntity);
		when(checklistProxy.getChecklist("token", auditType)).thenReturn(questionsList);
		assertNotNull(severityController
				.auditSeverity("token", new AuditRequest("P", "Q", "R", new AuditDetails("Internal", null))).getBody());
	}

	@Test
	public void testAuditSeverityTokenFails() {
		when(tokenService.checkTokenValidity("token")).thenReturn(false);
		assertEquals(HttpStatus.FORBIDDEN,
				severityController
						.auditSeverity("token", new AuditRequest("P", "Q", "R", new AuditDetails("Internal", null)))
						.getStatusCode());

	}

}
