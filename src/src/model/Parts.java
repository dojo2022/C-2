package model;

public class Parts {
	int code;
	String name;
	public Parts(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public Parts() {
		this(-10000, null);
	}
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
}