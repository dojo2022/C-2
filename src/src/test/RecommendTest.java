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
			System.out.println("アイテムコード" + itemList.get(i).getPartsCode());
			System.out.println();
		}
	}

}
