<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DIARY EDIT</title>
<link rel="stylesheet" href="/coordinator/css/diaryEdit.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<link rel="stylesheet" href="/coordinator/css/headerFooter.css">
<script src="js/diaryEdit.js"></script>
<script src="js/common.js"></script>
</head>
<body>
<h1 align="center"><img src="/coordinator/photo/logo.PNG" alt="coodinater" ></h1>
<nav class="nav">
 <ul>
    <li><a href="/coordinator/HomeServlet">HOME</a></li>
    <li><a href="/coordinator/DiaryServlet">DIARY</a></li>
    <li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
    <li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
    <li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
</ul>
</nav>

<h3>${diary.dateStr}</h3>

<form method="POST" action="/coordinator/DiaryEditServlet" enctype="multipart/form-data">
<table align="center" class="update_table">
	<tr>
  		<td><img src="/coordinator${diary.photo}" width="243" height="405"></td>
  		<td><canvas id="preview" style="max-width:200px;"></canvas></td>
  	</tr>
  	<tr>
		<td><input type="text" name="diary_id" value="${diary.id}"class="hidden">
			画像:<input type="file" name="IMAGE" accept="image/*" onchange="previewImage(this);"></td>
		<td class="new"><p id="new">new</p></td>
	</tr>
  <tr>
  	<td colspan="2"><textarea name="note">${diary.note}</textarea>
  		<input type="submit" name="update" value="UPDATE" class="btn"></td>
  </tr>
</form>
</table>
 <a href="/coordinator/DiaryServlet"><img src="/coordinator/photo/backpage.png" alt="Back Page"></a>



</body>
</html>
<!--  <button type="button" onclick="pushItemPhotoUpdateBtn();">変更</button>
  <button type="button" onclick="pushItemPhotoDeleteBtn();">削除</button>-->
  <!-- <button type="reset" class="btn">リセット</button> -->