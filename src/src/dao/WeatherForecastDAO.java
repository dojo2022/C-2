package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.WeatherForecast;

public class WeatherForecastDAO {
	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";
	/** 天気予報テーブルのデータを最新のものに更新するメソッド
	 * @param jsonData  : APIから取得した気象データのhourlyのところ
	 * @return          : 成功失敗
	 */
	public boolean setWeatherForecastDB(String[] time, String[] temperature, String[] rain, String[] windspeed, String[] weathercode) {
		Connection conn = null;
		boolean result = false;
		/*
		//jsonを分解
		//TODO 変数に中身入れる
		String[] time = weatherForecastData[0];
		String[] temperature;
		String[] rain;
		String[] windspeed;
		String[] weathercode;
		*/

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sqlCreate = "DROP TABLE Weather_forecast IF EXISTS;"
					+ " CREATE TABLE Weather_forecast ("
					+ " id int PRIMARY KEY AUTO_INCREMENT NOT NULL,"
					+ " date date NOT NULL,"
					+ " hour int NOT NULL,"
					+ " temperature double NOT NULL,"
					+ " weather_code int NOT NULL,"
					+ " rain double NOT NULL,"
					+ " wind double NOT NULL,"
					+ " season_code int NOT NULL,"
					+ " month int NOT NULL"
					+ ");";


			PreparedStatement pStmt = conn.prepareStatement(sqlCreate);
			//これを実行
			pStmt.execute();
			// SQL文を準備する
			for (int i = 0; i < time.length; i++) {
				/*
				String sqlInsert = "insert into Weather_forecast"
					+ " (date, hour, temperature, weather_code, rain, wind, season_code, month)"
					+ " values ('" + timeToDate(time[i]) + "', " + timeToHour(time[i]) + ", " + temperature[i]
					+ ", " + conversionWeatherCode(weathercode[i]) + ", " + rain[i]
					+ ", " + windspeed[i] + ", " + timeToSeasonCode(time[i]) + ", " + timeToMonth(time[i]) + ");";
				System.out.println(sqlInsert);
				*/
				String sqlInsert = "insert into Weather_forecast"
						+ " (date, hour, temperature, weather_code, rain, wind, season_code, month)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?);";
				pStmt = conn.prepareStatement(sqlInsert);

				/*
				System.out.println("元：" + time[i]);
				System.out.println("元：" + timeToDate(time[i]));
				 */
				pStmt.setString(1, timeToDate(time[i]));
				pStmt.setString(2, timeToHour(time[i]));
				pStmt.setString(3, temperature[i]);
				pStmt.setString(4, conversionWeatherCode(weathercode[i]));
				pStmt.setString(5, rain[i]);
				pStmt.setString(6, windspeed[i]);
				pStmt.setString(7, timeToSeasonCode(time[i]));
				pStmt.setString(8, timeToMonth(time[i]));

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				} else {
					result = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/** 右上に表示する今日または明日の天気予報を表示
	 * @return 戻り値はWeatherForecast型が３つ入っています。
	 * 一つ目は７時から、二つ目は１１時から、三つめは１５時からの４時間です。
	 */
	public List<WeatherForecast> oneDayWeatherForecast() {
		//もし1週間後までの天気予報がデータベースになかったら今日はまだ取得していないということなので、取得する
		/*
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "select * FROM WEATHER_FORECAST WHERE DATE = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


			// SQL文を実行し、結果表を取得する
			System.out.println("SQL文を実行し、結果表を取得する");
			ResultSet rs = pStmt.executeQuery();
			if (!(rs.next())) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}
		*/
		List<WeatherForecast> weatherList = new ArrayList<WeatherForecast>();
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");
			//7時からと１１時からと１５時からの３回
			for (int i = 7; i <= 15; i += 4) {
				// SQL文を準備する
				String sql = "select * FROM WEATHER_FORECAST WHERE date = ? AND HOUR BETWEEN " + i + " AND " + (i + 4);
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				int prog = 0;
				if (20 <= Integer.parseInt(new SimpleDateFormat("HH").format(new Date()))) { //もし20時を過ぎてたら明日の予報
					prog++;
				}
				pStmt.setString(1, LocalDate.now().plusDays(prog).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				int cnt = 0;
				int[] weatherCodeCnt = {0, 0, 0, 0};
				double rainAmount = 0.0;
				double windAmount = 0.0;
				double highestTemperature = -10000.0;
				double lowestTemperature = 10000.0;
				while(rs.next()) {
					//天気は平均、多数決で決める
					cnt ++;
					//天気コードの出現回数を記録
					weatherCodeCnt[Integer.parseInt(rs.getString("WEATHER_CODE")) - 1]++;
					//最低、最高気温の設定
					double t = Double.parseDouble(rs.getString("TEMPERATURE"));
					if (t > highestTemperature) {
						highestTemperature = t;
					}
					if (t < lowestTemperature) {
						lowestTemperature = t;
					}
					//降水量の加算
					rainAmount += Double.parseDouble(rs.getString("RAIN"));
					//風速の加算
					windAmount += Double.parseDouble(rs.getString("WIND"));
				}
				//その時間帯の天気は多数決で選ぶ。同率1位なら悪天候側。
				int weatherCodeAve = 0;
				int tmpMax = -1;
				for (int j = 0; j < weatherCodeCnt.length; j++) {
					if (tmpMax <= weatherCodeCnt[j]) {
						tmpMax = weatherCodeCnt[j];
						weatherCodeAve = j + 1;
					}
				}
				weatherList.add(new WeatherForecast(weatherCodeAve, rainAmount / cnt, windAmount / cnt, highestTemperature, lowestTemperature));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		return weatherList;
	}



	//time（'2022-06-14T00:00'の形）からDB用のdate型（'2022-06-14'の形）に加工
	public String timeToDate(String time) {
		char[] charArray = time.toCharArray();
		String date = "";
		for (int i = 0; i < 10; i++) {
			date += charArray[i];
		}
		return date;
	}

	//time（'2022-06-14T00:00'の形）からDB用の時間（何時かを数値型で表す）に加工
	public String timeToHour(String time) {
		char[] charArray = time.toCharArray();
		String hour = "";
		for (int i = 11; i <= 12; i++) {
			hour += charArray[i];
		}
		return hour;
	}

	//time（'2022-06-14T00:00'の形）からDB用の月（何月かを数値型で表す）に加工
	public String timeToMonth(String time) {
		char[] charArray = time.toCharArray();
		String Month = "";
		for (int i = 5; i <= 6; i++) {
			Month += charArray[i];
		}
		return Month;
	}

	//time（'2022-06-14T00:00'の形）からDB用の季節コード（春１夏２秋３冬４）に加工
	public String timeToSeasonCode(String time) {
		String month = timeToMonth(time);
		//System.out.println("timeToSeasonCode " + month);
		String ans;
		switch (month) {
			case "03":
			case "04":
			case "05":
				ans = "1";
				break;
			case "06":
			case "07":
			case "08":
				ans = "2";
				break;
			case "09":
			case "10":
			case "11":
				ans = "3";
				break;
			case "12":
			case "1":
			case "2":
				ans = "4";
				break;
			default:
				ans = "";
		}
		return ans;
	}
	//APIから取得したウェザーコードを、我々の天気コードに変換
	public String conversionWeatherCode(String originaWeatherCode) {
		String ans;
		switch (originaWeatherCode) {
			case "0":
			case "1":
				ans = "1";
				break;
			case "2":
			case "3":
				ans = "2";
				break;
			case "45":
			case "48":
			case "51":
			case "53":
			case "55":
			case "56":
			case "57":
			case "61":
			case "63":
			case "65":
			case "66":
			case "67":
			case "80":
			case "81":
			case "82":
			case "95":
			case "96":
			case "99":
				ans = "3";
				break;
			case "71":
			case "73":
			case "75":
			case "77":
			case "85":
			case "86":
				ans = "4";
				break;
			default:
				ans = "3"; //その他は全部とりあえず雨ということにします
		}
		return ans;
	}
}