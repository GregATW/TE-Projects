



<p>This example calls a function which returns the value of PI:</p>

<p id="today"></p>

<script>
function myFunction() {
	var current = new Date();
	return current;
}
document.getElementById("today").innerHTML = myFunction().toDateString();
</script>

<p id="followingDay"></p>
<script>
function myFunction() {
	var current = new Date();
	var followingDay = new Date(current.getTime() + 86400000);
	return followingDay;
}
document.getElementById("followingDay").innerHTML = myFunction().toDateString();
</script>
		
<p id="followingDay2"></p>
<script>
function myFunction() {
	var current = new Date();
	var followingDay2 = new Date(current.getTime() + 172800000);
	return followingDay2;
}
document.getElementById("followingDay2").innerHTML = myFunction().toDateString();
</script>

<p id="followingDay3"></p>
<script>
function myFunction() {
	var current = new Date();
	var followingDay3 = new Date(current.getTime() + 259200000);
	return followingDay3;
}
document.getElementById("followingDay3").innerHTML = myFunction().toDateString();
</script>	

<p id="followingDay4"></p>
<script>
function myFunction() {
	var current = new Date();
	var followingDay4 = new Date(current.getTime() + 345600000);
	return followingDay4;
}
document.getElementById("followingDay4").innerHTML = myFunction().toDateString();
</script>					
		
<!-- 		<button onclick="myFunction()">Try it</button>
 -->
<p id="demo"></p>

<script>
function myFunction() {
	
/* 	var current = new Date(); //'Mar 11 2015' current.getTime() = 1426060964567
 */ 	var followingDay = new Date(current.getTime() + 86400000); // + 1 day in ms
	followingDay.toLocaleDateString();
/* 	var following2Days = new Date(current.getTime() + 172800000);
	following2Days.toLocaleDateString();
	var following3Days = new Date(current.getTime() + 259200000); 
	following3Days.toLocaleDateString();
	var following4Days = new Date(current.getTime() + 345600000); 
	following4Days.toLocaleDateString(); */
}
     document.getElementById("demo").innerHTML = myfunction();
    
      /*  document.getElementById("demo").innerHTML = followingDay;
       document.getElementById("demo").innerHTML = following2Days;
       document.getElementById("demo").innerHTML = following3Days;
       document.getElementById("demo").innerHTML = following4Days; */

/* } */
</script> 


<!-- --------NEW DAYS---------- Attempting to add dynamic days of week to weather forecast-->
						
						<h2 class="day" style="font-size: 24px">
							
							<c:if test="${weather.fiveDayForecastValue == 1}">
							Today</c:if>
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