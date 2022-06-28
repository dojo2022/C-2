'use strict';
	//window.alert('更新成功');

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
/* inputタグの使い方を間違えて、個々に違うname属性をつけて進めてしまいました。
今更Daoを変えられないので、javascriptで一つのみ選択にします。6/28 */
function f1() {
	let a = document.getElementById('pattern_yes');
	let b = document.getElementById('pattern_no');
	if (a.checked) {
		b.checked = false;
	}
}
function f2() {
	let a = document.getElementById('pattern_yes');
	let b = document.getElementById('pattern_no');
	if (b.checked) {
		a.checked = false;
	}
}
function f3() {
	let a = document.getElementById('rain_ok');
	let b = document.getElementById('rain_ng');
	if (a.checked) {
		b.checked = false;
	}
}
function f4() {
	let a = document.getElementById('rain_ok');
	let b = document.getElementById('rain_ng');
	if (b.checked) {
		a.checked = false;
	}
}
function f5() {
	let a = document.getElementById('wind_ok');
	let b = document.getElementById('wind_ng');
	if (a.checked) {
		b.checked = false;
	}
}
function f6() {
	let a = document.getElementById('wind_ok');
	let b = document.getElementById('wind_ng');
	if (b.checked) {
		a.checked = false;
	}
}