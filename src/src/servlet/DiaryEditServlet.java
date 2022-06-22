package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiaryDAO;
import model.Diary;

/**
 * Servlet implementation class DiaryEditServlet
 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
			dispatcher.forward (request, response);
	}
}