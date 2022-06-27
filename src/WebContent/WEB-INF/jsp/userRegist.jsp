<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/coordinator/css/userRegist.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/userRegist.js"></script>
<script src="js/common.js"></script>
</head>
<body>

<img src="/coordinator/photo/logo.PNG" alt="coordinator" width="162" height="37">
<div class="user">
<h3>MEMBER REGISTRATION</h3>
 <form method="POST" action="/coordinator/LoginServlet" id="form">
 <div id= "table">
	<table id="list" align="center">
		<tr>
			<td>NAME</td>
			<td><input type="text" name="NAME"></td>
		</tr>
		<tr>
			<td>ID</td>
			<td><input type="text" name="ID"></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="PW" placeholder="パスワード"></td>
		</tr>
		<tr>
			<td>PW(確認)</td>
			<td><input type="password" name="PW2" placeholder="パスワード(確認用)"></td>
		</tr>
		<tr>
	<td colspan="2">
    <p align="center"><input type="submit" name="REGIST" value="REGIST" class="btn"></p>
    </td>
    </tr>
	</table>


	</div>

</form>

<a href="/coordinator/LoginServlet"><img src="/coordinator/photo/backpage.png" alt="Back Page" class="backpage"></a>
</div>
</body>

</html>