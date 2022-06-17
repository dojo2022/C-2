<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <img src="\test\logo.PNG" alt="coordinator">
        </h1>
        <h2>ログイン</h2>
        <form id="login_form" method="GET" action="index.html">
          <div id ="table">
            <table id="list" align="center">
            <tr>
              <td>
                <label>ユーザーID<br>
                <input type="text" name="user_id">
                </label>
              </td>
            </tr>
            <tr>
              <td>
                <label>パスワード<br>
                <input type="password" name="password">
                </label>
              </td>
            <tr>
            <tr>
              <td colspan="2">
                <input type="submit" name="submit" value="ログイン">
                <input type="reset" name="reset" value="リセット">
                <span id="error_message"></span>
              <td>
            </tr>
            <tr>
                <td>
                <a href="/coordinator/UserRegistServlet" id="userRegistBtn">初めての方はこちら</a>
                </td>
            </tr>
          </table>
        </form>
        <!-- メイン（ここまで） -->


      </div>



<!--
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
    -->