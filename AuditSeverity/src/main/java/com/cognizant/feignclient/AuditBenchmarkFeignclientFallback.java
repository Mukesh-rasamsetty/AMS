package com.cognizant.feignclient;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cognizant.pojo.AuditBenchmark;

public class AuditBenchmarkFeignclientFallback implements AuditBenchmarkFeignclient {

	@Override
	public ResponseEntity<List<AuditBenchmark>> getBenchmarkMap(String token) {
		return null;
	}

}
