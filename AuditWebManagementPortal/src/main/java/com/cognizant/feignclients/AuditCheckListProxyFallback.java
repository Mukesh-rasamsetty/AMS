package com.cognizant.feignclients;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cognizant.model.AuditType;
import com.cognizant.model.QuestionsEntity;

public class AuditCheckListProxyFallback implements AuditCheckListProxy{

	@Override
	public ResponseEntity<List<QuestionsEntity>> getCheckList(String token, AuditType auditType) {
		return null;
	}

	@Override
	public ResponseEntity<List<QuestionsEntity>> saveResponses(String token, List<QuestionsEntity> questionsResponse) {
		return null;
	}

}
