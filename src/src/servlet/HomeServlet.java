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
import dao.WeatherForecastDAO;
import model.Diary;
import model.WeatherForecast;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeatherForecastDAO wDAO = new WeatherForecastDAO();
		// セッションスコープに一日天気予報のデータを格納する
		List<WeatherForecast> oneDayWeatherList = wDAO.oneDayWeatherForecast();
		HttpSession session = request.getSession();
		session.setAttribute("oneDayWeather", oneDayWeatherList);

		// セッションスコープに週間天気予報のデータを格納する
		List<WeatherForecast> weeklyWeatherList = wDAO.weeklyWeatherForecast();
		session.setAttribute("weeklyWeather", weeklyWeatherList);

		//写真３枚取ってくる
		DiaryDAO dDAO = new DiaryDAO();
		//String型のidを取ってくる
		//String id =((User)session.getAttribute("id")).getId();
		String id = "aaaaa";
		List<Diary> diaryList= dDAO.search3Photos(id);
		for (int i = 0; i < diaryList.size(); i++) {
			System.out.println(diaryList.get(i).getPhoto());
			System.out.println(diaryList.get(i).getDate());
		}
		//リクエストスコープに写真のデータを格納する
		request.setAttribute("diaryList", diaryList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
