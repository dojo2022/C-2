package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DiaryDAO;
import dao.WeatherForecastDAO;
import model.Diary;
import model.User;
import model.WeatherForecast;

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
		String userId = ((User)session.getAttribute("id")).getId();
		//String userId = "aaaaa";
		DiaryDAO dDAO = new DiaryDAO();
		//もし今日の日記がまだなければ追加
		WeatherForecast weather = (WeatherForecast) session.getAttribute("todayWeather");
		if (weather == null) {
			WeatherForecastDAO wDao = new WeatherForecastDAO();
			weather = wDao.todayWeather();
			session.setAttribute("todayWeather", weather);
		}
		//表示
		//System.out.println("今日の天気" + weather.getWeatherCode());
		//boolean戻り値
		boolean b = dDAO.insertTodayDiary(weather, userId);
		System.out.println("b");

		Diary param = new Diary();

		//ユーザーid,開始日付、終了日付をセッターメソッドで格納する
		/*
		param.setStartDate("2022-05-30");
		param.setEndDate("2022-06-04");
		*/
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sqlFormat.format(cal.getTime());
		param.setEndDate(endDate);
		cal.add(Calendar.DATE, -7);
		String startDate = sqlFormat.format(cal.getTime());
		param.setStartDate(startDate);
		param.setUserId(userId);
		session.setAttribute("startDate", startDate);
		session.setAttribute("endDate", endDate);

		List<Diary> diaryList = dDAO.selectDiary(param);
		//System.out.println(diaryList);
		//System.out.println(diaryList.size());
		/*
		for (int i = 0; i< diaryList.size(); i++) {
			System.out.println(diaryList.get(i).getDate());
			System.out.println(diaryList.get(i).getDateStr());
		}
		*/

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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/coordinator/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		//System.out.println("diaryServlet確認" + startDate);

		String userId = ((User)session.getAttribute("id")).getId();
		//String userId = "aaaaa";

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
		//System.out.println(diaryList);
		//System.out.println(diaryList.size());
		/*
		for (int i = 0; i < diaryList.size(); i++) {
			System.out.println(diaryList.get(i).getDate());
			System.out.println(diaryList.get(i).getDateStr());
		}
		*/

		//リクエストスコープに指定した範囲の日記のリストを格納する
		request.setAttribute("diaryList", diaryList);
		session.setAttribute("startDate", startDate);
		session.setAttribute("endDate", endDate);

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diary.jsp");
		dispatcher.forward(request, response);

	}
}
