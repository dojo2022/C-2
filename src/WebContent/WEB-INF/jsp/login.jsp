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
		<h1 id="logo">
			<img src="/coordinator/photo/login_logo.PNG" alt="coordinator" >
		</h1>

		<c:if test="${result.result == false}">

		</c:if>
		<form method="POST" action="/coordinator/LoginServlet" id="form">
				<table id="list" align="center">
					<tr>
						<td>ID</td>
						<td><label><input type="text" name="ID"></label></td>
					</tr>
					<tr>
						<td>PW</td>
						<td><label><input type="password"name="PW" placeholder="パスワード"></label></td>
					<tr>
					<tr>
						<td colspan="2"><input type="submit" name="submit"
							value="LOGIN" class="btn"> <input type="reset" name="reset"
							value="RESET" class="btn"> <span id="error_message"></span>
							<c:if test="${result.result == false}">
								<p class="error_message">ログインに失敗しました。</p>
							</c:if>
						<td>
					</tr>
					<!--  <tr>
						<td><a href="/coordinator/UserRegistServlet"
							id="userRegistBtn">初めての方はこちら</a></td>
					</tr>-->
				</table>
		</form>
	<a href="/coordinator/UserRegistServlet"><img src="/coordinator/photo/signup.png" alt="はじめての方はこちら" width="204" height="44" class="signup"></a>

	</div>

</body>
</html>

