<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
<li><a href="//RegistServlet">HOME</a></li>
<nav>
 <ul>
<li><a href="/coordinator/HomeServlet">HOME</a></li>
<li><a href="/coordinator/DiaryServlet">diary</a></li>
<li> <a href="/coordinator/ItemSearchServlet">search</a></li>
<li><a href="/coordinator/ItemRegistUpdateServlet">resist</a></li>
<li><a href="/coordinator/LoginServlet">log out</a></li>
</ul>
</nav>
<a href="/coordinator/DiaryServlet">ViewMore</a>
</body>
</html>