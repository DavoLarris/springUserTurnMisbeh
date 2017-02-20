<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- No pueden convivir c & sgf? da error y no arranca -->
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
					<li><a href="<s:url value="/misbehaviors/" />"
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
			<h1>Misbehavior detail</h1>
			<p>See this misbehavior info</p>
		</div>

		<c:choose>
			<c:when test="${not empty misbehavior}">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Title</th>
							<th>Description</th>
							<th>Date</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${misbehavior.id}</td>
							<td>${misbehavior.title}</td>
							<td>${misbehavior.description}</td>
							<td>${misbehavior.date}</td>
							<td><a class="btn btn-sm btn-success"
								href="<s:url value="/misbehaviors/update/${misbehavior.id}" />"
								title="Update"> update</a> <a class="btn btn-sm btn-danger"
								href="<s:url value="/misbehaviors/delete/${misbehavior.id}" />"
								title="Delete"> delete</a></td>
						</tr>
					</tbody>
				</table>

				<br />
				<h2>User</h2>
				<c:choose>
					<c:when test="${not empty misbehavior.user}">
						<table class="table">
							<thead>
								<tr>
									<th>Id</th>
									<th>Login</th>
									<th>Description</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${misbehavior.user.id}</td>
									<td>${misbehavior.user.login}</td>
									<td>${misbehavior.user.description}</td>
									<td><a class="btn btn-sm btn-primary"
										href="<s:url value="/users/${user.id}" />"
										title="Detailed info"> see detail</a> <a
										class="btn btn-sm btn-success"
										href="<s:url value="/users/update/${user.id}" />"
										title="Update"> update</a> <a class="btn btn-sm btn-danger"
										href="<s:url value="/users/delete/${user.id}" />"
										title="Delete"> delete</a></td>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning" role="alert">I'm afraid
							there is no user</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div class="alert alert-warning" role="alert">A user with the
					id specified has not been found. Please, try again</div>
			</c:otherwise>
		</c:choose>
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