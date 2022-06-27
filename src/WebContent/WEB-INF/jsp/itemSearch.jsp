<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>itemSearch</title>
<link rel="stylesheet" href="/coordinator/css/itemSearch.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<link rel="stylesheet" href="/coordinator/css/headerFooter.css">
<script src="js/itemSearch.js"></script>
<script src="js/common.js"></script>
</head>

<body>
	<h1 align="center">
		<img src="/coordinator/photo/logo.PNG" alt="coodinater">
	</h1>

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
		<form method="POST" action="/coordinator/ItemSearchServlet">
			<table align="center" class="search">
				<tr>
					<td>
						<div class="multiselect">
							<div class="selectBox" onclick="showCheckboxes()">
								<select>
									<option>季節</option>
								</select>
								<div class="overSelect"></div>
							</div>
							<div id="checkboxes">
								<label> <input type="checkbox" name="spring">春
								</label> <label> <input type="checkbox" name="summer">夏
								</label> <label> <input type="checkbox" name="autumn">秋
								</label> <label> <input type="checkbox" name="winter">冬
								</label>
							</div>
						</div>
					</td>
					<td>
						<div class="multiselect">
							<div class="selectBox" onclick="showCheckboxeseses()">
								<select>
									<option>パーツ</option>
								</select>
								<div class="overSelect"></div>
							</div>
							<div id="checkboxeseses">
								<label> <input type="checkbox" name="outer">アウター
								</label> <label> <input type="checkbox" name="jacket">ジャケット
								</label> <label> <input type="checkbox" name="tops">トップス
								</label> <label> <input type="checkbox" name="skirt">スカート
								</label> <label> <input type="checkbox" name="pants">パンツ
								</label> <label> <input type="checkbox" name="shoes">シューズ
								</label>
							</div>
						</div>
					</td>
					<!--    </tr>
                <tr>-->
					<td>
						<div class="multiselect">
							<div class="selectBox" onclick="showCheckboxeses()">
								<select>
									<option>色</option>
								</select>
								<div class="overSelect"></div>
							</div>
							<div id="checkboxeses">
								<label> <input type="checkbox" name="white">白
								</label> <label> <input type="checkbox" name="black">黒
								</label> <label> <input type="checkbox" name="grey">グレー
								</label> <label> <input type="checkbox" name="beige">ベージュ
								</label> <label> <input type="checkbox" name="red">赤系
								</label> <label> <input type="checkbox" name="blue">青系
								</label> <label> <input type="checkbox" name="green">緑系
								</label> <label> <input type="checkbox" name="yellow">黄系
								</label> <label> <input type="checkbox" name="other">その他
								</label>
							</div>
						</div>
					</td>
				<tr>
					<td><label>柄 <input type="checkbox" name="pattern1">あり
					</label> <label> <input type="checkbox" name="pattern0">なし
					</label></td>
					<!--   </tr>
                <tr>-->
					<td><label>雨 <input type="checkbox" name="rain1">可
					</label> <label> <input type="checkbox" name="rain0">不可
					</label></td>
					<td><label>風 <input type="checkbox" name="wind1">可
					</label> <label> <input type="checkbox" name="wind0">不可
					</label></td>
				</tr>

				<tr>
					<td colspan="3" class="button_table">
						<p align="center">
							<input type="submit" name="list" value="ALL ITEM" class="btn">
							<input type="submit" name="search" value="SEARCH" class="btn">
							<input type="reset" name="reset" value="RESET" class="btn">
						</p>
					</td>
				</tr>
			</table>
			<br>
		</form>
	</main>
	<hr>


	<c:set value="0" var="itemAmount"></c:set>
	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${outerList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>OUTER</h3>
		<div class="list" class="list_outer">
			<div class="list2">



				<table id="item">
					<tr>
						<td class="result_outer"><c:forEach var="item"
								items="${outerList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="アウター"></a>
							</c:forEach></td>
					</tr>
				</table>


			</div>
		</div>
	</c:if>


	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${jkList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>JACKET</h3>
		<div class="list" class="list_jk">
			<div class="list2">

				<table id="item">
					<tr>
						<td class="result_jacket"><c:forEach var="item"
								items="${jkList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="ジャケット"></a>
							</c:forEach></td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>


	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${topsList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>TOPS</h3>
		<div class="list" class="list_tops">
			<div class="list2">

				<table id="item">
					<tr>
						<td class="result_tops"><c:forEach var="item"
								items="${topsList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="トップス"></a>
							</c:forEach></td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>


	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${skirtList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>SKIRT</h3>
		<div class="list" class="list_sk">
			<div class="list2">

				<table id="item">
					<tr>
						<td class="result_skirt"><c:forEach var="item"
								items="${skirtList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="スカート"></a>
							</c:forEach></td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>


	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${pantsList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>PANTS</h3>
		<div class="list" class="list_p">
			<div class="list2">

				<table id="item">
					<tr>
						<td class="result_pants"><c:forEach var="item"
								items="${pantsList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="パンツ"></a>
							</c:forEach></td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>

	<c:set value="0" var="count"></c:set>
	<c:forEach var="item" items="${shoesList}">
		<c:set value="${count + 1}" var="count"></c:set>
		<c:set value="${itemAmount + 1}" var="itemAmount"></c:set>
	</c:forEach>
	<c:if test="${count > 0}">
		<h3>SHOES</h3>
		<div class="list" class="list_shoes">
			<div class="list2">

				<table id="item">
					<tr>
						<td class="result_shoes"><c:forEach var="item"
								items="${shoesList}">
								<a href="javascript:itemEdit(${item.id});"><img src="/coordinator/${item.photo}" alt="シューズ"></a>
							</c:forEach></td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>
	<c:if test="${itemAmount == 0 && search}">
	<p>検索条件に当てはまるアイテムが見つかりませんでした</p>
	</c:if>


	<!--      <style>
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
        .list{
	overflow: hidden;
    overflow-x: scroll;
	margin: 3px auto;
	width: 1000px;
	height: 500px;
    border: solid 3px #FF8C00;
}
.list2{
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
    </script>-->

</body>

</html>