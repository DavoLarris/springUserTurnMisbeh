<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- No pueden convivir c & sgf? da error y no arranca -->
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring forms :: Users</title>

<!-- Bootstrap -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Users app</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<s:url value="/users" />" title="View Users">View
							users</a></li>
					<li><a href="<s:url value="/turns/" />" title="View turns">View
							turns</a></li>
					<li class="active"><a href="<s:url value="/misbehaviors/" />"
						title="View turns">View misbehaviors</a></li>
					<li><a href="<s:url value="/misbehaviors/new" />"
						title="New misbehavior">New misbehavior</a></li>
				</ul>
				<div class="nav navbar-nav navbar-right">
					<c:url var="get_misbehaviors" value="/misbehaviors/search" />
					<sf:form class="navbar-form" method="post"
						modelAttribute="misbehaviorS" action="${get_misbehaviors}">
						<div class="input-group">
							<sf:input path="title" class="form-control" placeholder="Search" />
							<div class="input-group-btn">
								<sf:button class="btn btn-default" type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</sf:button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1>Misbehaviors list</h1>
			<p>These are the misbehaviors currently in the system.</p>
			<p><a href="<s:url value="/users" />">See users</a> || <a href="<s:url value="/turns" />">See turns</a></p>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Date</th>
					<th>User</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${misbehaviors}" var="misbehavior">
					<tr>
						<td>${misbehavior.title}</td>
						<td>${misbehavior.description}</td>
						<td>${misbehavior.date}</td>
						<td>${misbehavior.user.id}</td>
						<td><a class="btn btn-sm btn-primary"
							href="<s:url value="/misbehaviors/${misbehavior.id}" />"
							title="Detailed info"> see detail</a> <a
							class="btn btn-sm btn-success"
							href="<s:url value="/misbehaviors/update/${misbehavior.id}" />"
							title="Update"> update</a> <a class="btn btn-sm btn-danger"
							href="<s:url value="/misbehaviors/delete/${misbehavior.id}" />"
							title="Delete"> delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="<s:url value="/misbehaviors/deleteall" />"><button class="bnt btn-sm btn-danger">Delete all</button></a>
	</div>
	<br />
	<hr />
	<footer class="footer">
		<div class="container">
			<p class="text-muted">&copy; 2017 David Larrea</p>
		</div>
	</footer>
</body>
</html>