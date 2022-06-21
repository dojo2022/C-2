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

import dao.DiaryDAO;
import model.Diary;

/**
 * Servlet implementation class DiaryServlet
 */
@WebServlet("/DiaryServlet")
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//String userId = ((User)session.getAttribute("id")).getId();
		String userId = "aaaaa";
		DiaryDAO dDAO = new DiaryDAO();
		Diary param = new Diary();
		//ユーザーid,開始日付、終了日付をセッターメソッドで格納する
		param.setStartDate("2021-01-06");
		param.setEndDate("2021-01-12");
		param.setUserId(userId);

		List<Diary> diaryList = dDAO.selectDiary(param);
		System.out.println(diaryList);
		System.out.println(diaryList.size());
		for (int i = 0; i< diaryList.size(); i++) {
			System.out.println(diaryList.get(i).getDate());
			System.out.println(diaryList.get(i).getDateStr());
		}

		//リクエストスコープに写真のデータを格納する
		request.setAttribute("diaryList", diaryList);
		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diary.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		//☆のとここれであってる？？
		request.setCharacterEncoding("UTF-8");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		HttpSession session = request.getSession();
		//String userId = ((User)session.getAttribute("id")).getId();
		String userId = "aaaaa";

		//今取ってきた始まりの日と終わりの日をdaoに渡して日記のリストが返される。それをスコープに入れる

		//★↑日付のみが条件だと、「誰の」日記か判定出来ないのでユーザーID情報も必要ですね！
		//	今回のシステムだと、セッションスコープにユーザー情報が入る事になるので、そこからユーザーIDを取り出してDaoに渡しましょう！

		DiaryDAO dDAO = new DiaryDAO();
		Diary param = new Diary();
		//ユーザーid,開始日付、終了日付をセッターメソッドで格納する
		param.setUserId(userId);
		param.setStartDate(startDate);
		param.setEndDate(endDate);

		List<Diary> diaryList = dDAO.selectDiary(param);
		System.out.println(diaryList);
		System.out.println(diaryList.size());
		for (int i = 0; i< diaryList.size(); i++) {
			System.out.println(diaryList.get(i).getDate());
			System.out.println(diaryList.get(i).getDateStr());
		}

		//リクエストスコープに写真のデータを格納する
		request.setAttribute("diaryList", diaryList);

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diary.jsp");
		dispatcher.forward(request, response);

	}
}
