<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>itemSearch</title>
    <link rel="stylesheet" href="/coordinator/css/itemSearch.css">
    <link rel="stylesheet" href="/coordinator/css/common.css">
    <script src="js/itemSearch.js"></script>
    <script src="js/common.js"></script>
</head>

<body>
    <h1> 検索</h1>
    <nav class="nav">
        <ul>
            <li><a href="/coordinator/HomeServlet">HOME</a></li>
            <li><a href="/coordinator/DiaryServlet">DIARY</a></li>
            <li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
            <li><a href="/coordinator/ItemRegistUpdateServlet">REGIST</a></li>
            <li><a href="/coordinator/LoginServlet">LOGOUT</a></li>
        </ul>
    </nav>
    <main>
    <c:forEach var="item" items="${itemList}" >
        <form method="POST" action="/coordinator/ItemSearchServlet">
            <table>
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
                                <label>
                                    <input type="checkbox" name="spring" value="${item.spring}">春</label>
                                <label>
                                    <input type="checkbox" name="summer">夏</label>
                                <label>
                                    <input type="checkbox" name="autumn">秋</label>
                                <label>
                                    <input type="checkbox" name="winter">冬</label>
                            </div>
                        </div>
                    </td>
                    <td>
				          <label>パーツ<br>
				          <select>
				            <option value="outer">アウター</option>
				            <option value="jacket">ジャケット</option>
				            <option value="tops">トップス</option>
				            <option value="skirt">スカート</option>
				            <option value="pants">パンツ</option>
				            <option value="shoes">シューズ</option>
				          </select>
				          </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="multiselect">
                            <div class="selectBox" onclick="showCheckboxeses()">
                                <select>
                                    <option>色</option>
                                </select>
                                <div class="overSelect"></div>
                            </div>
                            <div id="checkboxeses">
                                <label>
                                    <input type="checkbox" name="white">白</label>
                                <label>
                                    <input type="checkbox" name="black">黒</label>
                                <label>
                                    <input type="checkbox" name="grey">グレー</label>
                                <label>
                                    <input type="checkbox" name="beige">ベージュ</label>
                                <label>
                                    <input type="checkbox" name="red">赤系</label>
                                <label>
                                    <input type="checkbox" name="blue">青系</label>
                                <label>
                                    <input type="checkbox" name="green">緑系</label>
                                <label>
                                    <input type="checkbox" name="yellow">黄系</label>
                                <label>
                                    <input type="checkbox" name="other">その他</label>
                            </div>
                        </div>
                    </td>
                    <td>
                        <label>柄<br>
                            <input type="radio" name="pattern_yes">あり
                        </label>
                        <label>
                            <input type="radio" name="pattern_no">なし
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>雨<br>
                            <input type="radio" name="rain_ok">可
                        </label>
                        <label>
                            <input type="radio" name="rain_ng">不可
                        </label>
                    </td>
                    <td>
                        <label>風<br>
                            <input type="radio" name="wind_ok">可
                        </label>
                        <label>
                            <input type="radio" name="wind_ng">不可
                        </label>
                    </td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" name="update" value="更新">
                        <input type="submit" name="delete" value="削除">
                    </td>
                </tr>
                <tr>
					<td><br> 画像:<input type="file" name="IMAGE"
						accept="image/*" onchange="previewImage(this);"><br>
						<canvas id="preview" style="max-width: 200px;"></canvas>
					</td>
				</tr>

            </table><br>
        </form>
    </c:forEach>
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

    </script>

</body>

</html>