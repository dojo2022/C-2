package model;

public class ItemColor {
	int id;
	int itemId;
	int code;
	int check;
	public ItemColor(int id, int itemId, int code, int check) {
		this.id = id;
		this.itemId = itemId;
		this.code = code;
		this.check = check;
	}
	public ItemColor() {
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
