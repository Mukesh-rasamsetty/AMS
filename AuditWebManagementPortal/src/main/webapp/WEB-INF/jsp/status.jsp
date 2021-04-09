<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<title>Audit Response</title>
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
	<div class="container d-flex align-items-center justify-content-center">
		<div align="center">
			<table class="table table-striped">
				<tr>
					<th>Execution status</th>
					<c:choose>
						<c:when test="${result != null}">
							<td class="p-3 mb-2 bg-success text-white">"${auditResponse.getProjectExecutionStatus()}"</td>
						</c:when>
						<c:when test="${result == null}">
							<td class="p-3 mb-2 text-white bg-danger">"${auditResponse.getProjectExecutionStatus()}"</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<th>Remedial Action</th>
					<td>"${auditResponse.getRemedialActionDuration()}"</td>
				</tr>
			</table>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>
</html>