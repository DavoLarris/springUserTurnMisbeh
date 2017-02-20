<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="jumbotron">
			<h1>Request created</h1>
		</div>
		<div class="alert alert-success" role="alert">The question
			request has been added</div>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Questions</th>
					<th>Priority</th>
					<th>Date</th>
					<th>User</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${turn.id}</td>
					<td>${turn.question}</td>
					<td>${turn.priority}</td>
					<td>${turn.date}</td>
					<td>${turn.user.id}</td>
				</tr>
			</tbody>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>