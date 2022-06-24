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

import dao.DiaryDAO;
import model.Diary;

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
		request.setCharacterEncoding("UTF-8");

		String s1 = request.getParameter("edit");
		String s2 = request.getParameter("update");
		if (s1 != null) {

			// リクエストパラメータを取得する
			String diaryId = request.getParameter("diary_id");

			System.out.println("日記id:" + diaryId);

			DiaryDAO dDao = new DiaryDAO();
			Diary diary = dDao.search(diaryId);

			//dをスコープに入れる
			request.setAttribute("diary", diary);

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/diaryEdit.jsp");
			dispatcher.forward(request, response);

		} else if (s2 != null) {
			//id
			HttpSession session = request.getSession();
			//String userId = ((User)session.getAttribute("id")).getId();
			String userId = "aaaaa";
			// リクエストパラメータを取得する
			Part part = request.getPart("IMAGE"); // getPartで取得
			String photo = null;
			String fileName = this.getFileName(part);
			System.out.println(fileName);
			//System.out.println("part:" + part + "file :"  +this.getFileName(part));
			if (fileName != null && !(fileName.equals(""))) {
				photo = userId + System.currentTimeMillis() + "." + this.getExtension(fileName);
				part.write(photo);
				photo = "\\photo\\" + photo;
			}
			String diaryId = request.getParameter("diary_id");
			String note = request.getParameter("note");

			String startDate = (String) session.getAttribute("startDate");
			String endDate = (String) session.getAttribute("endDate");
			//System.out.println(" 確認" + startDate + endDate);

			System.out.println("1" + photo);

			//dao
			DiaryDAO dDao = new DiaryDAO();
			Diary diary = new Diary();

			//diaryオブジェクトにデータを格納する。
			diary.setId(Integer.parseInt(diaryId));
			diary.setPhoto(photo);
			diary.setNote(note);
			//System.out.println("2"+ diary.getNote());

			boolean ret = dDao.update(diary);

			//リクエストスコープに保存する
			request.setAttribute("diary", diary);

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
			for (int i = 0; i< diaryList.size(); i++) {
				System.out.println(diaryList.get(i).getDate());
				System.out.println(diaryList.get(i).getDateStr());
			}*/

			//リクエストスコープに指定した範囲の日記のリストを格納する
			request.setAttribute("diaryList", diaryList);

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