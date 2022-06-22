package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import model.Item;
import model.WeatherForecast;

public class WeatherForecastDAO {
	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";

	/** 天気予報テーブルのデータを最新のものに更新するメソッド
	 * @param jsonData  : APIから取得した気象データのhourlyのところ
	 * @return          : 成功失敗
	 */
	public boolean setWeatherForecastDB(String[] time, String[] temperature, String[] rain, String[] windspeed,
			String[] weathercode) {
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

				String strDate = LocalDate.now().plusDays(prog).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pStmt.setString(1, LocalDate.now().plusDays(prog).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdFormat.parse(strDate);

				//デバッグ用。表示したい日を入れてください。
				//pStmt.setString(1, LocalDate.now().plusDays(0).format(DateTimeFormatter.ofPattern("2022-06-25")));
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				int cnt = 0;
				int[] weatherCodeCnt = { 0, 0, 0, 0 };
				double rainAmount = 0.0;
				double windAmount = 0.0;
				double highestTemperature = -10000.0;
				double lowestTemperature = 10000.0;
				while (rs.next()) {
					//天気は平均、多数決で決める
					cnt++;
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
				weatherList.add(
						new WeatherForecast(weatherCodeAve, Math.round(rainAmount / cnt), Math.round(windAmount / cnt),
								highestTemperature, lowestTemperature, date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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

	/** 下に表示する週間天気予報用のデータを取得。
	 * @return 戻り値はWeatherForecast型が６つ入っています。
	 * 7時から19時までの12時間の、平均気温、平均天気、1時間あたり降水量、平均風速
	 */
	public List<WeatherForecast> weeklyWeatherForecast() {
		List<WeatherForecast> weatherList = new ArrayList<WeatherForecast>();
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");
			//6日文の天気予報を取ってくる
			for (int i = 1; i <= 6; i++) {
				// SQL文を準備する
				String sql = "select * FROM WEATHER_FORECAST WHERE date = ? AND ( HOUR BETWEEN 7 AND 19 );";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる(i日後の天気を取得),
				String strDate = LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				pStmt.setString(1, strDate);
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdFormat.parse(strDate);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				int cnt = 0;
				int[] weatherCodeCnt = { 0, 0, 0, 0 };
				double rainAmount = 0.0;
				double windAmount = 0.0;
				double highestTemperature = -10000.0;
				double lowestTemperature = 10000.0;
				while (rs.next()) {
					//天気は平均、多数決で決める
					cnt++;
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
				weatherList.add(
						new WeatherForecast(weatherCodeAve, Math.round(rainAmount / cnt), Math.round(windAmount / cnt),
								highestTemperature, lowestTemperature, date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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

	/** 今日の（20時以降なら明日の）おすすめコーデのアイテムリストを返す。
	 * @return 戻り値はItem型が5つ入っています。
	 * 最初のものから順にアウター、ジャケット、トップス、スカートorパンツ、シューズ
	 * 暑くてアウター無しがおすすめ等、ないパーツはnullです。
	 */
	public List<Item> recommendCoordinate(String id) {
		List<Item> itemList = new ArrayList<Item>();
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			//もし20時を過ぎてたら明日、そうでなければ今日の日付を用意
			int prog = 0;
			if (20 <= Integer.parseInt(new SimpleDateFormat("HH").format(new Date()))) {
				prog++;
			}
			String subjectDate = LocalDate.now().plusDays(prog).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			//そのユーザーID,日付のデータがあるか確認
			String recommendSql = "SELECT * FROM RECOMMENDS WHERE date = ? AND user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(recommendSql);
			pStmt.setString(1, subjectDate);
			pStmt.setString(2, id);

			ResultSet recommendRs = pStmt.executeQuery();
			if (recommendRs.next()) { //もしおすすめコーデがすでにあったら
				String[] items = {"OUTER", "JACKET", "TOPS", "SKIRT", "PANTS", "SHOES"};
				for (int i = 0; i < 6; i++) {
					String existRecSql = "select * FROM Item WHERE id = ?";
					pStmt = conn.prepareStatement(existRecSql);
					pStmt.setString(1, recommendRs.getString(items[i]));
					ResultSet rs = pStmt.executeQuery();
					if (rs.next()) {
						itemList.add(i, new Item(
								Integer.parseInt(rs.getString("ID")),
								rs.getString("USER_ID"),
								Integer.parseInt(rs.getString("PARTS_CODE")),
								Integer.parseInt(rs.getString("PATTERN")),
								Integer.parseInt(rs.getString("RAIN")),
								Integer.parseInt(rs.getString("WIND")),
								rs.getString("PHOTO"))
								);
					} else {
						itemList.add(i,null);
					}
				}
				//パンツかスカートのどっちかはnullなので削除
				if (itemList.get(3) == null && itemList.get(4) != null) {
					itemList.remove(3);
				} else if (itemList.get(3) != null && itemList.get(4) == null) {
					itemList.remove(4);
				} else {
					itemList.remove(new Random().nextInt(2) + 3);
				}

			} else { //おすすめコーデがまだなかったら

				// 季節、7から19時の最高気温、最低気温、雨降るか、最大風速を取得
				String weatherSql = "select * FROM WEATHER_FORECAST WHERE date = ? AND ( HOUR BETWEEN 7 AND 19 );";
				PreparedStatement pStmtWeather = conn.prepareStatement(weatherSql);
				//もし20時を過ぎてたら明日、そうでなければ今日の日付にする

				pStmtWeather.setString(1, subjectDate);
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmtWeather.executeQuery();

				//検索に必要な情報の変数を用意
				int seasonCode = 0;
				double rainAmount = 0.0;
				double maxWind = 0.0;
				double highestTemperature = -10000.0;
				double lowestTemperature = 10000.0;

				//結果表から上記の変数に値を入れる
				while (rs.next()) {
					seasonCode = Integer.parseInt(rs.getString("SEASON_CODE"));
					rainAmount += Double.parseDouble(rs.getString("RAIN"));
					if (maxWind < Double.parseDouble(rs.getString("WIND"))) {
						maxWind = Double.parseDouble(rs.getString("WIND"));
					}
					if (highestTemperature < Double.parseDouble(rs.getString("TEMPERATURE"))) {
						highestTemperature = Double.parseDouble(rs.getString("TEMPERATURE"));
					}
					if (lowestTemperature > Double.parseDouble(rs.getString("TEMPERATURE"))) {
						lowestTemperature = Double.parseDouble(rs.getString("TEMPERATURE"));
					}
				}

				//一応表示
				System.out.println(
						"おすすめコーデのための変数　季節コード:" + seasonCode + "　雨量：" + rainAmount + "　最大風速：" + maxWind + " 最高気温："
								+ highestTemperature + " 最低気温：" + lowestTemperature);

				List<Item> tmpItemList = new ArrayList<Item>();

				for (int i = 0; i < 6; i++) {
					// その条件に合ったアイテムのリストを取ってくる
					System.out.println(i+ "周目");
					String itemSql = "SELECT ITEM.ID, ITEM.USER_ID, ITEM.PARTS_CODE, ITEM.PATTERN, ITEM.RAIN, ITEM.WIND, ITEM.PHOTO FROM Item"
							+ " INNER JOIN Item_season ON Item.id = Item_season.item_id"
							+ " INNER JOIN Season ON Item_season.code = Season.code"
							+ " WHERE Item.user_id = ?"
							+ " AND Item.parts_code = ?"
							+ " AND (Item_season.code = ? AND Item_season.flag = 1)"
							+ " AND (Season.LOWERLIMIT_TEMPERATURE <= ? AND ? <= Season.UPPERLIMIT_TEMPERATURE)";
					if (rainAmount > 0) { //雨降るなら雨可のみ
						itemSql += " AND Item.rain = 1";
					}
					if (maxWind > 4) { //風吹くなら風可のみ。4はけっこう行っちゃうから、平均にするか3にするかしてもいいかも
						itemSql += " AND Item.wind = 1";
					}
					itemSql += ";";
					pStmt = conn.prepareStatement(itemSql);
					pStmt.setString(1, id);
					pStmt.setString(2, String.valueOf(i));
					pStmt.setString(3, String.valueOf(seasonCode));
					if (i == 1 || i == 2) { //アウターとジャケットは最低気温基準
						pStmt.setString(4, String.valueOf((int)Math.floor(lowestTemperature)));
						pStmt.setString(5, String.valueOf((int)Math.floor(lowestTemperature)));
					} else { //それ以外のパーツは最高気温基準
						pStmt.setString(4, String.valueOf((int)Math.floor(highestTemperature)));
						pStmt.setString(5, String.valueOf((int)Math.floor(highestTemperature)));
					}
					ResultSet rsOnePartsItem = pStmt.executeQuery();
					//検索に引っかかったアイテムidをリストに
					while (rsOnePartsItem.next()) {
						tmpItemList.add(new Item(
								Integer.parseInt(rs.getString("ID")),
								rs.getString("USER_ID"),
								Integer.parseInt(rs.getString("PARTS_CODE")),
								Integer.parseInt(rs.getString("PATTERN")),
								Integer.parseInt(rs.getString("RAIN")),
								Integer.parseInt(rs.getString("WIND")),
								rs.getString("PHOTO")));
					}
					if (i == 3) { //スカートならパンツもやる
						continue;
					}
					//アイテムを格納
					if (tmpItemList.size() == 0) { //アイテムなかったら無し
						itemList.add(i, null);
					} else {
						itemList.add(i, tmpItemList.get(new Random().nextInt(tmpItemList.size())));
					}
					//tmpList削除
					tmpItemList.clear();
				}
				//スカートのnullを消す
				itemList.remove(3);
				//それをおすすめコーデテーブルに保存
				for (int i = 0; i < itemList.size(); i++) {
					String insertRecommendSql = "INSERT INTO RECOMMENDS (USER_ID, DATE, OUTER, JACKET, TOPS, SKIRT, PANTS, SHOES) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
					pStmt = conn.prepareStatement(insertRecommendSql);
					pStmt.setString(1, id);
					pStmt.setString(2, subjectDate);
					for (int j = 3; j <= 8; j++) {
						pStmt.setString(i, null);
						for (int k = 0; k < itemList.size(); k++) {
							if (itemList.get(j) == null && (itemList.get(j).getPartsCode() == (j - 2))) {
								pStmt.setString(1, String.valueOf(itemList.get(k).getId()));
								break;
							}
						}

					}

				}

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
		return itemList;
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
