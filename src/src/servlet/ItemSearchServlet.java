package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDAO;
import model.Item;

/**
 * Servlet implementation class ItemSearchServlet
 */
@WebServlet("/ItemSearchServlet")
public class ItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//				HttpSession session = request.getSession();
//				if (session.getAttribute("id") == null) {
//					response.sendRedirect("/coordinator/LoginServlet");
//					return;
//				}

				// 検索ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemSearch.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
					response.sendRedirect("/coordinator/LoginServlet");
					return;
				}

// 季節　パーツ名　色　柄　雨　風


				// リクエストパラメータを取得する　改造項目数
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
				String pattern1 = request.getParameter("pattern1");
				String pattern0 = request.getParameter("pattern0");
				String rain1 = request.getParameter("rain1");
				String rain0 = request.getParameter("rain0");
				String wind1 = request.getParameter("wind1");
				String wind0 = request.getParameter("wind0");


				// 検索処理を行う　改造
				ItemDAO IDao = new ItemDAO();
				//かっこの中を変える
				List<Item> itemList = IDao.select(new Item(spring,summer,autumn,winter,outer,jacket,tops,skirt,pants,shoes,white,black,grey,beige,red,blue,green,yellow,other,pattern1,pattern0,rain1,rain0,));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("itemList", itemList);



	}

}
