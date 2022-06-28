package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	String dbURL = "jdbc:h2:file:C:\\dojo6_data\\C2";


	// ログインできるならtrueを返す
	public boolean isLoginOK(User user) {
		Connection conn = null;
		boolean loginResult = false;


		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection(dbURL, "sa", "");

			// SQL文を準備する
			String sql = "select count(*) from USER where ID = ? and PASSWORD = ?";

			// SQL文を完成させる
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setString(2,user.getPassword());
			//System.out.println("select count(*) from USER where ID = " +user.getId()+" and PASSWORD = " + user.getPassword());


			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();


			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			int tmp = rs.getInt("count(*)");
			//System.out.println(tmp);
			if (tmp == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}

	// 指定されたレコードを登録し、成功したらtrueを返す
		public boolean userRegist(User user) {
			//System.out.println("userRegist() start");
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver"); //テンプレの構文

				// データベースに接続する
				conn = DriverManager.getConnection(dbURL, "sa", "");

				// SQL文を準備する＜改造＞
				String sql = "INSERT INTO USER (id,name,password) values (?,?,?)";// ?何が入るかわからない
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる＜改造＞
				System.out.println(user.getId() + user.getName() + user.getPassword());

				if (user.getId() != null && !user.getId().equals("")) {
					pStmt.setString(1, user.getId());
				}
				else {
					pStmt.setString(1, null);
				}
				if (user.getName() != null && !user.getName().equals("")) {
					pStmt.setString(2, user.getName());
				}
				else {
					pStmt.setString(2, null);
				}
				if (user.getPassword() != null && !user.getPassword().equals("")) {
					pStmt.setString(3, user.getPassword());
				}
				else {
					pStmt.setString(3, null);
				}



				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					System.out.println("tourokudekitayo");
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

			// 結果を返す
			return result;
		}
}
