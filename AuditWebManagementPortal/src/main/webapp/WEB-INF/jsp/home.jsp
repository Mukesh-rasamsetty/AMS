<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<title>Project Details - Audit Management System</title>
<style>
body {
	background: #66ffff;
}

form {
	background: #6699ff;
}

h1 {
	color: #FFF;
}

.cus-brand {
	color: #eb8334;
	font-size: 2em;
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
	<!-- navigation-bar -->
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

		<h1 class="m-5 text-center display-3" style="color: red">Project
			Details</h1>
		<!-- Main Page -->
		<form:form action="/AuditCheckListQuestions"
			modelAttribute="projectDetails" method="post"
			class="px-5 py-4 border rounded">

			<div class="form-group">
				<form:label path="projectName">Project Name</form:label>
				<form:input path="projectName" required="required"
					class="form-control" id="ProjectName" />

			</div>
			<div class="form-group">
				<form:label path="projectManagerName">Project Manager Name</form:label>
				<form:input path="projectManagerName" required="required"
					class="form-control" id="ProjectManagerName" />
			</div>
			<div class="form-group">
				<form:label path="applicationOwnerName">Application Owner</form:label>
				<form:input path="applicationOwnerName" required="required"
					class="form-control" id="ApplicationOwnerName" />
			</div>

			<div class="form-group">
				<label for="AuditType">Audit Type</label>
				<form:form modelAttribute="auditType">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<form:radiobutton path="auditType" required="required"
									id="internal" value="Internal" name="audittype"
									aria-label="Radio button for following text input" />
							</div>
						</div>
						<label for="Internal" class="form-control"> Internal</label>

						<div class="input-group-prepend">
							<div class="input-group-text">
								<form:radiobutton path="auditType" id="sox" value="SOX"
									name="audittype"
									aria-label="Radio button for following text input" />
							</div>
						</div>
						<label for="SOX" class="form-control"> SOX</label>
					</div>
					<input type="submit" class="btn btn-primary btn-block mt-3"
						value="Submit">
				</form:form>
			</div>
		</form:form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>
</html>




