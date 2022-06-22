package test;

import java.util.List;

import dao.ItemDAO;
import model.Item;
import model.RegistInf;

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
		// insert()のテスト
		System.out.println("---------- insert(Itemテーブル)のテスト ----------");
		//Item insRec = new Item(800,"xxxxx", 3, 0, 1, 1, ".png");
		RegistInf reg = new RegistInf("on", null, null, null, null, null, null, null, null, null, "on", "on",
				null, null, null, null, null, null, null, null, "on", "on", null, "on", null, "tops");
		String id = "aaaaa";
		String photo = "abcdefgh.png";
		if (dao.insert(reg, id,  photo)) {
			System.out.println("登録成功！");
			/*
			List<Item> itemList2 = dao.select(reg);
			for (Item item : itemList2) {
				System.out.println("id：" + item.getId());
				System.out.println("userId：" + item.getUserId());
				System.out.println("partsCode：" + item.getPartsCode());
				System.out.println("pattern：" + item.getPattern());
				System.out.println("rain：" + item.getRain());
				System.out.println("wind：" + item.getWind());
				System.out.println("photo：" + item.getPhoto());
				System.out.println();
			}*/
		}
		else {
			System.out.println("登録失敗！");
		}
/*
		System.out.println("---------- insert(Item_seasonテーブル)のテスト ----------");
		ItemSeason insRec2 = new ItemSeason(800, 3, 0, 1);
		if (dao.insert(insRec2)) {
			System.out.println("登録成功！");
			/*
			List<ItemSeason> itemList3 = dao.select(insRec2);
			for (ItemSeason itemSeason : itemList3) {
				System.out.println("id：" + itemSeason.getId());
				System.out.println("itemid：" + itemSeason.getItemId());
				System.out.println("department：" + itemSeason.getCode());
				System.out.println("position：" + itemSeason.getCheck());
				System.out.println();
			}
		}
		else {
			System.out.println("登録失敗！");
		}

		System.out.println("---------- insert(Item_colorテーブル)のテスト ----------");
		ItemColor insRec3 = new ItemColor(800, 3, 0, 1);
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<ItemColor> itemList4 = dao.select(insRec);
			for (ItemColor itemColor : itemList4) {
				System.out.println("id：" + itemColor.getId());
				System.out.println("itemid：" + itemColor.getItemId());
				System.out.println("department：" + itemColor.getCode());
				System.out.println("position：" + itemColor.getCheck());
				System.out.println();
			}
		}
		else {
			System.out.println("登録失敗！");
		}*/

		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		if (dao.delete("73")) {
			System.out.println("削除成功！");
		}
		else {
			System.out.println("削除失敗！");
		}
	}

}


