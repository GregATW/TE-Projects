<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/common/header.jspf"%>

<link rel="stylesheet" href="css/npgeek.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<section id="survey-main-content">

	<c:url var="surveyUrl" value="/survey" />

	<br>

	<h1>National Parks Survey</h1>

	<br>

	<form:form method="POST" action="${surveyUrl}" modelAttribute = "survey">
		<label for="parkCode">Favorite National Park</label>
		<form:errors path = "parkCode" cssClass = "error" />
		<form:select path="parkCode">
<%-- 			<form:option value="default">*Please Select a National Park*</form:option> --%>
			<form:option value="CVNP">Cuyahoga Valley National Park</form:option>
			<form:option value="ENP">Everglades National Park</form:option>
			<form:option value="GCNP">Grand Canyon National Park</form:option>
			<form:option value="GNP">Glacier National Park</form:option>
			<form:option value="GSMNP">Great Smokey Mountains National Park</form:option>
			<form:option value="GTNP">Grand Teton National Park</form:option>
			<form:option value="MRNP">Mount Rainier National Park</form:option>
			<form:option value="RMNP">Rocky Mountain National Park</form:option>
			<form:option value="YNP">Yellowstone National Park</form:option>
			<form:option value="YNP2">Yosemite National Park</form:option>			
		</form:select>
		
		<br>

		<label for="email">Your email</label>
		<form:input path = "emailAddress" style="padding-left: 40px; margin-left: 104px"/>
<%-- 		<form:input type="text" name="email" id="email" /> --%>
		<form:errors path = "emailAddress" cssClass = "error" />
		<br>

		<label for="state" style="margin-right:28px">State of Residence</label>
		<form:select path = "state" style ="color:black; font-size:16px">
 		<form:errors path = "state" cssClass = "error" />
<%--   			<form:option value=" ">*Please Select a State*</form:option>
 --%>		<form:option value="Alabama">Alabama</form:option>
			<form:option value="Alaska">Alaska</form:option>
			<form:option value="Arizona">Arizona</form:option>
			<form:option value="Arkansas">Arkansas</form:option>
			<form:option value="California">California</form:option>
			<form:option value="Colorado">Colorado</form:option>
			<form:option value="Connecticut">Connecticut</form:option>
			<form:option value="Delaware">Delaware</form:option>
			<form:option value="Florida">Florida</form:option>
			<form:option value="Georgia">Georgia</form:option>
			<form:option value="Hawaii">Hawaii</form:option>
			<form:option value="Idaho">Idaho</form:option>
			<form:option value="Illinois">Illinois</form:option>
			<form:option value="Indiana">Indiana</form:option>
			<form:option value="Iowa">Iowa</form:option>
			<form:option value="Kansas">Kansas</form:option>
			<form:option value="Kentucky">Kentucky</form:option>
			<form:option value="Louisiana">Louisiana</form:option>
			<form:option value="Maine">Maine</form:option>
			<form:option value="Maryland">Maryland</form:option>
			<form:option value="Massachusetts">Massachusetts</form:option>
			<form:option value="Michigan">Michigan</form:option>
			<form:option value="Minnesota">Minnesota</form:option>
			<form:option value="Mississippi">Mississippi</form:option>
			<form:option value="Missouri">Missouri</form:option>
			<form:option value="Montana">Montana</form:option>
			<form:option value="Nebraska">Nebraska</form:option>
			<form:option value="Nevada">Nevada</form:option>
			<form:option value="New Hampshire">New Hampshire</form:option>
			<form:option value="New Jersey">New Jersey</form:option>
			<form:option value="New Mexico">New Mexico</form:option>
			<form:option value="New York">New York</form:option>
			<form:option value="North Carolina">North Carolina</form:option>
			<form:option value="North Dakota">North Dakota</form:option>
			<form:option value="Ohio">Ohio</form:option>
			<form:option value="Oklahoma">Oklahoma</form:option>
			<form:option value="Oregon">Oregon</form:option>
			<form:option value="Pennsylvania">Pennsylvania</form:option>
			<form:option value="Rhode Island">Rhode Island</form:option>
			<form:option value="South Carolina">South Carolina</form:option>
			<form:option value="South Dakota">South Dakota</form:option>
			<form:option value="Tennessee">Tennessee</form:option>
			<form:option value="Texas">Texas</form:option>
			<form:option value="Utah">Utah</form:option>
			<form:option value="Vermont">Vermont</form:option>
			<form:option value="Virginia">Virginia</form:option>
			<form:option value="Washington">Washington</form:option>
			<form:option value="West Virginia">West Virginia</form:option>
			<form:option value="Wisconsin">Wisconsin</form:option>
			<form:option value="Wyoming">Wyoming</form:option>
		</form:select>

		<br>

		<label for="activityLevel">Activity level</label>
		<form:errors path = "activityLevel" cssClass = "error" />
		<div><form:radiobutton path="activityLevel" value="inactive"/> Inactive</div>
		<div><form:radiobutton path="activityLevel" value="sedentary"/> Sedentary</div>
		<div><form:radiobutton path="activityLevel" value="active"/> Active</div>
		<div><form:radiobutton path="activityLevel" value="extremely active"/> Extremely Active</div>
		<br>

		<div id="submit">
			<input style="color: white" type="submit"
				value="Submit" />
		</div>

	</form:form>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jspf"%>