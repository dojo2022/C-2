package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Diary;

public class DiaryDAO {
	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";

	//日記を取ってくるためのメソッド

	//★selectDiaryのメソッド、引数↓にデータベースの検索条件「？」にセットする値の変数を定義しましょう。
	public List<Diary> selectDiary() {
		Connection conn = null;
		List<Diary> diaryList = new ArrayList<Diary>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL , "sa", "");

			// SQL文を準備する
			String sql = "SELECT *"
						+ "FROM Diary"
						+ "WHERE Diary.user_id = '?' AND Diary.date"
						+ "BETWEEN '?' AND '?'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			//★ここでセットする値は、基本的にメソッドの引数で指定された値を設定しましょう。
			//	そうすることで、DiaryDaoのselectDiaryメソッドを呼び出す際に、検索条件の値が変更出来るようになります。

			//	1:表示したいユーザーIDを設定
			//	2:画面に表示したい日記日付の開始日
			//	3:画面に表示したい日記日付の終了日
			pStmt.setString(1, id);
			pStmt.setString(2, String.valueOf(parts_code)); //pStmt.setStringはString型しか受け付けないので、int型のparts_codeをString型に変換 */


			// SQL文を実行し、結果表を取得する
			System.out.println("SQL文を実行し、結果表を取得する");
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Diary diary = new Diary();
				//★diary.setUserId(rs.getString(""));
				//	…
				//	SQLで取得したデータを全部diaryオブジェクトに格納

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

}
