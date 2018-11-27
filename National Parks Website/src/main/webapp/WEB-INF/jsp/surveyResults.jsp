<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>

<link rel="stylesheet" href="css/npgeek.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	
<section id="main-content">
	<br>

	<c:url value="/surveyResults" var="formAction" />

	<h2 id="surveyResultsHeader">Favorite Parks</h2>
	<h4 id="surveyResultsSubheader">(Ranked by Survey Votes Received)</h4>

	<form method="POST" action="surveyResults">

		<table id="surveyResults">

			<tr>
				<th></th>
				<th></th>
				<th style="padding-left: 25px">Votes</th>
			</tr>
			<br>

			<c:forEach var="survey" items="${surveyResults}">
				<tr>
					<td><img
						style="width: 250px; height: 100px; padding-left: 80px"
						src="img/parks/${survey.imageName}.jpg"></td>

					<td id="surveyName"><c:out value="${survey.parkName}" /></td>

					<td id="votes"><c:out value="${survey.votes}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</section>
<br>
<br>
<br>
<%@ include file="/WEB-INF/jsp/common/footer.jspf"%>