<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ItemRegist</title>
<link rel="stylesheet" href="/coordinator/css/itemRegist.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/itemRegist.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	アイテム登録ページ
	<nav class="nav">
		<ul>
			<li><a href="/coordinator/HomeServlet">HOME</a></li>
			<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
			<li><a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
			<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
			<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
		</ul>
	</nav>
	<form method="POST" action="/coordinator/ItemRegistServlet">
		<table>
			<tr>
				<td><input type="submit" name="REGIST" value="登録"> <input
					type="reset" name="reset" value="リセット"></td>
			</tr>
		</table>
	</form>
</body>
</html>