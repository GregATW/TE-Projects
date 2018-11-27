<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>

<link rel="stylesheet" href="css/homePage.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<section id="main-content">

	<table>
		<c:forEach var="park" items="${parks}">
			<c:url var="parkDetails" value="/parkDetails">
				<c:param name="parkImageName" value="${park.imageName}" />
			</c:url>

			<tr>
				<td>
					<div id="images">
						<td><a href="${parkDetails}"><img style = "padding-left: 20px"
								src="img/parks/${park.imageName}.jpg"></a></td>
					</div>
				</td>

				<td>
					<div id="homeText">

						<div id="name">
							<c:out value="${park.parkName}" />
						</div>

						<div id="state">
							<c:out value="${park.state}" />
						</div>

						<br>

						<div id="description">
							<c:out value="${park.parkDescription}" />
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jspf"%>