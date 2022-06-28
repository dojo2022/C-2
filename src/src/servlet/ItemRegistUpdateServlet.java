package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ItemDAO;
import model.ColorSeason;
import model.Item;
import model.RegistInf;
import model.Result;

/**
 * Servlet implementation class ItemRegistUpdateServlet
 */
@MultipartConfig(location = "C:\\dojo6\\src\\WebContent\\photo") // アップロードファイルの一時的な保存先
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
		HttpSession session = request.getSession();

		//String id = ((User) session.getAttribute("id")).getId();
		String id = "aaaaa";

		// いったんエラーを消すためだけの処理
		/*
		String photoExtension = "";
		String userID = "";
		String itemID = "";
		*/

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

		if (request.getParameter("REGIST") != null) {
			Part part = request.getPart("IMAGE"); // getPartで取得
			String photo = null;
			String fileName = this.getFileName(part);
			System.out.println(fileName);
			//System.out.println("part:" + part + "file :"  +this.getFileName(part));
			if (fileName != null && !(fileName.equals(""))) {
				photo = id + System.currentTimeMillis() + "." + this.getExtension(fileName);
				part.write(photo);
				photo = "\\photo\\" + photo;
				}
			//System.out.println(photo);
			// 登録処理を行う
			ItemDAO iDao = new ItemDAO();
			if (iDao.insert(new RegistInf(spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes,
					white, black, grey, beige, red, blue, green, yellow, other, patternYES, patternNO, rainOK, rainNG, windOK, windNG,parts), id, photo)) { // 登録成功
				request.setAttribute("result",
						new Result(true));
			}
			else { // 登録失敗
				request.setAttribute("result",
						new Result(false));
			}
			/*
			RegistInf registinf = new RegistInf();
			Item item =new Item();

			item.setPhoto(photo);

			boolean ret = iDao.insert(registinf, id, photo);


			//リクエストスコープに保存する
			request.setAttribute("item", item);
			*/

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemRegist.jsp");
			dispatcher.forward(request, response);
		} else if (request.getParameter("item_id_edit") != null) {
			String itemId = request.getParameter("item_id_edit");
			System.out.println("選択したアイテムのid" + itemId);
			ItemDAO iDao = new ItemDAO();
			List<Object> itemInf = iDao.select(itemId);
			Item item = (Item) (itemInf.get(0));
			ColorSeason colorSeason = (ColorSeason) (itemInf.get(1));
			request.setAttribute("item", item);
			request.setAttribute("colorSeason", colorSeason);
			// 結果ページにフォワードする
			RequestDispatcher dispatchers = request.getRequestDispatcher("/WEB-INF/jsp/itemUpdate.jsp");
			dispatchers.forward(request, response);
		}else {
			Part part = request.getPart("IMAGE"); // getPartで取得
			String photo = null;
			String fileName = this.getFileName(part);
			System.out.println(fileName);
			//System.out.println("part:" + part + "file :"  +this.getFileName(part));
			if (fileName != null && !(fileName.equals(""))) {
				photo = id + System.currentTimeMillis() + "." + this.getExtension(fileName);
				part.write(photo);
				photo = "\\photo\\" + photo;
				}
			//System.out.println(photo);
			String itemId = request.getParameter("item_id");
			// 更新または削除を行う
			ItemDAO tDao = new ItemDAO();
			if (request.getParameter("update") != null) {
				if (tDao.update(new RegistInf(spring, summer, autumn, winter, outer, jacket, tops, skirt, pants, shoes,
						white, black, grey, beige, red, blue, green, yellow, other, patternYES, patternNO, rainOK, rainNG, windOK, windNG,parts), id, itemId, photo)) { // 更新成功
					request.setAttribute("result",
							new Result(true));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result(false));
				}
			} else if (request.getParameter("delete") != null) {
				if (tDao.delete(itemId)) { // 削除成功
					request.setAttribute("result",
							new Result(true));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result(false));
				}
			}
			Boolean flag = false;
			request.setAttribute("search", flag);
			// 結果ページにフォワードする
			RequestDispatcher dispatchers = request.getRequestDispatcher("/WEB-INF/jsp/itemSearch.jsp");
			dispatchers.forward(request, response);
		}

	}
	//ファイルの名前を取得してくる
		private String getFileName(Part part) {
			String name = null;
			for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
				if (dispotion.trim().startsWith("filename")) {
					name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
					name = name.substring(name.lastIndexOf("\\") + 1);
					break;
				}
			} // TODO 自動生成されたメソッド・スタブ
			return name;
		}

		public String getExtension(String filename) {
			char[] array = filename.toCharArray();
			int dotIndex = -1;
			for (int i = array.length - 1; i >= 0; i--) {
				if (array[i] == '.') {
					dotIndex = i;
					break;
				}
			}
			String str = "";
			for (int i = dotIndex + 1; i < array.length; i++) {
				str += array[i];
			}
			return str;
		}
}

