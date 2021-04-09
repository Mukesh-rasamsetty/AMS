package com.cognizant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.exception.ServiceDownException;
import com.cognizant.feignclients.AuditCheckListProxy;
import com.cognizant.feignclients.AuditSeverityProxy;
import com.cognizant.feignclients.AuthClient;
import com.cognizant.model.AuditDetails;
import com.cognizant.model.AuditRequest;
import com.cognizant.model.AuditResponse;
import com.cognizant.model.AuditType;
import com.cognizant.model.ProjectDetails;
import com.cognizant.model.ProjectManager;
import com.cognizant.model.Questions;
import com.cognizant.model.QuestionsEntity;
import com.cognizant.model.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuditPortalController {

	private AuthClient authClient;
	private AuditCheckListProxy auditCheckListProxy;
	private AuditRequest auditRequest;
	private AuditSeverityProxy auditSeverityProxy;
	private Environment env;

	@Autowired
	public AuditPortalController(AuthClient authClient, AuditCheckListProxy auditCheckListProxy,
			AuditRequest auditRequest, AuditSeverityProxy auditSeverityProxy, Environment env) {
		super();
		this.authClient = authClient;
		this.auditCheckListProxy = auditCheckListProxy;
		this.auditRequest = auditRequest;
		this.auditSeverityProxy = auditSeverityProxy;
		this.env = env;
	}

	@GetMapping("/")
	public String login(@ModelAttribute User user) {
		log.info(env.getProperty("spring.start"));
		log.debug(user.getUserId());
		return "login";
	}

	@GetMapping("/loginPage")
	public String loginPage(@ModelAttribute User user) {
		log.info(env.getProperty("spring.start"));
		log.debug(user.getUserId());
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String getHome(@ModelAttribute("user") User userCredentials, HttpServletRequest request, ModelMap map) {
		log.info(env.getProperty("string.start"));
		log.info(userCredentials.toString());
		ResponseEntity<ProjectManager> token = null;
		ProjectManager projectManager = null;
		map.addAttribute("auditType", new AuditType());
		map.addAttribute("projectDetails", new ProjectDetails());

		try {
			token = (ResponseEntity<ProjectManager>) authClient.login(userCredentials);
			if (token == null) {
				log.warn("Audit-Authentication Service Down");
				throw new ServiceDownException(
						"<h1><b>Audit-Authentication Service Down..!</b></h1>\nService Not Found Exception");
			}
			log.info(token.toString());
			projectManager = token.getBody();
			request.getSession().setAttribute("token", "Bearer " + projectManager.getAuthToken());
			return "home";
		} catch (Exception e) {
			log.info(e.getMessage());
			map.addAttribute("msg", env.getProperty("string.invalid.cred"));
			log.info(env.getProperty("spring.end"));
			return "login";
		}

	}

	@PostMapping("/AuditCheckListQuestions")
	public String getResponses(@ModelAttribute("projectDetails") ProjectDetails projectDetails,
			@ModelAttribute("auditType") AuditType auditType, RedirectAttributes redirectAttributes,
			HttpSession request, ModelMap map) throws ServiceDownException {
		log.info(env.getProperty("spring.start"));
		List<QuestionsEntity> questions = new ArrayList<>();
		auditRequest.setProjectName(projectDetails.getProjectName());
		auditRequest.setProjectManagerName(projectDetails.getProjectManagerName());
		auditRequest.setApplicationOwnerName(projectDetails.getApplicationOwnerName());
		try {
			ResponseEntity<List<QuestionsEntity>> response = auditCheckListProxy
					.getCheckList(request.getAttribute("token").toString(), auditType);
			if (response == null || response.getBody().size() == 0) {
				log.warn("Audit-Check List Service Down");
				throw new ServiceDownException(
						"<h1><b>Audit-checkList Service Down..!</b></h1>\nService Not Found Exception");
			}
			questions = response.getBody();
		} catch (IndexOutOfBoundsException e) {
			log.info(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.null"))) {
				return "internalServerError";
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.token.exp")))
				return "forbidden";
		}
		if (questions != null && questions.size() == 0) {
			return "tokenExpiredPage";
		}
		for (QuestionsEntity question : questions) {
			if (question.getResponse() != null) {
				question.setResponse(null);
			}
		}
		Questions questionslist = new Questions();
		questionslist.setQuestionsEntity(questions);
		redirectAttributes.addFlashAttribute("questions", questionslist);
		redirectAttributes.addFlashAttribute("auditType", auditType);
		log.info(env.getProperty("spring.end"));
		return "redirect:/questions";

	}

	@GetMapping("/questions")
	public String getQuestions(@ModelAttribute("questions") Questions questions,
			@ModelAttribute("auditType") AuditType auditType, HttpSession session, ModelMap map) {
		log.info(env.getProperty("spring.start"));
		ResponseEntity<?> authResponse = null;
		try {
			authResponse = authClient.getValidity(session.getAttribute("token").toString());
		} catch (Exception e) {
			if (e.getMessage().contains(env.getProperty("token.expired")))
				return "tokenExpiredPage";
			if (e.getMessage().contains(env.getProperty("auth.failed")))
				return "authFailed";

			return "redirect:/logout";
		}
		if (authResponse == null) {
			return "tokenExpiredPage";
		}
		log.info(env.getProperty("spring.end"));
		return "questions";
	}

	@PostMapping("/questions")
	public String getResponses(@ModelAttribute("questions") Questions questions, HttpSession session) {
		log.info(env.getProperty("spring.start"));
		ResponseEntity<?> authResponse = null;
		List<QuestionsEntity> responseEntity = null;
		List<QuestionsEntity> questionsEntity = questions.getQuestionsEntity();
		try {
			authResponse = authClient.getValidity(session.getAttribute("token").toString());
			responseEntity = auditCheckListProxy
					.saveResponses(session.getAttribute("token").toString(), questionsEntity).getBody();

		} catch (Exception e) {
			if (e.getMessage().contains(env.getProperty("token.expired")))
				return "tokenExpiredPage";
			if (e.getMessage().contains(env.getProperty("auth.failed")))
				return "authFailed";

			return "redirect:/logout";
		}
		if (authResponse == null || responseEntity == null) {
			return "tokenExpiredPage";
		}
		AuditDetails auditDetails = new AuditDetails(questions.getQuestionsEntity().get(0).getAuditType(), new Date());
		auditRequest.setAuditDetails(auditDetails);
		log.info(env.getProperty("spring.end"));
		return "redirect:/status";
	}

	@GetMapping("/status")
	public String getProjectExecutionStatus(HttpSession request, ModelMap map) {
		log.info(env.getProperty("spring.start"));
		AuditResponse auditResponse = null;
		log.info(auditRequest.toString());
		try {
			auditResponse = auditSeverityProxy.auditSeverity(request.getAttribute("token").toString(), auditRequest)
					.getBody();

		} catch (Exception e) {
			log.info(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.token.exp")))
				return "tokenExpiredPage";

			return "tokenExpiredPage";
		}
		map.addAttribute("auditResponse", auditResponse);
		if (auditResponse.getProjectExecutionStatus().equalsIgnoreCase("GREEN")) {
			map.addAttribute("result", "green");
		} else {
			map.addAttribute("result", null);
		}
		log.info(env.getProperty("spring.end"));
		return "status";

	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		log.info(env.getProperty("spring.start"));
		request.getSession().invalidate();
		log.info(env.getProperty("spring.end"));
		return "redirect:/";
	}
}
