package model;

public class ItemSeason {
	int id;
	int itemId;
	int code;
	int check;
	public ItemSeason(int id, int itemId, int code, int check) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.code = code;
		this.check = check;
	}
	public ItemSeason() {
		this(-10000, -10000, -10000, -10000);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
}
