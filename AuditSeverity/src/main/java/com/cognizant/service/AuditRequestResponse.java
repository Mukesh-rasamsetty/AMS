package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.AuditDetailModel;
import com.cognizant.model.AuditRequestModel;
import com.cognizant.model.AuditResponseModel;
import com.cognizant.pojo.AuditRequest;
import com.cognizant.pojo.AuditResponse;
import com.cognizant.repository.RequestRepository;
import com.cognizant.repository.ResponseRepository;

@Service
public class AuditRequestResponse {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private ResponseRepository responseRepository;

	public AuditRequestModel saveRequest(AuditRequest request) {
		AuditRequestModel requestModel = new AuditRequestModel();
		AuditDetailModel auditDetailModel = new AuditDetailModel(request.getAuditDetails().getAuditType());
		requestModel.setAuditDetail(auditDetailModel);
		requestModel.setProjectName(request.getProjectName());
		requestModel.setManagerName(request.getProjectManagerName());
		requestModel.setOwnerName(request.getApplicationOwnerName());
		return requestRepository.save(requestModel);
	}

	public AuditResponseModel saveResponse(AuditResponse response) {
		AuditResponseModel responseModel = new AuditResponseModel(response.getProjectExecutionStatus(),
				response.getRemedialActionDuration());
		return responseRepository.save(responseModel);

	}

}