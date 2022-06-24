package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.sql.Date;
import java.util.List;

import model.Diary;
import model.WeatherForecast;

public class DiaryDAO {
	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";

	//日記を取ってくるためのメソッド

	//★selectDiaryのメソッド、引数↓にデータベースの検索条件「？」にセットする値の変数を定義しましょう。
	public List<Diary> selectDiary(Diary param) {
		Connection conn = null;
		List<Diary> diaryList = new ArrayList<Diary>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "SELECT *"
					+ " FROM Diary"
					+ " WHERE Diary.user_id = ? AND"
					+ " Diary.date BETWEEN ? AND ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			//★ここでセットする値は、基本的にメソッドの引数で指定された値を設定しましょう。
			//	そうすることで、DiaryDaoのselectDiaryメソッドを呼び出す際に、検索条件の値が変更出来るようになります。

			//	1:表示したいユーザーIDを設定
			//	2:画面に表示したい日記日付の開始日
			//	3:画面に表示したい日記日付の終了日
			pStmt.setString(1, param.getUserId());
			pStmt.setString(2, param.getStartDate());
			pStmt.setString(3, param.getEndDate());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			//DateというJavaのクラス
			while (rs.next()) {
				Diary diary = new Diary();
				//セッターメソッドを使って、12このフィールドに全部値をセットする。
				diary.setId(Integer.parseInt(rs.getString("id")));
				diary.setDate(rs.getDate("date"));
				//rs.getDate("date"); //java.sql.Date型の日付データがかえってくる。→java.util.Date型に変換する
				//				diary.setStartDate(rs.getString("startDate"));
				//				diary.setEndDate(rs.getString("endDate"));
				String ret = rs.getString("user_Id");
				diary.setUserId(ret);
				diary.setNote(rs.getString("note"));
				diary.setPhoto(rs.getString("photo"));
				diary.setWeatherCode(Integer.parseInt(rs.getString("weather_Code")));
				diary.setMaxTemperature(Double.parseDouble(rs.getString("max_Temperature")));
				diary.setMinTemperature(Double.parseDouble(rs.getString("min_Temperature")));
				diary.setWindSpeed(Double.parseDouble(rs.getString("wind_Speed")));
				diary.setAmountOfRain(Double.parseDouble(rs.getString("amount_Of_Rain")));
				//日付（文字列型）フィールドも
				//日付(String型)を用意する(Integer.parseInt(new SimpleDateFormat("MM").format(date))) + "/" + (Integer.parseInt(new SimpleDateFormat("dd").format(date)));
				String dateStr = (Integer.parseInt(new SimpleDateFormat("yyyy").format(rs.getDate("date")))) + "/"
						+ (Integer.parseInt(new SimpleDateFormat("MM").format(rs.getDate("date")))) + "/"
						+ (Integer.parseInt(new SimpleDateFormat("dd").format(rs.getDate("date"))));
				System.out.println("DiaryDao:" + dateStr);
				diary.setDateStr(dateStr);

				//SQLで取得したデータを全部diaryオブジェクトに格納
				diaryList.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			diaryList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			diaryList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					diaryList = null;
				}
			}
		}

		// 結果を返す
		return diaryList;
	}

	public List<Diary> search3Photos(String id) {
		Connection conn = null;
		List<Diary> diaryList = new ArrayList<Diary>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");
			// SQL文を準備する
			String sql = "SELECT date, photo FROM Diary WHERE user_id = ? AND photo != '¥photo¥no_image.png' ORDER BY date DESC LIMIT 3";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			pStmt.setString(1, id);
			// SQL文を実行し、結果表を取得する
			//System.out.println("SQL文を実行し、結果表を取得する");
			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				diaryList.add(new Diary(0, sdf.parse(rs.getString("DATE")), null, null, null, null,
						rs.getString("PHOTO"), 0, 0, 0, 0, 0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			diaryList = null;
		} catch (ParseException e) {
			e.printStackTrace();
			diaryList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			diaryList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					diaryList = null;
				}
			}
		}
		// 結果を返す
		return diaryList;
	}

	public Diary search(String id) {
		Connection conn = null;
		Diary diary = new Diary();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = " SELECT * "
					+ " FROM Diary "
					+ " WHERE Diary.id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				diary = new Diary(
						Integer.parseInt(rs.getString("id")),
						rs.getDate("date"),
						null,
						null,
						rs.getString("user_id"),
						rs.getString("note"),
						rs.getString("photo"),
						-10000,
						-10000,
						-10000,
						-10000,
						-10000);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			diary = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			diary = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					diary = null;
				}
			}
		}

		// 結果を返す
		return diary;
	}

	public boolean update(Diary edit) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");
			// SQL文を準備する　ここも改造
			if (edit.getPhoto() != null) {
				String sql = "UPDATE Diary SET photo= ?, note= ? where Id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる 改造

				if (edit.getPhoto() != null && !edit.getPhoto().equals("")) {
					pStmt.setString(1, edit.getPhoto());
				} else {
					pStmt.setString(1, "");
				}

				if (edit.getNote() != null && !edit.getNote().equals("")) {
					//System.out.println("3"+ edit.getNote());
					pStmt.setString(2, edit.getNote());
				} else {
					pStmt.setString(2, null);
				}
				if (edit.getId() >= 1) {
					pStmt.setString(3, String.valueOf(edit.getId()));
				} else {
					pStmt.setString(3, "");
				}

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} else {
				String sql = "UPDATE Diary SET note= ? where Id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				if (edit.getNote() != null && !edit.getNote().equals("")) {
					//System.out.println("3"+ edit.getNote());
					pStmt.setString(1, edit.getNote());
				} else {
					pStmt.setString(1, null);
				}
				if (edit.getId() >= 1) {
					pStmt.setString(2, String.valueOf(edit.getId()));
				} else {
					pStmt.setString(2, "");
				}

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
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
		return result;
	}

	public boolean insertTodayDiary(WeatherForecast weather, String userId) {
		boolean result = false;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");
			// SQL文を準備する
			String sql = "SELECT * FROM Diary WHERE date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cl = Calendar.getInstance();
			String date = sdf.format(cl.getTime());
			System.out.println("DiaryDao,insertTodayDiary(),今日の日付：" + date);
			pStmt.setString(1, date);

			// SQL文を実行する
			ResultSet rs = pStmt.executeQuery();

			if (!(rs.next())) {
				String sql1 = "INSERT INTO Diary (date, user_id, note, photo, weather_code, max_temperature, min_temperature, wind_speed, amount_of_rain ) Values (?,?,'','\\photo\\no_image.png',?,?,?,?,?);";
				PreparedStatement pStmt1 = conn.prepareStatement(sql1);

				// SQL文を完成させる

				pStmt1.setString(1, date);
				pStmt1.setString(2, userId);
				pStmt1.setString(3, String.valueOf(weather.getWeatherCode()));
				pStmt1.setString(4, String.valueOf(weather.getHighestTemperature()));
				pStmt1.setString(5, String.valueOf(weather.getLowestTemperature()));
				pStmt1.setString(6, String.valueOf(weather.getWind()));
				pStmt1.setString(7, String.valueOf(weather.getRain()));

				// SQL文を実行する
				if (pStmt1.executeUpdate() == 1) {
					result = true;
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

		return result;
	}
}
