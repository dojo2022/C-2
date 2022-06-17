package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {
	/* データベースのURLは後で共有フォルダのやつに書き換えます。
	全部書き換えるのが大変なのでフィールドに書きます。 */
	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";

	//servlet上にこれをかいてあげる
	//search("higashi", 1);	//アウター情報をってきなさい
	//search("higashi", 2);	//ジャケット情報をとってきなさい

	/* 6/15山田
	searchPartsメソッドは、ログイン中のユーザーのidとパーツコード
	(1:アウター, 2:ジャケット, 3:トップス, 4:スカート, 5:パンツ, 6:シューズ)
	を引数に、その人のそのパーツ全部のリストを出すメソッドです。
	アイテム検索ページの一覧検索ボタンを押した時などに使います。
	アイテムが一つもない場合は空のList<Item>が戻ります。
	*/

	/**
	 *
	 * @param id			：ユーザid
	 * @param parts_code	：パーツコード
	 * @return				：指定したいユーザー、パーツコードのアイテム一覧
	 */
	public List<Item> searchParts(String id, int parts_code){
		Connection conn = null;
		//戻り値にするアイテムのリストを用意
		List<Item> itemList = new ArrayList<Item>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "SELECT * "
						+ "FROM Item INNER JOIN Parts ON Item.parts_code = Parts.code "
						+ "WHERE Item.user_id = ? AND Item.parts_code = ?";
			/* ↑後で足りなくて困ることのないよう、不要なカラムまで全部取っています。
			 * デバッグ時にアイテム名を出したいときのために一応内部結合しています。*/
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, id);
			pStmt.setString(2, String.valueOf(parts_code)); //pStmt.setStringはString型しか受け付けないので、int型のparts_codeをString型に変換 */

			// SQL文を実行し、結果表を取得する
			System.out.println("SQL文を実行し、結果表を取得する");
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Item item = new Item(
					Integer.parseInt(rs.getString("ID")),
					rs.getString("USER_ID"),
					Integer.parseInt(rs.getString("PARTS_CODE")),
					Integer.parseInt(rs.getString("PATTERN")),
					Integer.parseInt(rs.getString("RAIN")),
					Integer.parseInt(rs.getString("WIND")),
					rs.getString("PHOTO"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			itemList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			itemList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					itemList = null;
				}
			}
		}

		// 結果を返す
		return itemList;
	}

// 引数itemで指定されたレコードを登録し、成功したらtrueを返す
public boolean insert(Item item) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection(dbURL, "sa", "");

		// item体ブルにインサート
		// SQL文を準備する
		String sql = "insert into Item (id, user_id, parts_code, pattern, rain, wind, photo) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(1, String.valueOf(item.getId));

		pStmt.setString(2, item.getUserId());

		pStmt.setString(3, item.getPartsCode());

		pStmt.setString(4, item.getPattern());

		pStmt.setString(5, item.getRain());

		pStmt.setString(6, item.getWind());

		pStmt.setString(7, item.getPhoto());

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// item体ブルにインサートここまで

	//アイテム季節設定マスターにインサート
	// insert into Item_season (item_id, code, flag) values (?, 1, 1);"
	// insert into Item_season (item_id, code, flag) values (?, 2, 1);"
	// insert into Item_season (item_id, code, flag) values (?, 3, 0);"
	// insert into Item_season (item_id, code, flag) values (?, 4, 0);"

	//アイテム色設定マスターにインサート
	// insert into Item_color (item_id, code, flag) values (?, 1, 1);"
	// insert into Item_color (item_id, code, flag) values (?, 2, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 3, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 4, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 5, 1);"
	// insert into Item_color (item_id, code, flag) values (?, 6, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 7, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 8, 0);"
	// insert into Item_color (item_id, code, flag) values (?, 9, 0);"



	// 結果を返す
	return result;
}
}
