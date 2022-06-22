package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DiaryDAO;
import model.Diary;
import model.User;

/**
 * Servlet implementation class DiaryEditServlet
 */
//↓これ絶対入れてね！！
@MultipartConfig(location = "C:\\dojo6\\src\\WebContent\\photo") // アップロードファイルの一時的な保存先
@WebServlet("/DiaryEditServlet")
public class DiaryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diaryEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("edit");
		String s2 = request.getParameter("update");
		if (s1.equals("編集")) {

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String diaryId = request.getParameter("diary_id");

			System.out.println("日記id:" + diaryId);

			DiaryDAO dDao = new DiaryDAO();
			Diary diary = dDao.search(diaryId);

			//dをスコープに入れる
			request.setAttribute("diary", diary);

			/*if (request.getParameter("SUBMIT").equals("更新")) {
				if (dDao.update(new Diary("photo","note"))) { // 更新成功
					request.setAttribute("result",
							new Result("更新成功！", "レコードを更新しました。", "/simpleBC/MenuServlet"));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result("更新失敗！", "レコードを更新できませんでした。", "/simpleBC/MenuServlet"));
				}
			*/

			/*else {
				if (bDao.delete(number)) { // 削除成功
					request.setAttribute("result",
							new Result("削除成功！", "レコードを削除しました。", "/simpleBC/MenuServlet"));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result("削除失敗！", "レコードを削除できませんでした。", "/simpleBC/MenuServlet"));
				}
			}*/

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diaryEdit.jsp");
			dispatcher.forward(request, response);

		} else if (s2.equals("更新")) {
			//id
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("id")).getId();;
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			Part part = request.getPart("IMAGE"); // getPartで取得
			String photo = "\\photo\\" + userId + System.currentTimeMillis() + this.getExtension(this.getFileName(part));

			part.write(photo);

			String diaryId = request.getParameter("diary_id");
			String note = request.getParameter("note");


			//dao


			DiaryDAO dDao = new DiaryDAO();
			Diary diary = dDao.search(diaryId);
			//リクエストスコープに保存する
			//request.setAttribute("diaryList");

			//jspにフォワード
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diary.jsp");
			dispatcher.forward(request, response);

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
	        }		// TODO 自動生成されたメソッド・スタブ
			return name;
		}
		public String getExtension(String filename) {
		    char[] array = filename.toCharArray();
		    int dotIndex = -1;
		    for (int i = array.length - 1; i >= 0; i--) {
		      if (array[i] =='.') {
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