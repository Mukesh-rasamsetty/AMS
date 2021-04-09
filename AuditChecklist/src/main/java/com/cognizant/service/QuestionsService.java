package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.entity.QuestionsEntity;
import com.cognizant.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	QuestionsRepository questionsRepository;

	public List<QuestionsEntity> getQuestions(String auditType) throws IndexOutOfBoundsException {
		if (questionsRepository.findByAuditType(auditType).isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return questionsRepository.findByAuditType(auditType);
	}

	public List<QuestionsEntity> saveResponses(List<QuestionsEntity> questionsResponse) {
		return questionsRepository.saveAll(questionsResponse);
	}

}