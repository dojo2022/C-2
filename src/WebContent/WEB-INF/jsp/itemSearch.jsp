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
            <li> <a href="/coordinator/ItemSearchServlet">SEARCH</a></li>
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
                                <label>
                                    <input type="checkbox" name="spring">春</label>
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
                    <div class="multiselect">
                        <div class="selectBox" onclick="showCheckboxeseses()">
                            <select>
                            	<option>パーツ</option>
                            </select>
                            <div class="overSelect"></div>
                        </div>
                        <div id="checkboxeseses">
                            <label>
                                <input type="checkbox" name="outer">アウター</label>
                            <label>
                                <input type="checkbox" name="jacket">ジャケット</label>
                            <label>
                                <input type="checkbox" name="tops">トップス</label>
                            <label>
                                <input type="checkbox" name="skirt">スカート</label>
                            <label>
                                <input type="checkbox" name="pants">パンツ</label>
                            <label>
                                <input type="checkbox" name="shoes">シューズ</label>
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
                    <tr>
                    <td>
                        <label>柄
                            <input type="checkbox" name="pattern1">あり
                        </label>
                        <label>
                            <input type="checkbox" name="pattern0">なし
                        </label>
                    </td>
              <!--   </tr>
                <tr>-->
                    <td>
                        <label>雨
                            <input type="checkbox" name="rain1">可
                        </label>
                        <label>
                            <input type="checkbox" name="rain0">不可
                        </label>
                    </td>
                    <td>
                        <label>風
                            <input type="checkbox" name="wind1">可
                        </label>
                        <label>
                            <input type="checkbox" name="wind0">不可
                        </label>
                    </td>
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
            </table><br>
        </form>
    </main>
<hr>

    <h2>RECOMMENDS COORDINATE</h2>
    <table align="center" id="recomend">
        <tr>
            <td><img src="photo/17.PNG" alt="アウター_1"></td>
			<td><img src="photo/17.PNG" alt="アウター_2"></td>
            <td><img src="photo/17.PNG" alt="アウター_3"></td>
        </tr>
        <tr>
            <td><img src="photo/3.PNG" alt="ジャケット_1"></td>
			<td><img src="photo/3.PNG" alt="ジャケット_2"></td>
            <td><img src="photo/3.PNG" alt="ジャケット_3"></td>
        </tr>
        <tr>
            <td><img src="photo/18.PNG" alt="トップス_1"></td>
			<td><img src="photo/18.PNG" alt="トップス_2"></td>
            <td><img src="photo/18.PNG" alt="トップス_3"></td>
        </tr>
        <tr>
            <td><img src="photo/26.PNG" alt="スカート_1"></td>
			<td><img src="photo/26.PNG" alt="スカート_2"></td>
            <td><img src="photo/26.PNG" alt="スカート_3"></td>
        </tr>
        <tr>
            <td><img src="photo/55.PNG" alt="シューズ_1"></td>
			<td><img src="photo/55.PNG" alt="シューズ_2"></td>
            <td><img src="photo/55.PNG" alt="シューズ_3"></td>
        </tr>
    </table>


    <h3>OUTER</h3>
    <div class="list" class="list_outer">
        <div class="list2">



    <table id="item">
<tr>
    <td class="result_outer">
    <img src="photo/17.PNG" alt="アウター">
    <img src="photo/23.PNG" alt="アウター">
    <img src="photo/38.PNG" alt="アウター">

    </td>
    </tr>
    </table>


</div>
</div>



    <h3>JACKET</h3>
    <div class="list" class="list_jk">
        <div class="list2">

<table id="item">
    <tr>
        <td class="result_jacket">

    <img src="photo/1.PNG" alt="ジャケット">
    <img src="photo/2.PNG" alt="ジャケット">
    <img src="photo/3.PNG" alt="ジャケット">
    </td>
    </tr>
    </table>

</div>
</div>



    <h3>TOPS</h3>
    <div class="list" class="list_tops">
        <div class="list2">

<tabale id="item">
    <tr>
        <td class="result_tops">
    <img src="photo/20.PNG" alt="トップス">
    <img src="photo/21.PNG" alt="トップス">
    <img src="photo/22.PNG" alt="トップス">
    </td>
    </tr>
    </tabale>

</div>
</div>




    <h3>SKIRT</h3>
    <div class="list" class="list_sk">
        <div class="list2">

    <table id="item">
        <tr>
            <td class="result_skirt">
    <img src="photo/5.PNG" alt="スカート">
    <img src="photo/6.PNG" alt="スカート">
    <img src="photo/7.PNG" alt="スカート">
    </td>
    </tr>
    </table>

</div>
</div>



    <h3>PANTS</h3>
    <div class="list" class="list_p">
        <div class="list2">

    <table id="item">
        <tr>
            <td class="result_pants">
    <img src="photo/42.PNG" alt="パンツ">
    <img src="photo/52.PNG" alt="パンツ">
    <img src="photo/53.PNG" alt="パンツ">
    </td>
    </tr>
    </table>

</div>
</div>


    <h3>SHOES</h3>
    <div class="list" class="list_shoes">
        <div class="list2">

    <table id="item">
        <tr>
            <td class="result_shoes">
    <img src="photo/13.PNG" alt="アウター">
    <img src="photo/14.PNG" alt="アウター">
    <img src="photo/15.PNG" alt="アウター">
    </td>
    </tr>
    </table>

</div>
</div>
<hr>

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