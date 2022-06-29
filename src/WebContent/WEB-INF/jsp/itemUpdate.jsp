<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>itemUpdate</title>
<link rel="stylesheet" href="/coordinator/css/itemRegist.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<link rel="stylesheet" href="/coordinator/css/headerFooter.css">
<script src="js/itemSearch.js"></script>
<script src="js/common.js"></script>
</head>

<body>
<h1 align="center"><img src="/coordinator/photo/logo.PNG" alt="coodinater" ></h1>
	<nav class="nav">
		<ul>
			<li><a href="/coordinator/HomeServlet">HOME</a></li>
			<li><a href="/coordinator/DiaryServlet">DIARY</a></li>
			<li><a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
			<li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
			<li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
		</ul>
	</nav>
	<main>
	<h3 align="center">UPDATE</h3>
		<form method="POST" action="/coordinator/ItemRegistUpdateServlet" enctype="multipart/form-data">
			<table class="regist_table" align="center">
				<tr>
					<td>
						<div class="multiselect">
							<div class="selectBox" onclick="showCheckboxes()">

								<p class="pulldown_name">季節</p>
								<select>
									<option>複数選択可</option>
								</select>
								<div class="overSelect"></div>
							</div>
							<div id="checkboxes">
								<label> <c:if test="${colorSeason.spring}">
										<input type="checkbox" name="spring" checked>
									</c:if> <c:if test="${!colorSeason.spring}">
										<input type="checkbox" name="spring">
									</c:if> 春
								</label> <label> <c:if test="${colorSeason.summer}">
										<input type="checkbox" name="summer" checked>
									</c:if> <c:if test="${!colorSeason.summer}">
										<input type="checkbox" name="summer">
									</c:if> 夏
								</label> <label> <c:if test="${colorSeason.autumn}">
										<input type="checkbox" name="autumn" checked>
									</c:if> <c:if test="${!colorSeason.autumn}">
										<input type="checkbox" name="autumn">
									</c:if> 秋
								</label> <label> <c:if test="${colorSeason.winter}">
										<input type="checkbox" name="winter" checked>
									</c:if> <c:if test="${!colorSeason.winter}">
										<input type="checkbox" name="winter">
									</c:if> 冬
								</label>
							</div>
						</div>
					</td>
					<td><label>パーツ<br>
					<select name="parts">
					<c:if test="${item.partsCode == 1}">
								<option value="outer" selected>アウター</option>
					</c:if>
					<c:if test="${item.partsCode != 1}">
								<option value="outer">アウター</option>
					</c:if>
					<c:if test="${item.partsCode == 2}">
								<option value="jacket" selected>ジャケット</option>
					</c:if>
					<c:if test="${item.partsCode != 2}">
								<option value="jacket">ジャケット</option>
					</c:if>
					<c:if test="${item.partsCode == 3}">
								<option value="tops" selected>トップス</option>
					</c:if>
					<c:if test="${item.partsCode != 3}">
								<option value="tops">トップス</option>
					</c:if>
					<c:if test="${item.partsCode == 4}">
								<option value="skirt" selected>スカート</option>
					</c:if>
					<c:if test="${item.partsCode != 4}">
								<option value="skirt">スカート</option>
					</c:if>
					<c:if test="${item.partsCode == 5}">
								<option value="pants" selected>パンツ</option>
					</c:if>
					<c:if test="${item.partsCode != 5}">
								<option value="pants">パンツ</option>
					</c:if>
					<c:if test="${item.partsCode == 6}">
								<option value="shoes" selected>シューズ</option>
					</c:if>
					<c:if test="${item.partsCode != 6}">
								<option value="shoes">シューズ</option>
					</c:if>
						</select>
					</label></td>
					<td>
						<div class="multiselect">
							<div class="selectBox" onclick="showCheckboxeses()">

								<p class="pulldown_name">色</p>
								<select>
									<option>複数選択可</option>
								</select>
								<div class="overSelect"></div>
							</div>
							<div id="checkboxeses">
								<label> <c:if test="${colorSeason.white}">
										<input type="checkbox" name="white" checked>
									</c:if> <c:if test="${!colorSeason.white}">
										<input type="checkbox" name="white">
									</c:if> 白
								</label> <label> <c:if test="${colorSeason.black}">
										<input type="checkbox" name="black" checked>
									</c:if> <c:if test="${!colorSeason.black}">
										<input type="checkbox" name="black">
									</c:if> 黒
								</label> <label> <c:if test="${colorSeason.grey}">
										<input type="checkbox" name="grey" checked>
									</c:if> <c:if test="${!colorSeason.grey}">
										<input type="checkbox" name="grey">
									</c:if> グレー
								</label> <label> <c:if test="${colorSeason.beige}">
										<input type="checkbox" name="beige" checked>
									</c:if> <c:if test="${!colorSeason.beige}">
										<input type="checkbox" name="beige">
									</c:if> ベージュ
								</label> <label> <c:if test="${colorSeason.red}">
										<input type="checkbox" name="red" checked>
									</c:if> <c:if test="${!colorSeason.red}">
										<input type="checkbox" name="red">
									</c:if> 赤系
								</label> <label> <c:if test="${colorSeason.blue}">
										<input type="checkbox" name="blue" checked>
									</c:if> <c:if test="${!colorSeason.blue}">
										<input type="checkbox" name="blue">
									</c:if> 青系
								</label> <label> <c:if test="${colorSeason.green}">
										<input type="checkbox" name="green" checked>
									</c:if> <c:if test="${!colorSeason.green}">
										<input type="checkbox" name="green">
									</c:if> 緑系
								</label> <label> <c:if test="${colorSeason.yellow}">
										<input type="checkbox" name="yellow" checked>
									</c:if> <c:if test="${!colorSeason.yellow}">
										<input type="checkbox" name="yellow">
									</c:if> 黄系
								</label> <label> <c:if test="${colorSeason.other}">
										<input type="checkbox" name="other" checked>
									</c:if> <c:if test="${!colorSeason.other}">
										<input type="checkbox" name="other">
									</c:if> その他
								</label>
							</div>
						</div>
					</td>
					</tr>
					<tr>
					<td><c:if test="${item.pattern == 1}">
							<label>柄<br> <input type="radio" name="pattern_yes"
								checked>あり
							</label>
							<label> <input type="radio" name="pattern_no">なし
							</label>
						</c:if> <c:if test="${item.pattern == 0}">
							<label>柄<br> <input type="radio" name="pattern_yes">あり
							</label>
							<label> <input type="radio" name="pattern_no" checked>なし
							</label>
						</c:if></td>
					<td><c:if test="${item.rain == 1}">
							<label>雨<br> <input type="radio" name="rain_ok"
								checked>可
							</label>
							<label> <input type="radio" name="rain_ng">不可
							</label>
						</c:if> <c:if test="${item.rain == 0}">
							<label>雨<br> <input type="radio" name="rain_ok">可
							</label>
							<label> <input type="radio" name="rain_ng" checked>不可
							</label>
						</c:if></td>
					<td><c:if test="${item.wind == 1}">
							<label>風<br> <input type="radio" name="wind_ok"
								checked>可
							</label>
							<label> <input type="radio" name="wind_ng">不可
							</label>
						</c:if> <c:if test="${item.wind == 0}">
							<label>風<br> <input type="radio" name="wind_ok">可
							</label>
							<label> <input type="radio" name="wind_ng" checked>不可
							</label>
						</c:if></td>
				</tr>

				<tr>
					<td><img src="/coordinator/${item.photo}"><br>
					画像を登録してください:<input type="file" name="IMAGE"
						accept="image/*" onchange="previewImage(this);">
						</td>
						<td>
						<canvas id="preview" style="max-width: 200px;"></canvas>
						</td>
						<td align="right" valign="bottom">
					<input type="submit" name="update" value="UPDATE" class="btn"><br><br> <input
						type="submit" name="delete" value="DELETE" class="btn"></td>
				</tr>



			</table>
			<br>
			<input type="text" name="item_id" class="hidden" value="${item.id}">
		</form>
	</main>

<style>
img {
	margin: 30px 0;
	width: 200px;
}
#recomend {
	display: inline-block;
}
.multiselect {
	width: 200px;
}
.selectBox {
	position: relative;
}
.selectBox select {
	width: 100%;
	font-weight: bold;
}
.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}
#checkboxes {
	display: none;
	border: 1px #dadada solid;
}
#checkboxes label {
	display: block;
}
#checkboxes label:hover {
	background-color: #1e90ff;
}
#checkboxeses {
	display: none;
	border: 1px #dadada solid;
}
#checkboxeses label {
	display: block;
}
#checkboxeses label:hover {
	background-color: #1e90ff;
}
#checkboxeseses {
	display: none;
	border: 1px #dadada solid;
}
#checkboxeseses label {
	display: block;
}
#checkboxeseses label:hover {
	background-color: #1e90ff;
}
.list {
	overflow: hidden;
	overflow-x: scroll;
	margin: 3px auto;
	width: 1000px;
	height: 500px;
	border: solid 3px #FF8C00;
}
.list2 {
	overflow-x: scroll;
	margin: 3px auto;
	width: calc(0px + 1200px);
	height: 100%;
	padding: 1em;
	padding-right: calc(2em + 100px);
}


</style>

	<script>
		var expanded = false;
		function showCheckboxes() {
			var checkboxes = document.getElementById("checkboxes");
			if (!expanded) {
				checkboxes.style.display = "block";
				expanded = true;
			} else {
				checkboxes.style.display = "none";
				expanded = false;
			}
		}
		function showCheckboxeses() {
			var checkboxes = document.getElementById("checkboxeses");
			if (!expanded) {
				checkboxes.style.display = "block";
				expanded = true;
			} else {
				checkboxes.style.display = "none";
				expanded = false;
			}
		}
		function showCheckboxeseses() {
			var checkboxes = document.getElementById("checkboxeseses");
			if (!expanded) {
				checkboxes.style.display = "block";
				expanded = true;
			} else {
				checkboxes.style.display = "none";
				expanded = false;
			}
		}
	</script>

</body>

</html>