package model;

public class Color {
	int code;
	String name;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public Color() {
		this(-1000000, null); //仮
	}

}
