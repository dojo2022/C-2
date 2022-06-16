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
}
