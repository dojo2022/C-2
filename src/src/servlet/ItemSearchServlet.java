package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import model.Item;
import model.SearchCondition;

/**
 * Servlet implementation class ItemSearchServlet
 */
@WebServlet("/ItemSearchServlet")
public class ItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String userId = "aaaaa";
		// 検索処理を行う　改造
		ItemDAO IDao = new ItemDAO();
		List<Item> outerList = new ArrayList<Item>();
		List<Item> jkList = new ArrayList<Item>();
		List<Item> topsList = new ArrayList<Item>();
		List<Item> skirtList = new ArrayList<Item>();
		List<Item> pantsList = new ArrayList<Item>();
		List<Item> shoesList = new ArrayList<Item>();
		// 季節　パーツ名　色　柄　雨　風
		if (request.getAttribute("list") != null) {
			System.out.println("一覧");
			outerList = IDao.searchParts(userId, 1);
			jkList = IDao.searchParts(userId, 2);
			topsList = IDao.searchParts(userId, 3);
			skirtList = IDao.searchParts(userId, 4);
			pantsList = IDao.searchParts(userId, 5);
			shoesList = IDao.searchParts(userId, 6);
		} else if (request.getAttribute("search") != null) {
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
			String patternYES = request.getParameter("patternYES");
			String patternNO = request.getParameter("patternNO");
			String rainOK = request.getParameter("rainOK");
			String rainNG = request.getParameter("rainNG");
			String windOK = request.getParameter("windOK");
			String windNG = request.getParameter("windNG");

			//かっこの中を変える
			List<Item> itemList = IDao.select(
					new SearchCondition(spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes,
							white, black, grey, beige, red, blue, green, yellow, other, patternYES, patternNO, rainOK,
							rainNG, windOK, windNG),
					userId);


			for (int i = 0; i < itemList.size(); i++) {
				switch (itemList.get(i).getPartsCode()) {
				case 1:
					outerList.add(itemList.get(i));
					break;
				case 2:
					jkList.add(itemList.get(i));
					break;
				case 3:
					topsList.add(itemList.get(i));
					break;
				case 4:
					skirtList.add(itemList.get(i));
					break;
				case 5:
					pantsList.add(itemList.get(i));
					break;
				case 6:
					shoesList.add(itemList.get(i));
					break;
				default:
					break;
				}
			}
		}
		System.out.println("outer" + outerList.size());
		System.out.println("jk" + jkList.size());
		System.out.println("tops" + topsList.size());
		System.out.println("skirt" + skirtList.size());
		System.out.println("pants" + pantsList.size());
		System.out.println("shoes" + shoesList.size());
		//スコープ
		request.setAttribute("outerList", outerList);
		request.setAttribute("jkList", jkList);
		request.setAttribute("topsList", topsList);
		request.setAttribute("skirtList", skirtList);
		request.setAttribute("pantsList", pantsList);
		request.setAttribute("shoesList", shoesList);
		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemSearch.jsp");
		dispatcher.forward(request, response);

	}
}
