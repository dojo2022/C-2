<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
新規登録


<!-- <form method="POST" action="/coordinator/LoginServlet" id="form"> -->
	<table>
		<tr>
			<td>名前</td>
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
			<td>PW確認</td>
			<td><input type="password" name="PW2" placeholder="パスワード"></td>
		</tr>

	</table>
	<input type="submit" name="REGIST" value="登録">
	<a href="/coordinator/LoginServlet">ログイン画面に戻る</a>
</body>

</html>