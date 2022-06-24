<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DIARY</title>
<link rel="stylesheet" href="/coordinator/css/diary.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<link rel="stylesheet" href="/coordinator/css/headerFooter.css">
<script src="js/diary.js"></script>
<script src="js/common.js"></script>
</head>
<body>
<h1 align="center"><img src="/coordinator/photo/logo.PNG" alt="coodinater" ></h1>
	<br>
	<nav class="nav">
		<ul>
			<li><a href="/coordinator/HomeServlet">HOME</a></li>
			<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
			<li><a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
			<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
			<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
		</ul>
	</nav>
	<br>
		<div align="center"><h3>DIARY</h3>
		<form method="POST" action="/coordinator/DiaryServlet" >
		<input type="date" name="startDate">～
		<input type="date" name="endDate">
		<input type="submit" name="search" value="SEARCH" class="searchbtn">
		</form>
		</div>
		<br>
		<div id="table">
			<table id="list" align="center">
				<tr>
					<th>DATE</th>
					<th>COORDINATE</th>
					<th></th>
					<th>WEATHER / COMMENT</th>
					<th></th>
					<th></th>

				</tr>
				<c:forEach var="diary_list" items="${diaryList}">
					<tr>
						<td rowspan="3">${diary_list.dateStr}</td>
						<td rowspan="3"><img src="/coordinator/${diary_list.photo}" width="257" height="375"></td>
						<td rowspan="2"><c:choose>
								<c:when
									test="${diary_list.weatherCode == 1}">
									<img src="/coordinator/photo/sunny.PNG" alt="晴れ">
								</c:when>
								<c:when test="${diary_list.weatherCode == 2}">
									<img src="/coordinator/photo/cloudy.png" alt="曇り">
								</c:when>
								<c:when test="${diary_list.weatherCode == 3}">
									<img src="/coordinator/photo/rainy.PNG" alt="雨">
								</c:when>
								<c:when test="${diary_list.weatherCode == 4}">
									<img src="/coordinator/photo/snowy.PNG" alt="雪">
								</c:when>
							</c:choose></td>
						<td class="max">${diary_list.maxTemperature}</td>
						<td><img src="/coordinator/photo/rain.PNG" width="80" height="96"></td>
						<td>${diary_list.amountOfRain}mm</td>
					</tr>
					<tr>
						<td class="min">${diary_list.minTemperature}</td>
						<td><img src="/coordinator/photo/wind.PNG" width="80" height="96"></td>
						<td class="wind">${diary_list.windSpeed}m</td>
					</tr>
					<tr>
						<td colspan="3" width="300">${diary_list.note}</td>
					<!--<td></td>
						<td></td>
					-->
						<td><form method="POST" action="/coordinator/DiaryEditServlet"><input type="text" name="diary_id" value=${diary_list.id} class="hidden"><input type="submit" name="edit" value="編集" class="btn"></form></td>
					</tr>
					<tr class="data_row"><td colspan="6"></td></tr>
				</c:forEach>
				<!-- <a href="/coordinator/DiaryEditServlet">日記の編集</a> -->
			</table>
		</div>
</body>
</html>



