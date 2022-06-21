package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.List;

import model.Diary;

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
			conn = DriverManager.getConnection(dbURL , "sa", "");

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

//			if (param.getUserId() != null) {
//				pStmt.setString(1, "%" + param.getUserId() + "%");
//			}
//			else {
//				pStmt.setString(1, "%");
//			}
//			if (param.getStartDate() != null) {
//				pStmt.setString(2, "%" + param.getStartDate() + "%");
//			}
//			else {
//				pStmt.setString(2, "%");
//			}
//			if (param.getEndDate() != null) {
//				pStmt.setString(3, "%" + param.getEndDate() + "%");
//			}
//			else {
//				pStmt.setString(3, "%");
//			}
			/*
			if (param.getStartDate() != null) {
				pStmt.setString(2,  String.valueOf(StartDate));
			}
			else {
				pStmt.setString(2, "%");
			}
			if (param.getEndDate() != null) {
				pStmt.setString(3, String.valueOf(EndDate));
			}
			else {
				pStmt.setString(3, "%");
			}
			*/
			/*
			pStmt.setString(1, id);
			pStmt.setString(2, String.valueOf(StartDate)); //pStmt.setStringはString型しか受け付けないので、int型のparts_codeをString型に変換 */

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			//DateというJavaのクラス
			while (rs.next()) {
				Diary diary= new Diary();
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
//				Diary diary = new Diary(
//				//diary.setUserId(
//						Integer.parseInt(rs.getString("id")),
//						rs.getString("startDate"),
//						rs.getString("endDate"),
//						rs.getString("userId"),
//						rs.getString("note"),
//						rs.getString("photo"),
//						Integer.parseInt(rs.getString("weatherCode")),
//						Double.parseDouble(rs.getString("maxTemperature")),
//						Double.parseDouble(rs.getString("minTemperature")),
//						Double.parseDouble(rs.getString("windSpeed")),
//						Double.parseDouble(rs.getString("amountOfRain"))
//				//★diary.setUserId(rs.getString(""));
//				//	…
//				);
//				SQLで取得したデータを全部diaryオブジェクトに格納
				diaryList.add(diary);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			diaryList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			diaryList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
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
	      conn = DriverManager.getConnection(dbURL , "sa", "");
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
	    	  diaryList.add(new Diary(0, sdf.parse(rs.getString("DATE")), null, null, null, null, rs.getString("PHOTO"), 0,0,0,0,0)
	    	  );
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      diaryList = null;
	    }
	    catch (ParseException e) {
		      e.printStackTrace();
		      diaryList = null;
		}
	    catch (ClassNotFoundException e) {
	      e.printStackTrace();
	      diaryList = null;
	    }
	    finally {
	      // データベースを切断
	      if (conn != null) {
	        try {
	          conn.close();
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	          diaryList = null;
	        }
	      }
	    }
	    // 結果を返す
	    return diaryList;
	  }

}
