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
<script src="js/home.js"></script>
<script src="js/common.js"></script>
</head>
<body>

<h1>Coordinator</h1>
<nav class="nav">
 <ul>
<li><a href="/coordinator/HomeServlet">HOME</a></li>
<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
<li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
</ul>
</nav>
<h3>Recommends and Forecast</h3>
<h3>History</h3>
<a href="/coordinator/DiaryServlet">ViewMore</a>
<h3>Forecast</h3>
</body>
</html>