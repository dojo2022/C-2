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
<script src="js/diary.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	diary
	<nav class="nav">
		<ul>
			<li><a href="/coordinator/HomeServlet">HOME</a></li>
			<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
			<li><a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
			<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
			<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
		</ul>
		<h1>DIARY</h1>
		<div id="table">
			<table id="list">
				<tr>
					<th>日付</th>
					<th>画像</th>
					<th></th>
					<th>天気</th>
					<th></th>
					<th>コメント</th>
				</tr>
				<!--
        <tr class="data_row"><td>2022-05-31</td><td><img src="\dojo6\src\WebContent\photo\aaaaa5.png" alt="aaaaa"></td><td><img src="\test\sunny.PNG"></td><div class="maxTemp"><td>25</td></div><div class="minTemp"><td>18</td></div><td><img src="\test\wind.PNG"></td><td>4m</td><td><input type="text"></td></tr>
      -->
				<c:forEach var="diary_list" items="${diaryList}">
					<tr>
						<td rowspan="2">${diary_list.date}</td>
						<td rowspan="2"><img src="/coordinator/${diary_list.photo}"></td>
						<td><c:choose>
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
						<td>${diary_list.maxTemperature}</td>
						<td><img src="/coordinator/photo/wind.PNG"></td>
						<td rowspan="2">${diary_list.note}</td>
					</tr>
					<tr class="data_row">
						<td>${diary_list.amountOfRain}</td>
						<td>${diary_list.minTemperature}</td>
						<td>${diary_list.windSpeed}m</td>
						<td></td>
					</tr>
				</c:forEach>


				<a href="/coordinator/DiaryEditServlet">日記の編集</a>
			</table>
		</div>
	</nav>
</body>
</html>



