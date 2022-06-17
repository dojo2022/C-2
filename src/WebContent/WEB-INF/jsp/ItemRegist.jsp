<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ItemRegist</title>
<link rel="stylesheet" href="/coordinator/css/itemRegist.css">
<link rel="stylesheet" href="/coordinator/css/common.css">
<script src="js/itemRegist.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	アイテム登録ページ
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
  <h2>登録</h2>
	<form method="POST" action="/coordinator/ItemRegistServlet">
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
              <label for="one">
                <input type="checkbox" id="one" />春</label>
              <label for="two">
                <input type="checkbox" id="two" />夏</label>
              <label for="three">
                <input type="checkbox" id="three" />秋</label>
              <label for="four">
                <input type="checkbox" id="four" />冬</label>
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
              <label for="five">
                <input type="checkbox" id="five" />白</label>
              <label for="six">
                <input type="checkbox" id="six" />黒</label>
              <label for="seven">
                <input type="checkbox" id="seven" />グレー</label>
              <label for="eight">
                <input type="checkbox" id="eight" />ベージュ</label>
              <label for="nine">
                <input type="checkbox" id="nine" />赤系</label>
              <label for="ten">
                <input type="checkbox" id="ten" />青系</label>
              <label for="eleven">
                <input type="checkbox" id="eleven" />緑系</label>
              <label for="twelve">
                <input type="checkbox" id="twelve" />黄系</label>
              <label for="thirteen">
                <input type="checkbox" id="thirteen" />その他</label>
            </div>
          </div>
        </td>
        <td>
          <label>柄<br>
          <input type="radio" name="post_name">あり
          </label>
          <label>
          <input type="radio" name="post_name">なし
          </label>
        </td>
      </tr>
      <tr>
        <td>
          <label>雨<br>
          <input type="radio" name="full_name">可
          </label>
          <label>
          <input type="radio" name="full_name">不可
          </label>
        </td>
        <td>
          <label>風<br>
          <input type="radio" name="tel">可
          </label>
          <label>
          <input type="radio" name="tel">不可
          </label>
        </td>
      </tr>
      <tr>
        <td><br>
          画像:<input type="file" name="IMAGE" accept="image/*" onchange="previewImage(this);"><br>
          <canvas id="preview" style="max-width:200px;"></canvas>
        </td>
      </tr>
			<tr>
				<td>
          <input type="submit" name="REGIST" value="登録">
          <input type="reset" name="reset" value="リセット">
        </td>
			</tr>
		</table><br>

	</form>
</main>
<style>
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

</script>
</body>
</html>