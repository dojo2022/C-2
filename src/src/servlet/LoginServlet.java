package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");

		// ログイン処理を行う
				UserDAO iDao = new UserDAO();
				if (iDao.isLoginOK(new User(id, null, password))) { // ログイン成功
					// セッションスコープにIDを格納する
					HttpSession session = request.getSession();
					session.setAttribute("id", new User(id, null, null));

					// メニューサーブレットにリダイレクトする
					response.sendRedirect("/coordinator/HomeServlet");
				} else {



					/*// ログイン失敗
					// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
					request.setAttribute("result",
							new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/coordinator/LoginServlet"));
*/
				}
	}

}
