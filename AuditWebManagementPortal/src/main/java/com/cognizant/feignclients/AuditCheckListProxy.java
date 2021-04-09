package com.cognizant.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.model.AuditType;
import com.cognizant.model.QuestionsEntity;

@FeignClient(url = "${fos.checklist}", name = "audit-checklist", fallback = AuditCheckListProxyFallback.class)
public interface AuditCheckListProxy {

	@PostMapping("/getChecklist")
	public ResponseEntity<List<QuestionsEntity>> getCheckList(
			@RequestHeader(name = "Authorization", required = true) String token, @RequestBody AuditType auditType);

	@PostMapping("/saveResponses")
	public ResponseEntity<List<QuestionsEntity>> saveResponses(
			@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody List<QuestionsEntity> questionsResponse);

}
