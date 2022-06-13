package model;

public class Item {
	int id;
	String userId;
	int partsCode;
	int pattern;
	int rain;
	int wind;
	int photo;
	public Item(int id, String userId, int partsCode, int pattern, int rain, int wind, int photo) {
		super();
		this.id = id;
		this.userId = userId;
		this.partsCode = partsCode;
		this.pattern = pattern;
		this.rain = rain;
		this.wind = wind;
		this.photo = photo;
	}
	public Item() {
		this(-10000, null, -10000, -10000, -10000, -10000, -10000);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPartsCode() {
		return partsCode;
	}
	public void setPartsCode(int partsCode) {
		this.partsCode = partsCode;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public int getRain() {
		return rain;
	}
	public void setRain(int rain) {
		this.rain = rain;
	}
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	public int getPhoto() {
		return photo;
	}
	public void setPhoto(int photo) {
		this.photo = photo;
	}

}
