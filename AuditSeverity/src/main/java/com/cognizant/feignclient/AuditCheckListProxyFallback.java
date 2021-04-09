package com.cognizant.feignclient;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cognizant.pojo.AuditType;
import com.cognizant.pojo.QuestionsEntity;

public class AuditCheckListProxyFallback implements AuditCheckListProxy{

	@Override
	public ResponseEntity<List<QuestionsEntity>> getChecklist(String token, AuditType auditType) {
		return null;
	}

}
