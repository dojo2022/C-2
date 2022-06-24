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
import model.SearchCondition;

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

			if (registinf.getRainOK() != null && registinf.getRainNG() == null) {
				pStmt.setString(4, "1");
			} else if (registinf.getRainOK() == null && registinf.getRainNG() != null) {
				pStmt.setString(4, "0");
			} else {
				pStmt.setString(4, "1");
			}

			if (registinf.getWindOK() != null && registinf.getWindNG() == null) {
				pStmt.setString(5, "1");
			} else if (registinf.getWindOK() == null && registinf.getWindNG() != null) {
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

	public boolean update(RegistInf registinf, String userID, String itemID, String photo) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "UPDATE ITEM SET PARTS_CODE=? PATTERN=? RAIN=?	WIND=? PHOTO=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			switch (registinf.getParts()) {
			case "outer":
				pStmt.setString(1, "1");
				break;
			case "jacket":
				pStmt.setString(1, "2");
				break;
			case "tops":
				pStmt.setString(1, "3");
				break;
			case "skirt":
				pStmt.setString(1, "4");
				break;
			case "pants":
				pStmt.setString(1, "5");
				break;
			case "shoes":
				pStmt.setString(1, "6");
				break;
			default:
				break;
			}

			if (registinf.getPatternYES() != null && registinf.getPatternNO() == null) {
				pStmt.setString(2, "1");
			} else if (registinf.getPatternYES() == null && registinf.getPatternNO() != null) {
				pStmt.setString(2, "0");
			} else {
				pStmt.setString(2, "0");
			}

			if (registinf.getRainOK() != null && registinf.getRainNG() == null) {
				pStmt.setString(3, "1");
			} else if (registinf.getRainOK() == null && registinf.getRainNG() != null) {
				pStmt.setString(3, "0");
			} else {
				pStmt.setString(3, "1");
			}

			if (registinf.getWindOK() != null && registinf.getWindNG() == null) {
				pStmt.setString(4, "1");
			} else if (registinf.getWindOK() == null && registinf.getWindNG() != null) {
				pStmt.setString(4, "0");
			} else {
				pStmt.setString(4, "1");
			}

			pStmt.setString(5, photo);

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

			//String sqlColorSeason =

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

	//検索

	public List<Item> select(SearchCondition sc, String id) {
		Connection conn = null;
		//null:初期化
		List<Item> itemList2 = new ArrayList<Item>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			/*
						String sql = "SELECT *"
								+ " FROM Item INNER JOIN Item_Season ON Item.id = Item_Season.Item_id INNER JOIN Item_Color ON Item.id = Item_Color.Item_id "// INNER JOIN Parts ON Item.parts_code = Parts.code"
								+ " WHERE (";
						sql += " ((Item_season.code = 1 AND Item_season.flag = ?) OR (Item_season.code = 2 AND Item_season.flag = ?) OR (Item_season.code = 3 AND Item_season.flag = ?) OR (Item_season.code = 4 AND Item_season.flag = ?))";
						sql += " AND ((Item_color.code = 1 AND Item_color.flag = ?) OR (Item_color.code = 2 AND Item_color.flag = ?) OR (Item_color.code = 3 AND Item_color.flag = ?) OR (Item_color.code = 4 AND Item_color.flag = ?) OR (Item_color.code = 5 AND Item_color.flag = ?) OR (Item_color.code = 6 AND Item_color.flag = ?) OR (Item_color.code = 7 AND Item_color.flag = ?) OR (Item_color.code = 8 AND Item_color.flag = ?) OR (Item_color.code = 9 AND Item_color.flag = ?)))";
						sql += " AND Item.user_id = ?";
						//sql += "(Item.pattern = ?) AND (Item_color.code = 2 AND Item_color.flag = ?) AND (Item_color.code = 3 AND Item_color.flag = ?) AND (Item_color.code = 4 AND Item_color.flag = ?)AND (Item_color.code = 5 AND Item_color.flag = ?) AND (Item_color.code = 6 AND Item_color.flag = ?)";
						if ((sc.getPatternYES() != null && sc.getPatternNO() == null)) {
							sql += " AND Item.pattern = 1";
						}else if ((sc.getPatternYES() == null && sc.getPatternNO() != null)) {
							sql += " AND Item.pattern = 0";
						}

						if ((sc.getRainOK() != null && sc.getRainNG() == null)) {
							sql += " AND Item.rain = 1";
						}else if ((sc.getRainOK() == null && sc.getRainNG() != null)) {
							sql += " AND Item.rain = 0";
						}

						if ((sc.getWindOK() != null && sc.getWindNG() == null)) {
							sql += " AND Item.wind = 1";
						}else if ((sc.getWindOK() == null && sc.getWindNG() != null)) {
							sql += " AND Item.wind = 0";
						}

						if (sc.getOuter() != null || sc.getJacket() != null || sc.getTops() != null || sc.getSkirt() != null || sc.getPants() != null || sc.getShoes() != null) {
							boolean flg = false;
							sql += " AND(";
							if (sc.getOuter() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 1";
								flg = true;
							}
							if (sc.getJacket() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 2";
								flg = true;
							}

							if (sc.getTops() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 3";
								flg = true;
							}

							if (sc.getSkirt() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 4";
								flg = true;
							}

							if (sc.getPants() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 5";
								flg = true;
							}

							if (sc.getShoes() != null) {
								if (flg) {
									sql += " OR";
								}
								sql += " Item.parts_code = 6";
								flg = true;
							}
							sql += " );";
						}




						/*
						sql += "(Item_season.code = 1 AND Item_season.flag = ";
						if (sc.getSpring() != null) {
							 sql += "1";
						} else {
							sql += "0";
						}
						sql += ") AND";


						if (sc.getSpring() != null) {
							 sql += "(Item_season.code = 1 AND Item_season.flag = 1)";
						} else {
							sql += "(Item_season.code = 1 AND Item_season.flag = 0)";
						}



						sql += ")";



						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						if (sc.getSpring() != null) {
							pStmt.setString(1, "1");
						} else {
							pStmt.setString(1, "0");
						}

						if (sc.getSummer() != null) {
							pStmt.setString(2, "1");
						} else {
							pStmt.setString(2, "0");
						}

						if (sc.getAutumn() != null) {
							pStmt.setString(3, "1");
						} else {
							pStmt.setString(3, "0");
						}

						if (sc.getWinter() != null) {
							pStmt.setString(4, "1");
						} else {
							pStmt.setString(4, "0");
						}

						if (sc.getWhite() != null) {
							pStmt.setString(5, "1");
						} else {
							pStmt.setString(5, "0");
						}

						if (sc.getBlack() != null) {
							pStmt.setString(6, "1");
						} else {
							pStmt.setString(6, "0");
						}

						if (sc.getGrey() != null) {
							pStmt.setString(7, "1");
						} else {
							pStmt.setString(7, "0");
						}

						if (sc.getBeige() != null) {
							pStmt.setString(8, "1");
						} else {
							pStmt.setString(8, "0");
						}

						if (sc.getRed() != null) {
							pStmt.setString(9, "1");
						} else {
							pStmt.setString(9, "0");
						}

						if (sc.getBlue() != null) {
							pStmt.setString(10, "1");
						} else {
							pStmt.setString(10, "0");
						}

						if (sc.getGreen() != null) {
							pStmt.setString(11, "1");
						} else {
							pStmt.setString(11, "0");
						}

						if (sc.getYellow() != null) {
							pStmt.setString(12, "1");
						} else {
							pStmt.setString(12, "0");
						}

						if (sc.getOther() != null) {
							pStmt.setString(13, "1");
						} else {
							pStmt.setString(13, "0");
						}

						pStmt.setString(14, id);
						*/
			// SQL文を準備する
			String sql = "select * from item join item_season on item.id = item_season.item_id join item_color on item.id = item_color.item_id";
			if (!((sc.getSpring() != null && sc.getSummer() != null && sc.getAutumn() != null && sc.getWinter() != null)
					|| (sc.getSpring() == null && sc.getSummer() == null && sc.getAutumn() == null
							&& sc.getWinter() == null))) {
				sql += " where item_season.flag = 1 and item_season.code in (";
				boolean flg = false;
				if (sc.getSpring() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getSummer() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "2";
					flg = true;
				}
				if (sc.getAutumn() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "3";
					flg = true;
				}
				if (sc.getWinter() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "4";
					flg = true;
				}

				sql += ")";
			}
			if (!((sc.getWhite() != null && sc.getBlack() != null && sc.getGrey() != null && sc.getBeige() != null
					&& sc.getRed() != null && sc.getBlue() != null && sc.getGreen() != null && sc.getYellow() != null
					&& sc.getOther() != null)
					|| (sc.getWhite() == null && sc.getBlack() == null && sc.getGrey() == null && sc.getBeige() == null
							&& sc.getRed() == null && sc.getBlue() == null && sc.getGreen() == null
							&& sc.getYellow() == null && sc.getOther() == null))) {
				sql += " AND item_color.flag = 1 and item_color.code in (";
				boolean flg = false;
				if (sc.getWhite() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getBlack() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "2";
					flg = true;
				}
				if (sc.getGrey() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "3";
					flg = true;
				}
				if (sc.getBeige() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "4";
					flg = true;
				}
				if (sc.getRed() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "5";
					flg = true;
				}
				if (sc.getBlue() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "6";
					flg = true;
				}
				if (sc.getGreen() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "7";
					flg = true;
				}
				if (sc.getYellow() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "8";
					flg = true;
				}
				if (sc.getOther() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "9";
					flg = true;
				}
				sql += ")";

			}

			if (!((sc.getOuter() != null && sc.getJacket() != null && sc.getTops() != null && sc.getSkirt() != null
					&& sc.getPants() != null && sc.getShoes() != null)
					|| (sc.getOuter() == null && sc.getJacket() == null && sc.getTops() == null && sc.getSkirt() == null
							&& sc.getPants() == null && sc.getShoes() == null))) {
				sql += " AND item.parts_code in (";
				boolean flg = false;
				if (sc.getOuter() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getJacket() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "2";
					flg = true;
				}
				if (sc.getTops() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "3";
					flg = true;
				}
				if (sc.getSkirt() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "4";
					flg = true;
				}
				if (sc.getPants() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "5";
					flg = true;
				}
				if (sc.getShoes() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "6";
					flg = true;
				}
				sql += ")";
			}

			if (!((sc.getPatternYES() != null && sc.getPatternNO() != null)
					|| (sc.getPatternYES() == null && sc.getPatternNO() == null))) {
				sql += " AND item.pattern in (";
				boolean flg = false;
				if (sc.getPatternYES() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getPatternNO() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "0";
					flg = true;
				}
				sql += ")";
			}

			if (!((sc.getRainOK() != null && sc.getRainNG() != null)
					|| (sc.getRainOK() == null && sc.getRainNG() == null))) {
				sql += " AND item.Rain in (";
				boolean flg = false;
				if (sc.getRainOK() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getRainNG() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "0";
					flg = true;
				}
				sql += ")";
			}

			if (!((sc.getWindOK() != null && sc.getWindNG() != null)
					|| (sc.getWindOK() == null && sc.getWindNG() == null))) {
				sql += " AND item.Wind in (";
				boolean flg = false;
				if (sc.getWindOK() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "1";
					flg = true;
				}
				if (sc.getWindNG() != null) {
					if (flg) {
						sql += ",";
					}
					sql += "0";
					flg = true;
				}
				sql += ")";
			}

			//sql += " AND Item.user_id in = ";
			sql += " AND Item.user_id = ?";

			// SQL文を実行し、結果表を取得する
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			ArrayList<Integer> al = new ArrayList<Integer>();
			while (rs.next()) {
				int tmpId = Integer.parseInt(rs.getString("ID"));
				boolean tmpFlg = true;
				for (int i = 0; i < al.size(); i++) {
					if (al.get(i) == tmpId) {
						tmpFlg = false;
					}
				}
				if (tmpFlg) {
					al.add(tmpId);
					Item item = new Item(
							Integer.parseInt(rs.getString("ID")),
							rs.getString("USER_ID"),
							Integer.parseInt(rs.getString("PARTS_CODE")),
							Integer.parseInt(rs.getString("PATTERN")),
							Integer.parseInt(rs.getString("RAIN")),
							Integer.parseInt(rs.getString("WIND")),
							rs.getString("PHOTO"));
					itemList2.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			itemList2 = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			itemList2 = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					itemList2 = null;
				}
			}
		}

		// 結果を返す
		return itemList2;
	}

}
