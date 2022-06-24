<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<h3 align="center">
	</nav>

<form method="POST" action="/coordinator/DiaryEditServlet">
  <img src="/coordinator${diary.photo}">
  <button type="button" onclick="pushItemPhotoUpdateBtn();">変更</button>
  <button type="button" onclick="pushItemPhotoDeleteBtn();">削除</button>
  <br>
  <input type="text" name="diary_id" value="${diary.id}"class="hidden">
  画像:<input type="file" name="IMAGE" accept="image/*" onchange="previewImage(this);"><br>
		<canvas id="preview" style="max-width:200px;"></canvas>
    <br>
  <textarea name="note">${diary.note}</textarea>
  <button type="reset" class="btn">リセット</button>
  <br>
  <input type="submit" name="update" value="更新" class="btn">
</form>
<a href="/coordinator/DiaryServlet">日記画面に戻る</a>

</body>
</html>

</body>
</html>