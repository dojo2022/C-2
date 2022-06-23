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
			<img src="/coordinator/photo/logo.PNG" alt="coordinator">
		</h1>
		<h2>ログイン</h2>
		<c:if test="${result.result == false}">

		</c:if>
		<form method="POST" action="/coordinator/LoginServlet" id="form">
			<div id="table">
				<table id="list" align="center">
					<tr>
						<td><label>ID<br> <input type="text" name="ID">
						</label></td>
					</tr>
					<tr>
						<td><label>PW<br> <input type="password"
								name="PW" placeholder="パスワード">
						</label></td>
					<tr>
					<tr>
						<td colspan="2"><input type="submit" name="submit"
							value="ログイン"> <input type="reset" name="reset"
							value="リセット"> <span id="error_message"></span>
							<c:if test="${result.result == false}">
								<p>ログインに失敗しました。</p>
							</c:if>
						<td>
					</tr>
					<tr>
						<td><a href="/coordinator/UserRegistServlet"
							id="userRegistBtn">初めての方はこちら</a></td>
					</tr>
				</table>
		</form>
		<!-- メイン（ここまで） -->


	</div>

</body>
</html>

