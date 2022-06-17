<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DIARY</title>
<link rel="stylesheet" href="/coordinator/css/diary.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/diary.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	diary
	<nav class="nav">
		<ul>
			<li><a href="/coordinator/HomeServlet">HOME</a></li>
			<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
			<li><a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
			<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
			<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
		</ul>
		<h1>DIARY</h1>
		<div id="table">
			<table id="list">
				<tr>
					<th>日付</th>
					<th>画像</th>
					<th></th>
					<th>天気</th>
					<th></th>
					<th>コメント</th>
				</tr>
				<!--
        <tr class="data_row"><td>2022-05-31</td><td><img src="\dojo6\src\WebContent\photo\aaaaa5.png" alt="aaaaa"></td><td><img src="\test\sunny.PNG"></td><div class="maxTemp"><td>25</td></div><div class="minTemp"><td>18</td></div><td><img src="\test\wind.PNG"></td><td>4m</td><td><input type="text"></td></tr>
      -->
				<tr>
					<td rowspan="2">2022-05-31</td>
					<td rowspan="2"><img src="/coordinator/photo/aaaaa5.png"
						width="224" height="268" alt="aaaaa"></td>
					<td><img src="/coordinator/photo/sunny.PNG"></td>
					<td>25</td>
					<td><img src="/coordinator/photo/wind.PNG"></td>
					<td rowspan="2"><p id="diarycomment">〇〇とランチ。キレイめ。</p></td>
				</tr>
				<tr class="data_row">
					<td>10mm</td>
					<td>18</td>
					<td>4m</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">2022-06-01</td>
					<td rowspan="2"><img src="/coordinator/photo/aaaaa4.png"
						width="224" height="268" alt="aaaaa"></td>
					<td><img src="/coordinator/photo/cloudy.png"></td>
					<td>23</td>
					<td><img src="/coordinator/photo/wind.PNG"></td>
					<td rowspan="2"><p id="diarycomment">取引先とディナークルーズ。きれいめ。</p></td>
				</tr>
				<tr class="data_row">
					<td>10mm</td>
					<td>18</td>
					<td>4m</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">2022-06-02</td>
					<td rowspan="2"><img src="/coordinator/photo/aaaaa3.png"
						width="224" height="268" alt="aaaaa"></td>
					<td><img src="/coordinator/photo/rainy.PNG"></td>
					<td>21</td>
					<td><img src="/coordinator/photo/wind.PNG"></td>
					<td rowspan="2"><p id="diarycomment">同期会。</p></td>
				</tr>
				<tr class="data_row">
					<td>10mm</td>
					<td>18</td>
					<td>4m</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">2022-06-03</td>
					<td rowspan="2"><img src="/coordinator/photo/aaaaa2.png"
						width="224" height="268" alt="aaaaa"></td>
					<td><img src="/coordinator/photo/rainy.PNG"></td>
					<td>26</td>
					<td><img src="/coordinator/photo/wind.PNG"></td>
					<td rowspan="2"><p id="diarycomment">得意先訪問。キレイ目。</p></td>
				</tr>
				<tr class="data_row">
					<td>10mm</td>
					<td>18</td>
					<td>4m</td>
					<td></td>
				</tr>
				<tr class="data_row">
					<td rowspan="2">2022-06-04</td>
					<td rowspan="2"><img src="/coordinator/photo/aaaaa1.png"
						width="224" height="268" alt="aaaaa"></td>
					<td><img src="/coordinator/photo/sunny.PNG"></td>
					<td>25</td>
					<td><img src="/coordinator/photo/wind.PNG"></td>
					<td rowspan="2"><p id="diarycomment">出張</p></td>
				</tr>
				<tr class="data_row">
					<td>10mm</td>
					<td>18</td>
					<td>4m</td>
					<td></td>
				</tr>


				<a href="/coordinator/DiaryEditServlet">日記の編集</a>
			</table>
		</div>
	</nav>
</body>
</html>



