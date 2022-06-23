package test;

import java.util.List;

import dao.WeatherForecastDAO;
import model.Item;

public class RecommendTest {

	public static void main(String[] args) {
		WeatherForecastDAO wDao = new WeatherForecastDAO();
		List<Item> itemList = wDao.recommendCoordinate("aaaaa");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(i + "こめ");
			if (itemList.get(i) == null) {
				System.out.println("なし");
			} else {
				System.out.println("id" + itemList.get(i).getId());
				System.out.println("パーツコード" + itemList.get(i).getPartsCode());
			}
			System.out.println();
		}
	}

}
