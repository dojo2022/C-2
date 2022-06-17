/** いずれ全体のjsに統一します。山田
 *
 */
'use strict';
//console.log('start');
setWeatherForecastDB();
function setWeatherForecastDB(){
	//console.log('setWeatherForecastDB()');
        /*
        URLの設定
        場所とタイムゾーン　東京
        期間　2日前から1週間先までの9日間
        頻度　1時間おき
        項目　気温(℃)、降水量(その1時間の)(mm)、風速(m/s)、ウェザーコード(整数)
        */
        const url = 'https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&hourly=temperature_2m,rain,weathercode,windspeed_10m&windspeed_unit=ms&timezone=Asia%2FTokyo&past_days=2';
        fetch(url)
          .then(data => data.json())
          .then(json => goAjax(json))
}
function goAjax(json){
	//console.log('goAjax()');
	let postData = conversionJson(json);
	//console.log(postData);
  //非同期通信始める
  $.ajaxSetup({scriptCharset:'utf-8'});
  $.ajax({
    //どのサーブレットに送るか
    //ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
    url: '/coordinator/AjaxSampleServlet',
    //どのメソッドを使用するか
    type:"POST",
    //受け取るデータのタイプ
    dataType:"json",
    //何をサーブレットに飛ばすか（変数を記述）
    data: postData,
    //この下の２行はとりあえず書いてる（書かなくても大丈夫？）
    processDate:false,
    timeStamp: new Date().getTime()
    //非同期通信が成功したときの処理
  }).done(function(data) {
    alert("成功1");
    })
    //非同期通信が失敗したときの処理
    .fail(function() {
    alert("失敗！");
    });
}
//APIから取ったjsonをサーブレットに渡す形に変更
function conversionJson(originalJson) {
	let str = '{"itemKinds":"' + 5 + '",'
			  + '"itemQuantity":"' + originalJson.hourly.time.length + '",';
	for (let i = 0; i < originalJson.hourly.time.length; i++) {
		str += '"time' + i + '":"' + originalJson.hourly.time[i] + '",'
	}
	for (let i = 0; i < originalJson.hourly.temperature_2m.length; i++) {
		str += '"temperature' + i + '":"' + originalJson.hourly.temperature_2m[i] + '",'
	}
	for (let i = 0; i < originalJson.hourly.rain.length; i++) {
		str += '"rain' + i + '":"' + originalJson.hourly.rain[i] + '",'
	}
	for (let i = 0; i < originalJson.hourly.windspeed_10m.length; i++) {
		str += '"windspeed' + i + '":"' + originalJson.hourly.windspeed_10m[i] + '",'
	}
	for (let i = 0; i < originalJson.hourly.weathercode.length; i++) {
		str += '"weathercode' + i + '":"' + originalJson.hourly.weathercode[i] + '",'
	}
	str += '"fin":"fin"}';
	//console.log(str);
	//オブジェクトに変換して返す
	let obj = JSON.parse(str);
	return obj;
}