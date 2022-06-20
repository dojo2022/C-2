package test;

import java.util.List;

import dao.DiaryDAO;
import model.Diary;

public class DiaryDAOTest {
	public static void main(String[] args) {
		DiaryDAO dao = new DiaryDAO();

		// select()のテスト
		System.out.println("---------- select()のテスト ----------");
		Diary d1 = new Diary(0, null, "2021-01-06", "2021-01-12", "aaaaa", null, null, 0, 0, 0, 0, 0);
		/* 開始日と終了日もdate型だと勘違いしてました
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdFormat.parse("2017-03-02");
		Date date = sdFormat.parse("2017-03-02");
		*/
		List<Diary> diaryList = dao.selectDiary(d1);
		System.out.println(diaryList.size());
		for (Diary diary : diaryList) {
			System.out.println(diary.getId());
			System.out.println(diary.getDate());
			System.out.println(diary.getStartDate());
			System.out.println(diary.getEndDate());
			System.out.println(diary.getUserId());
			System.out.println(diary.getNote());
			System.out.println(diary.getPhoto());
			System.out.println(diary.getWeatherCode());
			System.out.println(diary.getMaxTemperature());
			System.out.println(diary.getMinTemperature());
			System.out.println(diary.getWindSpeed());
			System.out.println(diary.getAmountOfRain());
			System.out.println();
		}
	}
}
