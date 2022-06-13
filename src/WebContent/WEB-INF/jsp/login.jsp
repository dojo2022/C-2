<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" type="text/css" href="/coordinator/css/login.css">
</head>
<body>
ログイン
  <a href="/coordinator/UserRegistServlet">新規会員登録はこちら</a>

	<!-- <form method="POST" action="/coordinator/LoginServlet" id="form"> -->
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
			type="reset" name="LESET" value="リセット">

</body>
</html>