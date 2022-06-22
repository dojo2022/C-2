package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.RegistInf;

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
	public List<Item> searchParts(String id, int parts_code) {
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
	public boolean insert(RegistInf registinf, String id, String photo) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// itemテーブルにインサート
			// SQL文を準備する

			String sql = "INSERT INTO Item (USER_ID, PARTS_CODE, PATTERN, RAIN, WIND, PHOTO) VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, id);

			switch (registinf.getParts()) {
			case "outer":
				pStmt.setString(2, "1");
				break;
			case "jacket":
				pStmt.setString(2, "2");
				break;
			case "tops":
				pStmt.setString(2, "3");
				break;
			case "skirt":
				pStmt.setString(2, "4");
				break;
			case "pants":
				pStmt.setString(2, "5");
				break;
			case "shoes":
				pStmt.setString(2, "6");
				break;
			default:
				break;
			}

			if (registinf.getPatternYES() != null && registinf.getPatternNO() == null) {
				pStmt.setString(3, "1");
			} else if (registinf.getPatternYES() == null && registinf.getPatternNO() != null) {
				pStmt.setString(3, "0");
			} else {
				pStmt.setString(3, "0");
			}


			if (registinf.getRainOK() != null  && registinf.getRainNG() == null) {
				pStmt.setString(4, "1");
			} else if (registinf.getRainOK() == null && registinf.getRainNG() != null ) {
				pStmt.setString(4, "0");
			} else {
				pStmt.setString(4, "1");
			}

			if (registinf.getWindOK() != null  && registinf.getWindNG() == null) {
				pStmt.setString(5, "1");
			} else if (registinf.getWindOK() == null && registinf.getWindNG() != null ) {
				pStmt.setString(5, "0");
			} else {
				pStmt.setString(5, "1");
			}

			//写真の名前

			pStmt.setString(6, photo);


			// SQL文を実行する
			int cnt = pStmt.executeUpdate();
			if (cnt == 1) {
				System.out.println("item:" + cnt);
				result = true;
			}
			//今増やしたレコードのidを取得

			String ItemID = "";
			/*
			ResultSet rs = pStmt.getGeneratedKeys();
			 if (rs.next()) {
				 ItemID = String.valueOf(rs.getInt(1));
		     }
			 System.out.println("今増やしたレコードのid:" + ItemID);
			 */

			//写真の名前がさっき登録したやつと同じレコードを取得。そのidを調べる
			// SQL文を準備する
			String sqlSelectID = "select id FROM Item WHERE photo = ?";
			pStmt = conn.prepareStatement(sqlSelectID);
			pStmt.setString(1, photo);

			// SQL文を実行し、結果を取得する
			ResultSet rs = pStmt.executeQuery();

			//
			// 結果をコピーする
			while (rs.next()) {
				ItemID = rs.getString("ID");
			}
			System.out.println("今増やしたレコードのid:" + ItemID);


			String sqlColorSeason = "INSERT INTO Item_season (ITEM_ID, CODE, FLAG) VALUES (?, 1, ?); "
					+ "INSERT INTO Item_season (ITEM_ID, CODE, FLAG) VALUES (?, 2, ?);"
					+ "INSERT INTO Item_season (ITEM_ID, CODE, FLAG) VALUES (?, 3, ?); "
					+ "INSERT INTO Item_season (ITEM_ID, CODE, FLAG) VALUES (?, 4, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 1, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 2, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 3, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 4, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 5, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 6, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 7, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 8, ?);"
					+ "INSERT INTO Item_color (ITEM_ID, CODE, FLAG) VALUES (?, 9, ?);";
			pStmt = conn.prepareStatement(sqlColorSeason);
			for (int i = 0; i < 2 * 13; i++) {
				if (i % 2 == 1) {
					pStmt.setString(i, ItemID);
				}
			}
			if (registinf.getSpring() != null) {
				pStmt.setString(2, "1");
			} else {
				pStmt.setString(2, "0");
			}
			if (registinf.getSummer() != null) {
				pStmt.setString(4, "1");
			} else {
				pStmt.setString(4, "0");
			}
			if (registinf.getAutumn() != null) {
				pStmt.setString(6, "1");
			} else {
				pStmt.setString(6, "0");
			}
			if (registinf.getWinter() != null) {
				pStmt.setString(8, "1");
			} else {
				pStmt.setString(8, "0");
			}
			if (registinf.getWhite() != null) {
				pStmt.setString(10, "1");
			} else {
				pStmt.setString(10, "0");
			}
			if (registinf.getBlack() != null) {
				pStmt.setString(12, "1");
			} else {
				pStmt.setString(12, "0");
			}
			if (registinf.getGrey() != null) {
				pStmt.setString(14, "1");
			} else {
				pStmt.setString(14, "0");
			}
			if (registinf.getBeige() != null) {
				pStmt.setString(16, "1");
			} else {
				pStmt.setString(16, "0");
			}
			if (registinf.getRed() != null) {
				pStmt.setString(18, "1");
			} else {
				pStmt.setString(18, "0");
			}
			if (registinf.getBlue() != null) {
				pStmt.setString(20, "1");
			} else {
				pStmt.setString(20, "0");
			}
			if (registinf.getGreen() != null) {
				pStmt.setString(22, "1");
			} else {
				pStmt.setString(22, "0");
			}
			if (registinf.getYellow() != null) {
				pStmt.setString(24, "1");
			} else {
				pStmt.setString(24, "0");
			}
			if (registinf.getOther() != null) {
				pStmt.setString(26, "1");
			} else {
				pStmt.setString(26, "0");
			}
			// SQL文を実行する
			cnt = pStmt.executeUpdate();
			if (cnt == 13) {
				System.out.println("季節、色；" + cnt);
				result = true;
			} else {
				result = false;
			}
		}

		catch (SQLException e) {
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

		// 結果を返す
		return result;
	}

	public boolean update(RegistInf r , String userID, String itemID, String photo) {
		return false;
	}
	public boolean delete(String itemID) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM ITEM WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, itemID);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}

				// SQL文を準備する
			sql = "DELETE FROM ITEM_SEASON WHERE item_id=?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, itemID);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 4) {
				result = true;
			} else {
				result = false;
			}


			// SQL文を準備する
			sql = "DELETE FROM ITEM_COLOR WHERE item_id=?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, itemID);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 9) {
				result = true;
			} else {
				result = false;
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
		// 結果を返す
		return result;
	}
}

