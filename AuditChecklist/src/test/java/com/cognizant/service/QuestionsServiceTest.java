package com.cognizant.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.entity.QuestionsEntity;
import com.cognizant.repository.QuestionsRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class QuestionsServiceTest {

	@Mock
	QuestionsRepository questionsRespository;

	@InjectMocks
	QuestionsService questionsService;

	@Test
	public void testGetQuestionsList() throws IndexOutOfBoundsException {
		List<QuestionsEntity> questions = new ArrayList<>();
		questions.add(
				new QuestionsEntity(1, "Internal", "Have all Change requests followed SDLC before PROD move?", "Yes"));
		when(questionsRespository.findByAuditType("Internal")).thenReturn(questions);
		assertEquals(questions, questionsService.getQuestions("Internal"));
	}

	@Test
	public void testQuestionsListthrowsIndexOutOfBoundsException() {
		when(questionsRespository.findByAuditType("Internal")).thenThrow(IndexOutOfBoundsException.class);
		assertThrows(IndexOutOfBoundsException.class, () -> questionsService.getQuestions("Internal"));
	}

	@Test
	public void testSaveResponses() {
		List<QuestionsEntity> questions = new ArrayList<>();
		questions.add(
				new QuestionsEntity(1, "Internal", "Have all Change requests followed SDLC before PROD move?", "Yes"));
		when(questionsRespository.saveAll(questions)).thenReturn(questions);
		assertEquals(questions, questionsService.saveResponses(questions));
	}
}
