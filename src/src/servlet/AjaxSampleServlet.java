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

import dao.WeatherForecastDAO;
import model.WeatherForecast;

@WebServlet("/AjaxSampleServlet")
public class AjaxSampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ajaxInOut.jspへフォワードするだけ
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/weatherForecast.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");

		// 送信されたデータの取得
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
		//送られてきたjsonの時間を配列に
		String[] time = new String[itemQuantity];
		String[] temperature = new String[itemQuantity];
		String[] rain = new String[itemQuantity];
		String[] windspeed = new String[itemQuantity];
		String[] weathercode = new String[itemQuantity];
		for (int i = 0; i < itemQuantity; i++) {
			time[i] = request.getParameter("time" + i);
			temperature[i] = request.getParameter("temperature" + i);
			rain[i] = request.getParameter("rain" + i);
			windspeed[i] = request.getParameter("windspeed" + i);
			weathercode[i] = request.getParameter("weathercode" + i);
		}
		//daoに渡してデータベースに天気予報を登録してもらう
		WeatherForecastDAO wDAO = new WeatherForecastDAO();
		wDAO.setWeatherForecastDB(time, temperature, rain, windspeed, weathercode);

		// セッションスコープに一日天気予報のデータを格納する
		List<WeatherForecast> oneDayWeatherList = wDAO.oneDayWeatherForecast();
		HttpSession session = request.getSession();
		session.setAttribute("oneDayWeather", oneDayWeatherList);

		// セッションスコープに一日天気予報のデータを格納する
		List<WeatherForecast> weeklyWeatherList = wDAO.weeklyWeatherForecast();
		session.setAttribute("weeklyWeather", weeklyWeatherList);

		//

		//一日天気予報のデータを表示
		/*
		System.out.println("一日天気予報");
		for (int i = 0; i < oneDayWeatherList.size(); i++) {
			System.out.println("time " + i);
			WeatherForecast w = oneDayWeatherList.get(i);
			System.out.println("WeatherCode:" + w.getWeatherCode());
			System.out.println("Rain:" + w.getRain());
			System.out.println("wind:" + w.getWind());
			System.out.println("HighestTemperature:" + w.getHighestTemperature());
			System.out.println("LowestTemperature:" + w.getLowestTemperature());
		}
		*/

		//週間天気予報のデータを表示
		/*
		System.out.println("週間天気予報");
		for (int i = 0; i < weeklyWeatherList.size(); i++) {
			System.out.println("time " + i);
			WeatherForecast w = weeklyWeatherList.get(i);
			System.out.println("WeatherCode:" + w.getWeatherCode());
			System.out.println("Rain:" + w.getRain());
			System.out.println("wind:" + w.getWind());
			System.out.println("HighestTemperature:" + w.getHighestTemperature());
			System.out.println("LowestTemperature:" + w.getLowestTemperature());
		}
		*/

		//System.out.println("DBset:" + result);
		// 一応表示
		/*
		for (int i = 0; i < time.length; i++) {
			System.out.println(time[i]);
		}
		for (int i = 0; i < temperature.length; i++) {
			System.out.println(temperature[i]);
		}
		for (int i = 0; i < rain.length; i++) {
			System.out.println(rain[i]);
		}
		for (int i = 0; i < windspeed.length; i++) {
			System.out.println(windspeed[i]);
		}
		for (int i = 0; i < weathercode.length; i++) {
			System.out.println(weathercode[i]);
		}
		*/

		/*
		//ArrayListをインスタンス化
		ArrayList<JsonUserBeans> list = new ArrayList<>();

		//適当な値を突っ込む
		for(int i=0;i<5;i++) {
			JsonUserBeans jub = new JsonUserBeans();
			jub.setId(data1);
			jub.setName(data2);
			jub.setData(data3);
			list.add(jub);
		}


		ObjectMapper mapper = new ObjectMapper();
		try {
		    //JavaオブジェクトからJSONに変換
		    String testJson = mapper.writeValueAsString(list);
		    //JSONの出力
		    response.getWriter().write(testJson);
		} catch (JsonProcessingException e) {
		    e.printStackTrace();
		}

		//		//文字コードの設定（めんどいのでコピペでOK）
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		//
		//		//JSPに返却する値を作成する。値はoutの中に格納する
		//		PrintWriter out = response.getWriter();
		//		//outの中に持ってきたデータを連結したものを入れる
		//		//勝手にJSPに渡り、dataという名前で使用することができる
		//		out.print(data1+","+data2+","+data3);
		//
		//        return;
		* */

	}

}
