package com.cognizant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditRequest {
	private String projectName;
	private String projectManagerName;
	private String applicationOwnerName;
	private AuditDetails auditDetails;

}