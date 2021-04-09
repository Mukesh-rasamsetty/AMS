<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<title>Audit Questions</title>
<style>
body {
	background: linear-gradient(to right, #1d976c, #93f9b9);
}

form {
	background: #FFF;
}

h3 {
	color: #FFF;
}
</style>
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</SCRIPT>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="col-11">
			<a class="navbar-brand cus-brand" href="#">Audit Management
				System</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarText" aria-controls="navbarText"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse col" id="navbarText">

			<span class="navbar-text btn float-right"> <a href="/logout"
				class="btn btn-warn">Logout</a>
			</span>
		</div>
	</nav>
	<div class="container">

		<h3 class="m-4 display-4 text-center">${auditType.getAuditType()}
			Audit Questions</h3>

		<form:form action="/questions" method="post"
			modelAttribute="questions" class="px-5 py-4 border rounded">
			<c:forEach var="emp" items="${questions.questionsEntity}"
				varStatus="status">
				<h5 class="mt-3">${emp.question}</h5>
				<form:hidden path="questionsEntity[${status.index}].questionId"
					value="${emp.questionId }" />
				<form:hidden path="questionsEntity[${status.index}].question"
					value="${emp.question }" />
				<form:hidden path="questionsEntity[${status.index}].auditType"
					value="${emp.auditType }" />
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<form:radiobutton
								path="questionsEntity[${status.index}].response" value="yes"
								required="required" />
						</div>
					</div>
					<form:label class="form-control"
						path="questionsEntity[${status.index}].response">Yes</form:label>

					<div class="input-group-prepend">
						<div class="input-group-text">
							<form:radiobutton
								path="questionsEntity[${status.index}].response" value="no" />
						</div>
					</div>
					<form:label class="form-control"
						path="questionsEntity[${status.index}].response">No</form:label>
				</div>
			</c:forEach>
			<input type="Submit" value="Submit"
				class="btn btn-primary btn-block mt-3" />
		</form:form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>
</html>






