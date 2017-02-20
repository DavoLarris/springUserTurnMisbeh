<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
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
			<h1>Update user</h1>
		</div>
		<s:url var="action" value="/misbehaviors/saveupdate" />
		<sf:form method="post" modelAttribute="misbehavior"
			action="${action}">
			<div class="form-group">
				<label for="title">Title</label>
				<sf:input path="title" class="form-control" placeholder="title" />
			</div>
			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" class="form-control"
					placeholder="Description" />
			</div>
			<div class="form-group">
				<label for="date">Date</label>
				<sf:input type="date" path="date" class="form-control" />
			</div>
			<div class="form-group">
				<label for="user.id">User</label>
				<sf:select path="user.id" class="form-control" placeholder="id user">
					<sf:options items="${users}" itemLabel="login" itemValue="id" />
				</sf:select>
			</div>
			<sf:button class="btn btn-primary pull-right">Create</sf:button>
		</sf:form>
		<br />
		<hr />
		<footer class="footer">
			<div class="container">
				<p class="text-muted">&copy; 2017 David Larrea</p>
			</div>
		</footer>
	</div>
</body>
</html>
