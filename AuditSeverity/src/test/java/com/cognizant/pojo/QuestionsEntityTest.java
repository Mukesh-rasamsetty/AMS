package com.cognizant.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsEntityTest {

	@Test
	void testQuestionsEntity_AllArgsConstructor() {
		QuestionsEntity entity = new QuestionsEntity(1, "Internal", "question", "No");

		assertNotNull(entity);
		assertEquals(1, entity.getQuestionId());
		assertEquals("Internal", entity.getAuditType());
		assertEquals("question", entity.getQuestion());
		assertEquals("No", entity.getResponse());
	}

	@Test
	void testQuestionsEntity_NoArgsConstructor() {
		QuestionsEntity entity = new QuestionsEntity();

		assertNotNull(entity);
		assertNull(entity.getQuestionId());
		assertNull(entity.getAuditType());
		assertNull(entity.getQuestion());
		assertNull(entity.getResponse());
	}

	@Test
	void testGetQuestionId() {
		QuestionsEntity entity = new QuestionsEntity();

		assertNotNull(entity);
		entity.setQuestionId(1);
		assertEquals(1, entity.getQuestionId());
	}

	@Test
	void testGetAuditType() {
		QuestionsEntity entity = new QuestionsEntity();

		assertNotNull(entity);
		entity.setAuditType("SOX");
		assertEquals("SOX", entity.getAuditType());
	}

	@Test
	void testGetQuestion() {
		QuestionsEntity entity = new QuestionsEntity();

		assertNotNull(entity);
		entity.setQuestion("Some Questions");
		assertEquals("Some Questions", entity.getQuestion());
	}

	@Test
	void testGetResponse() {
		QuestionsEntity entity = new QuestionsEntity();

		assertNotNull(entity);
		entity.setResponse("YES");
		assertEquals("YES", entity.getResponse());
	}

}
