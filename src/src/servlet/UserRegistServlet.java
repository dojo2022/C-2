package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Result;
import model.User;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


 		// 登録ページにフォワードする
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegist.jsp");
 		dispatcher.forward(request, response);
 	}

 	/**
 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


 		// リクエストパラメータを取得する　
 		request.setCharacterEncoding("UTF-8");
 		String id = request.getParameter("ID");
 		String name = request.getParameter("NAME");
 		String password = request.getParameter("PW");
 		String password2 = request.getParameter("PW2");
 		System.out.println(id + name + password);

 		if (password.equals(password2)) {
 		// 登録処理を行う
 	 		UserDAO uDao = new UserDAO();
 	 		if (uDao.userRegist(new User(id,name,password,password2))) {	// 登録成功　
 	 			request.setAttribute("result",
 	 			new Result(true));
 	 		}
 	 		else {												// 登録失敗
 	 			request.setAttribute("result",
 	 			new Result(false));
 	 		}
 		} else {
 			request.setAttribute("result",
 	 	 			new Result(false));
 		}


 		// 結果ページにフォワードする
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
 		dispatcher.forward(request, response);
 	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */



}
