package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import model.RegistInf;
import model.Result;

/**
 * Servlet implementation class ItemRegistUpdateServlet
 */
@WebServlet("/ItemRegistUpdateServlet")
public class ItemRegistUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response):
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/coordinator/LoginServlet");
			return;
		}
		*/

		//String id = ((User) session.getAttribute("id")).getId();
		String id = "aaaaa";

		String photoExtension = "";

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String spring = request.getParameter("spring");
		String summer = request.getParameter("summer");
		String autumn = request.getParameter("autumn");
		String winter = request.getParameter("winter");
		String outer = request.getParameter("outer");
		String jacket = request.getParameter("jacket");
		String tops = request.getParameter("tops");
		String skirt = request.getParameter("skirt");
		String pants = request.getParameter("pants");
		String shoes = request.getParameter("shoes");
		String white = request.getParameter("white");
		String black = request.getParameter("black");
		String grey = request.getParameter("grey");
		String beige = request.getParameter("beige");
		String red = request.getParameter("red");
		String blue = request.getParameter("blue");
		String green = request.getParameter("green");
		String yellow = request.getParameter("yellow");
		String other = request.getParameter("other");
		String patternYES = request.getParameter("pattern_yes");
		String patternNO = request.getParameter("pattern_no");
		String rainOK = request.getParameter("rain_ok");
		String rainNG = request.getParameter("rain_ng");
		String windOK = request.getParameter("wind_ok");
		String windNG = request.getParameter("wind_ng");
		String parts = request.getParameter("parts");

		System.out.println("parts:" + parts);
		/*
		System.out.println("spring:" + spring);
		System.out.println("summer:" +summer);
		System.out.println("patternYES:" + patternYES);
		System.out.println("patternNO:" + patternNO);
		*/

		if (request.getParameter("REGIST").equals("登録")) {
			// 登録処理を行う
			ItemDAO iDao = new ItemDAO();
			if (iDao.insert(new RegistInf(spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes,
					white, black, grey, beige, red, blue, green, yellow, other, patternYES, patternNO, rainOK, rainNG, windOK, windNG,parts), id, photoExtension)) { // 登録成功
				request.setAttribute("result",
						new Result(true));
			} else { // 登録失敗
				request.setAttribute("result",
						new Result(false));
			}

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemRegist.jsp");
			dispatcher.forward(request, response);
		} else {

			// 更新または削除を行う
			ItemDAO tDao = new ItemDAO();
			if (request.getParameter("SUBMIT").equals("更新")) {
				if (tDao.update(new RegistInf(spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes,
						white, black, grey, beige, red, blue, green, yellow, other, patternYES, patternNO, rainOK, rainNG, windOK, windNG,parts))) { // 更新成功
					request.setAttribute("result",
							new Result(true));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result(false));
				}
			} else {
				if (tDao.delete(id)) { // 削除成功
					request.setAttribute("result",
							new Result(true));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result(false));
				}
			}
			// 結果ページにフォワードする
			RequestDispatcher dispatchers = request.getRequestDispatcher("/WEB-INF/jsp/itemSearch.jsp");
			dispatchers.forward(request, response);
		}

	}
}
