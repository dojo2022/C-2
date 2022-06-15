<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/coordinator/css/itemUpdate.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/itemUpdate.js"></script>
<script src="js/common.js"></script>
</head>
<body>
登録
<nav class="nav">
 <ul>
<li><a href="/coordinator/HomeServlet">HOME</a></li>
<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
<li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
</ul>
</nav>
<a href="/coordinator/ItemSearchServlet"></a>
<!-- <form method="POST" action="/coordinator/LoginServlet" id="form"> -->

		<input type="submit" name="UPDATE" value="更新"> <input
			type="submit" name="DELETE" value="削除">
<!-- </form> -->


</body>


</html>