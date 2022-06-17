<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" type="text/css" href="/coordinator/css/login.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/login.js"></script>
<script src="js/common.js"></script>
</head>
<body>
<div class="wrapper">
ログイン

<!-- もしログイン失敗でこのページにフォワードされてきてたら、ログイン失敗と出す。 -->
	<c:if test="${result.result == false}">
		<p>ログイン失敗</p>
	</c:if>

	<form method="POST" action="/coordinator/LoginServlet" id="form">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="ID"></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="PW" placeholder="パスワード"></td>
			</tr>

		</table>
		<input type="submit" name="LOGIN" value="ログイン"> <input
			type="reset" name="LESET" value="リセット"><br>
			<a href="/coordinator/UserRegistServlet" id="userRegistBtn">初めての方はこちら</a>
	</form>
	</div>
</body>
</html>