package test;

import java.util.List;

import dao.ItemDAO;
import model.Item;

public abstract class ItemDAOTest {
	public static void main(String[] args) {
		ItemDAO dao = new ItemDAO();

		// select()のテスト
		System.out.println("---------- select()のテスト ----------");
		//List<Item> itemList = dao.searchParts(new Item(0 , "", 0, 0, 0, 0, ""));
		List<Item> itemList = dao.searchParts("aaaaa", 1);
		for (Item item : itemList) {
			System.out.println(item.getId());
			System.out.println(item.getUserId());
			System.out.println(item.getPartsCode());
			System.out.println(item.getPattern());
			System.out.println(item.getRain());
			System.out.println(item.getWind());
			System.out.println(item.getPhoto());
			System.out.println();
		}
	}

}
