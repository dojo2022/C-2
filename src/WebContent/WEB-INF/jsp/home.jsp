<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
<link rel="stylesheet" href="/coordinator/css/home.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<link rel="stylesheet" href="/coordinator/css/headerFooter.css">
<script src="js/home.js"></script>
<script src="js/common.js"></script>
</head>
<body>
<h1 align="center"><img src="/coordinator/photo/logo.PNG" alt="coodinater" ></h1>
<nav class="nav">
 <ul>
    <li><a href="/coordinator/HomeServlet">HOME</a></li>
    <li><a href="/coordinator/DiaryServlet">DIARY</a></li>
    <li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
    <li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
    <li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
</ul>
</nav>

<h3 align="center">
<c:set value="1" var="count"></c:set>
<c:forEach var="weather_fourhour" items="${oneDayWeather}">
<c:if test="${count==1}">
${weather_fourhour.dateStr}
</c:if>
<c:set value="${count + 1}" var="count"></c:set>
</c:forEach>
RECOMMENDS and FORECAST</h3>

<div class="wrapper">
    <div class="recommends">
    	<c:set value = "1" var="count"></c:set>
    	<c:forEach var="item" items="${recommends}">
    		<c:choose>
        		<c:when test="${count == 1}">
        			<img id="jk_photo" src="/coordinator/${item.photo}" width="224" height="268" alt="jk">
        		</c:when>
        		<c:when test="${count == 2}">
        			<img id="tops_photo" src="/coordinator/${item.photo}" width="200" height="271" alt="tops">
        		</c:when>
        		<c:when test="${count == 3}">
        			<img id="bottoms_photo" src="/coordinator/${item.photo}" width="212" height="288" alt="bottoms">
        		</c:when>
        		<c:when test="${count == 4}">
        			<img id="shoes_photo" src="/coordinator/${item.photo}" width="224" height="142" alt="shoes">
        		</c:when>
        		<c:when test="${count == 5}">
        			<img id="outer_photo" src="/coordinator/${item.photo}" width="148" height="275" alt="outer">

        		</c:when>
    		</c:choose>
    		<c:set value = "${count + 1}" var="count"></c:set>
    	</c:forEach>
    	<c:remove var="count"/>

    <!--
    <img id="jk_photo" src="/coordinator/photo/4.PNG" width="224" height="268" alt="aaaaa">
    <img id="tops_photo" src="/coordinator/photo/21.PNG" width="200" height="271" alt="aaaaa">
    <img id="botoms_photo" src="/coordinator/photo/8.PNG" width="212" height="288" alt="aaaaa">
    <img id="shoes_photo" src="/coordinator/photo/16.PNG" width="224" height="142" alt="aaaaa">
    <img id="outer_photo" src="/coordinator/photo/17.PNG" width="148" height="275" alt="aaaaa">
    -->
    </div>
    <div class="oneday_weather">
    <table class="ondayWeather_table">
        <th>MORNING</th><th>AFTERNOON</th><th>EVENING</th>
		<tr>
		<c:set value = "1" var="count"></c:set>
		<c:forEach var="weather_fourhour" items="${oneDayWeather}">
        <td>
         <c:choose>
        <c:when test="${weather_fourhour.weatherCode == 1 && count != 3}">
        	<img src="/coordinator/photo/sunny.PNG" alt="晴れ">
        </c:when>
        <c:when test="${weather_fourhour.weatherCode == 2}">
        	<img src="/coordinator/photo/cloudy.png" alt="曇り">
        </c:when>
        <c:when test="${weather_fourhour.weatherCode == 3}">
        	<img src="/coordinator/photo/rainy.PNG" alt="雨">
        </c:when>
        <c:when test="${weather_fourhour.weatherCode == 4}">
        	<img src="/coordinator/photo/snowy.PNG" alt="雪">
        </c:when>
        <c:when test="${weather_fourhour.weatherCode == 1 && count==3}">
        	<img src="/coordinator/photo/moon.PNG" alt="月">
        </c:when>
        </c:choose>
        </td>
        <c:set value="${count + 1}" var="count"></c:set>
        </c:forEach>
        <c:remove var="count"/>

        <tr class="max">
        <c:forEach var="weather_fourhour" items="${oneDayWeather}">
        <td>${weather_fourhour.highestTemperature}</td>
        </c:forEach>
        </tr>
        <tr class="min">
        <c:forEach var="weather_fourhour" items="${oneDayWeather}">
        <td>${weather_fourhour.lowestTemperature}</td>
        </c:forEach>
        </tr>
         <tr>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    </tr>
    <tr class="wind">
        <c:forEach var="weather_fourhour" items="${oneDayWeather}">
        <td>${weather_fourhour.wind}m</td>
        </c:forEach>
        </tr>
    </table>
    </div>
</div>

<br>
<br>

<h3 align="center">HISTORY</h3>
<!-- 過去3日分の服 -->
<table align="center" class="history">
	<c:forEach var="history" items="${diaryList}">
    <th>
    ${history.dateStr}
    <!--
    	日付
    	-->
    </th>
    </c:forEach>
    <tr class="history_photo">
    	<c:forEach var="history" items="${diaryList}">
    	<td><img src="/coordinator/${history.photo}" width="162" height="270"></td>
    	</c:forEach>
    </tr>
        <!--　<td><img src="/coordinator/photo/aaaaa1.png" width="162" height="270" alt="aaaaa"></td>
        <td>&nbsp;</td>
        <td><img src="/coordinator/photo/aaaaa2.png" width="162" height="270" alt="aaaaa"></td>
        <td>&nbsp;</td>
        <td><img src="/coordinator/photo/aaaaa3.png" width="162" height="270" alt="aaaaa"></td>  -->

</table>
<a href="/coordinator/DiaryServlet"><img class="viewmore" src="/coordinator/photo/viewmore.PNG"  alt="View More"></a>

<br>
<br>

<h3 align="center">FORECAST</h3>
<table border="1" style="border-collapse: collapse" align="center" >

	<c:forEach var="weather_week" items="${weeklyWeather}">
    <th>
    ${weather_week.dateStr}
    </th>
    </c:forEach>

    <tr>
    	<c:forEach var="weather_week" items="${weeklyWeather}">
        <td>
         <c:choose>
        <c:when test="${weather_week.weatherCode == 1}">
        	<img src="/coordinator/photo/sunny.PNG" alt="晴れ">
        </c:when>
        <c:when test="${weather_week.weatherCode == 2}">
        	<img src="/coordinator/photo/cloudy.png" alt="曇り">
        </c:when>
        <c:when test="${weather_week.weatherCode == 3}">
        	<img src="/coordinator/photo/rainy.PNG" alt="雨">
        </c:when>
        <c:when test="${weather_week.weatherCode == 4}">
        	<img src="/coordinator/photo/snowy.PNG" alt="雪">
        </c:when>
        </c:choose>
        </td>
        </c:forEach>
    </tr>
    <tr class="max">
    <c:forEach var="weather_week" items="${weeklyWeather}">
        <td>${weather_week.highestTemperature}</td>
    </c:forEach>
    </tr>
    <tr class="min">
    	<c:forEach var="weather_week" items="${weeklyWeather}">
        <td>${weather_week.lowestTemperature}</td>
    </c:forEach>
    </tr>
    <tr>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    	<td><img src="/coordinator/photo/wind.PNG" alt="風"></td>
    </tr>
    <tr class="wind">
    <c:forEach var="weather_week" items="${weeklyWeather}">
        <td>${weather_week.wind}m</td>
    </c:forEach>
    </tr>
</table>
<p class="time">Weather from 07:00 to 19:00</p>
</body>
</html>