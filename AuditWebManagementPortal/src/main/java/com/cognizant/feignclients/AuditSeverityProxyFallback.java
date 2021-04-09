package com.cognizant.feignclients;

import org.springframework.http.ResponseEntity;

import com.cognizant.model.AuditRequest;
import com.cognizant.model.AuditResponse;

public class AuditSeverityProxyFallback implements AuditSeverityProxy{

	@Override
	public ResponseEntity<AuditResponse> auditSeverity(String token, AuditRequest request) {
		return null;
	}

}
