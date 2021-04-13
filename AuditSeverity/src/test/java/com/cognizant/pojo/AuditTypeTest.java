package com.cognizant.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditTypeTest {
	@Test
	void testAuditType_NoArgsConstructor() {
		AuditType auditType = new AuditType();

		assertNotNull(auditType);
		assertNull(auditType.getAuditType());
	}

	@Test
	void testAuditType_AllArgsConstructor() {
		AuditType auditType = new AuditType("Internal");

		assertNotNull(auditType);
		assertEquals("Internal", auditType.getAuditType());
	}

	@Test
	void testSetAuditType() {
		AuditType auditType = new AuditType();

		assertNotNull(auditType);
		assertNull(auditType.getAuditType());
		auditType.setAuditType("Internal");
		assertEquals("Internal", auditType.getAuditType());
	}

}
