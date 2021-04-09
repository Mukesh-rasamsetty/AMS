package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.feignclient.AuditBenchmarkFeignclient;
import com.cognizant.feignclient.AuditCheckListProxy;
import com.cognizant.pojo.AuditBenchmark;
import com.cognizant.pojo.AuditRequest;
import com.cognizant.pojo.AuditResponse;
import com.cognizant.pojo.AuditType;
import com.cognizant.pojo.QuestionsEntity;
import com.cognizant.service.AuditRequestResponse;
import com.cognizant.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SeverityController {

	@Autowired
	private AuditRequestResponse service;
	@Autowired
	AuditCheckListProxy checklistProxy;
	@Autowired
	TokenService tokenService;
	@Autowired
	Environment env;
	@Autowired
	AuditBenchmarkFeignclient auditBenchmarkFeignclient;

	@PostMapping("/ProjectExecutionStatus")
	public ResponseEntity<?> auditSeverity(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody AuditRequest auditRequest) {
		int accNoAnswers = 0, actualNoAnswers = 0;
		ResponseEntity<?> responseEntity = null;
		List<QuestionsEntity> questionsList = null;
		if (tokenService.checkTokenValidity(token)) {
			List<AuditBenchmark> benchmarkList = auditBenchmarkFeignclient.getBenchmarkMap(token).getBody();
			if (benchmarkList == null) {
				log.warn("Aduit Benchmark is down...!");
				responseEntity = new ResponseEntity<String>("Benchmark Service down...!", HttpStatus.BAD_GATEWAY);
				return responseEntity;
			}
			for (AuditBenchmark benchmark : benchmarkList) {
				if (benchmark.getAuditType().equalsIgnoreCase(auditRequest.getAuditDetails().getAuditType())) {
					accNoAnswers = benchmark.getAccNoAnswers();
				}
			}
			AuditType auditType = new AuditType(auditRequest.getAuditDetails().getAuditType());
			questionsList = checklistProxy.getChecklist(token, auditType).getBody();
			if (questionsList == null) {
				log.warn("Aduit Checklist is down...!");
				responseEntity = new ResponseEntity<String>("CheckList Service down...!", HttpStatus.BAD_GATEWAY);
				return responseEntity;
			}
			for (QuestionsEntity answer : questionsList) {
				if (answer.getResponse().equalsIgnoreCase("No")) {
					actualNoAnswers++;
				}
			}
			if (actualNoAnswers <= accNoAnswers) {
				responseEntity = new ResponseEntity<AuditResponse>(new AuditResponse(
						env.getProperty("project.status.green"), env.getProperty("remedial.action.no")), HttpStatus.OK);
				AuditResponse response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);
			} else if (auditRequest.getAuditDetails().getAuditType().equalsIgnoreCase("Internal")) {
				responseEntity = new ResponseEntity<AuditResponse>(
						new AuditResponse(env.getProperty("project.status.red"),
								env.getProperty("remedial.action.yes.one")),
						HttpStatus.OK);
				AuditResponse response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);
			} else if (auditRequest.getAuditDetails().getAuditType().equalsIgnoreCase("SOX")) {
				responseEntity = new ResponseEntity<AuditResponse>(
						new AuditResponse(env.getProperty("project.status.red"),
								env.getProperty("remedial.action.yes.two")),
						HttpStatus.OK);
				AuditResponse response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);

			}
			service.saveRequest(auditRequest);
			return responseEntity;

		} else {
			log.error(env.getProperty("string.token.exp"));
			log.info(env.getProperty("string.end"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}

	}

}