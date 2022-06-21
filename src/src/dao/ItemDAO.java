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
public boolean insert(RegistInf registinf, String id, String photoExtension) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection(dbURL, "sa", "");

		// itemテーブルにインサート
		// SQL文を準備する
		/*
		String sql = "insert into item (spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes, "
				+ "white, black, grey, beige, red, blue, green, yellow, other, pattern, rain, wind) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		*/
		String sql = "INSERT INTO Item (USER_ID, PARTS_CODE, PATTERN, RAIN, WIND, PHOTO) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(1, id);

			switch (registinf.getParts()){
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

			if (registinf.getPatternYES().equals("on") && registinf.getPatternNO()==null) {
				pStmt.setString(3, "1");
			} else if (registinf.getPatternYES()==null && registinf.getPatternNO().equals("on")) {
				pStmt.setString(3, "0");
			} else {
				pStmt.setString(3, "0");
			}
			/*
			switch (registinf.getPatternYES()){
			  case "on":
				  pStmt.setString(3, "1");
			    break;
			  case "null":
				  pStmt.setString(3, "0");
			    break;
			  case "tops":
				  pStmt.setString(3, "3");
			    break;
			  default:
					break;*/

			if (registinf.getRainOK().equals("on") && registinf.getRainNG() == null) {
				pStmt.setString(4, "1");
			} else if (registinf.getRainOK() == null && registinf.getRainNG().equals("on")) {
				pStmt.setString(4, "0");
			} else {
				pStmt.setString(4, "1");
			}

			if (registinf.getWindOK().equals("on") && registinf.getWindNG() == null) {
				pStmt.setString(5, "1");
			} else if (registinf.getWindOK() == null && registinf.getWindNG().equals("on")) {
				pStmt.setString(5, "0");
			} else {
				pStmt.setString(5, "1");
			}

			//写真の名前
			String photoName = "\\photo\\" + id +  System. currentTimeMillis() + photoExtension;
			pStmt.setString(6, photoName);
			/*

		pStmt.setString(2, registinf.getSummer());

		pStmt.setString(3, registinf.getAutumn());

		pStmt.setString(4, registinf.getWinter());

		pStmt.setString(5, registinf.getOuter());

		pStmt.setString(6, registinf.getJacket());

		pStmt.setString(7, registinf.getTops());

		pStmt.setString(8, registinf.getSkirt());

		pStmt.setString(9, registinf.getPants());

		pStmt.setString(10, registinf.getShoes());

		pStmt.setString(11, registinf.getWhite());

		pStmt.setString(12, registinf.getBlack());

		pStmt.setString(13, registinf.getGrey());

		pStmt.setString(14, registinf.getBeige());

		pStmt.setString(15, registinf.getRed());

		pStmt.setString(16, registinf.getBlue());

		pStmt.setString(17, registinf.getGreen());

		pStmt.setString(18, registinf.getYellow());

		pStmt.setString(19, registinf.getOther());
		*/
/*
		pStmt.setString(20, registinf.getPattern());

		pStmt.setString(21, registinf.getRain());

		pStmt.setString(22, registinf.getWind());
		*/


		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}

		//写真の名前がさっき登録したやつと同じレコードを取得。そのidを調べる
		// SQL文を準備する
					String sqlSelectID = "select id FROM Item WHERE photo = ?";
					pStmt = conn.prepareStatement(sqlSelectID);
					pStmt.setString(1, photoName);

					// SQL文を実行し、結果を取得する
					ResultSet rs = pStmt.executeQuery();

					String ItemID = "";
					// 結果をコピーする
					while (rs.next()) {
						ItemID = rs.getString("ID");
					}


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
		for (int i = 0; i < 2 * 13; i++) {
			if (i % 2 == 1) {
				pStmt.setString(i, ItemID);
			}
		}
		if (registinf.getSpring().equals("on")) {
			pStmt.setString(2, "1");
		} else {
			pStmt.setString(2, "0");
		}
		if (registinf.getSummer().equals("on")) {
			pStmt.setString(4, "1");
		} else {
			pStmt.setString(4, "0");
		}
		if (registinf.getAutumn().equals("on")) {
			pStmt.setString(6, "1");
		} else {
			pStmt.setString(6, "0");
		}
		if (registinf.getWinter().equals("on")) {
			pStmt.setString(8, "1");
		} else {
			pStmt.setString(8, "0");
		}
		if (registinf.getWhite().equals("on")) {
			pStmt.setString(10, "1");
		} else {
			pStmt.setString(10, "0");
		}
		if (registinf.getBlack().equals("on")) {
			pStmt.setString(12, "1");
		} else {
			pStmt.setString(12, "0");
		}
		if (registinf.getGrey().equals("on")) {
			pStmt.setString(14, "1");
		} else {
			pStmt.setString(14, "0");
		}
		if (registinf.getBeige().equals("on")) {
			pStmt.setString(16, "1");
		} else {
			pStmt.setString(16, "0");
		}if (registinf.getRed().equals("on")) {
			pStmt.setString(18, "1");
		} else {
			pStmt.setString(18, "0");
		}
		if (registinf.getBlue().equals("on")) {
			pStmt.setString(20, "1");
		} else {
			pStmt.setString(20, "0");
		}
		if (registinf.getGreen().equals("on")) {
			pStmt.setString(22, "1");
		} else {
			pStmt.setString(22, "0");
		}
		if (registinf.getYellow().equals("on")) {
			pStmt.setString(24, "1");
		} else {
			pStmt.setString(24, "0");
		}
		if (registinf.getOther().equals("on")) {
			pStmt.setString(26, "1");
		} else {
			pStmt.setString(26, "0");
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
	public boolean update(RegistInf r) {
		return false;
	}
	public boolean delete(String s) {
		return false;
	}
}
