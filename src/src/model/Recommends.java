package model;
import java.util.Date;

public class Recommends {
	int outer;
	int jacket;
	int tops;
	int skirt;
	int pants;
	int shoes;
	Date date;
	String userId;
	int id;
	public Recommends(int outer, int jacket, int tops, int skirt, int pants, int shoes, Date date, String userId,
			int id) {
		super();
		this.outer = outer;
		this.jacket = jacket;
		this.tops = tops;
		this.skirt = skirt;
		this.pants = pants;
		this.shoes = shoes;
		this.date = date;
		this.userId = userId;
		this.id = id;
	}
	public Recommends() {
		this(-10000, -10000, -10000, -10000, -10000, -10000, null, null, -10000);
	}
	public int getOuter() {
		return outer;
	}
	public void setOuter(int outer) {
		this.outer = outer;
	}
	public int getJacket() {
		return jacket;
	}
	public void setJacket(int jacket) {
		this.jacket = jacket;
	}
	public int getTops() {
		return tops;
	}
	public void setTops(int tops) {
		this.tops = tops;
	}
	public int getSkirt() {
		return skirt;
	}
	public void setSkirt(int skirt) {
		this.skirt = skirt;
	}
	public int getPants() {
		return pants;
	}
	public void setPants(int pants) {
		this.pants = pants;
	}
	public int getShoes() {
		return shoes;
	}
	public void setShoes(int shoes) {
		this.shoes = shoes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}