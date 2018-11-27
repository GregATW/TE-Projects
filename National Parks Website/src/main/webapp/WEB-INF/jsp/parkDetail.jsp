<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/jsp/common/header.jspf"%>

<head>
<meta name="viewport" content="width=device-width" />
<title>Park Detail and Forecast</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="css/parkDetail.css" />
</head>
<body>

	<section id="main-content">

		<div class="allParkDetails">
			<div id="image">
				<img style="width: 1020px; height: 480px"
					src="img/parks/${park.imageName}.jpg" />
			</div>

		</div>
		<br>
		<div class = "bodyBelowImage">
		<div class="parkInfoSection">

			<div id="parkName">
				<c:out value="${park.parkName}" />
			</div>

			<div id="parkQuote">
				"
				<c:out value="${park.inspirationalQuote}" />
				" &emsp; -
				<c:out value="${park.inspirationalQuoteSource}" />
			</div>

			<div id="description" style="font-style: normal">
				<c:out value="${park.parkDescription}" />
			</div>

			<div class="wholeTable">
				<table class="tableLeft">
					<ul>
						<tr>
							<td><li><b><em>State: </em></b></li></td>
							<td>&emsp;<c:out value="${park.state}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Acreage: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.acreage}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Elevation in Feet: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.elevationInFeet}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Miles of Trail: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.milesOfTrail}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Number of Campsites: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.numberOfCampsites}" /></td>
						</tr>
					</ul>
				</table>

				<table class="tableRight">
					<ul>
						<tr>
							<td><li><b><em>Climate: </em></b></li></td>
							<td>&emsp;<c:out value="${park.climate}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Year Founded: </em></b></li></td>
							<td>&emsp;<c:out value="${park.yearFounded}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Annual Visitor Count: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.annualVisitorCount}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Entry Fee: </em></b></li></td>
							<td>&emsp;<c:out value="$${park.entryFee}" /></td>
						</tr>

						<tr>
							<td><li><b><em>Number of Animal Species: </em></b></li></td>
							<td>&emsp;<fmt:formatNumber type="number"
									value="${park.numberOfAnimalSpecies}" /></td>
						</tr>
					</ul>
				</table>
			</div>
		</div>
		<br>
<br> <br>
		<h2>Weather Forecast</h2><br>
		<div class="weatherSection">

			<div class="card-deck">
				<c:forEach var="weather" items="${weather}">
					<div class="card bg-5cb85c">

						<%-- <h2 class="day" style="font-size: 24px">
							<c:choose>
							<c:when test="${weather.fiveDayForecastValue == 1}">
							Today</c:when>
							<c:otherwise>
							<c:out value="Day ${weather.fiveDayForecastValue}" />
							</c:otherwise>
							</c:choose>
						</h2> 				 --%>		
		<!-- --------NEW DAYS---------- -->
						
						<h2 class="day" style="font-size: 24px">
						
							<div id="today" style="font-size: 36px">
							<c:if test="${weather.fiveDayForecastValue == 1}">
							Today</c:if></div>
							
							<c:if test="${weather.fiveDayForecastValue == 2}">
							<p id="followingDay"></p>
								<script>
									function myFunction() {
									var current = new Date();
									var followingDay = new Date(current.getTime() + 86400000);
									return followingDay;
									}
									document.getElementById("followingDay").innerHTML = myFunction().toDateString();
								</script>
							</c:if>
							
							<c:if test="${weather.fiveDayForecastValue == 3}">
							<p id="followingDay2"></p>
								<script>
									function myFunction() {
									var current = new Date();
									var followingDay2 = new Date(current.getTime() + 172800000);
									return followingDay2;
									}
									document.getElementById("followingDay2").innerHTML = myFunction().toDateString();
								</script>
							</c:if>
							
							<c:if test="${weather.fiveDayForecastValue == 4}">
							<p id="followingDay3"></p>
								<script>
									function myFunction() {
									var current = new Date();
									var followingDay3 = new Date(current.getTime() + 259200000);
									return followingDay3;
									}
									document.getElementById("followingDay3").innerHTML = myFunction().toDateString();
								</script>
							</c:if>
							
							<c:if test="${weather.fiveDayForecastValue == 5}">
							<p id="followingDay4"></p>
								<script>
									function myFunction() {
									var current = new Date();
									var followingDay4 = new Date(current.getTime() + 345600000);
									return followingDay4;
									}
									document.getElementById("followingDay4").innerHTML = myFunction().toDateString();
								</script>
							</c:if>
						<div id="weatherGraphic">
							<img style="width: 125px; height: 125px"
								src="img/weather/${weather.forecast}.png"
								alt="${weather.forecast} image" />

						</div>
						<div id="temps">
							<c:choose>
								<c:when test="${scale == 'C'}">
									<c:set var="tempH" value="${((weather.high) -32) * (5/9)}" />

									<text style="color:red;">H:</text>
									<fmt:formatNumber type="number" maxFractionDigits="0"
										value="${tempH}" />&#176;C &nbsp
                
                <c:set var="tempL"
										value="${((weather.low) -32) * (5/9)}" />

									<text style="color:blue;">L:</text>
									<fmt:formatNumber type="number" maxFractionDigits="0"
										value="${tempL}" />&#176;C 
            </c:when>
								<c:otherwise>
									<text style="color:red;">H:</text>
									<c:out value="${weather.high}" />&#176;F &nbsp
                <text style="color:blue;">L:</text>
									<c:out value="${weather.low}" />&#176;F 
            </c:otherwise>
							</c:choose>
						</div>

						<div id="forecast">
							<c:out value="Forecast: ${weather.forecast}" />
						</div>

						<div id="warning">
							<c:if test="${weather.forecast == 'snow'}">
								<c:out value="Pack Snowshoes!" />
							</c:if>
							<c:if test="${weather.forecast == 'rain'}">
								<c:out value="Pack rain gear and wear waterproof shoes" />
							</c:if>
							<c:if test="${weather.forecast == 'thunderstorms'}">
								<c:out value="Seek shelter and avoid hiking on exposed ridges" />
							</c:if>
							<c:if test="${weather.forecast == 'sunny'}">
								<c:out value="Pack Sunblock!" />
							</c:if>
						</div>

						<div id="tempWarnings">
							<c:if test="${weather.high > 75}">
								<c:out value="Bring an extra gallon of water" />
							</c:if>
							<c:if test="${(weather.high - weather.low) > 20}">
								<c:out value="Wear breathable layers" />
							</c:if>
							<c:if test="${weather.low < 20}">
								<c:out value="Warning: Avoid exposure to frigid temperatures" />
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
			</div>
		</div>

		<br>
<div id = "conversionButton">

		<form method="post" action="parkDetails">
			<input type="hidden" name="parkCode"
				value="${park.imageName.toUpperCase()}" /> <input type="radio"
				name="scale" value="C"> Celsius <input type="radio"
				name="scale" value="F" checked> Fahrenheit <input
				style="color: white; background-color: blue;" type="submit"
				value="Convert" />
		</form>

		</div>

	</section>
</body>
</html>

<%@ include file="/WEB-INF/jsp/common/footer.jspf"%>
