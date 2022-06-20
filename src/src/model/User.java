package model;
public class User {
	String id;
	String name;
	String password;
	String password2;
	public User(String id, String name, String password,String password2) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.password2 = password2;
	}

	public User() {
		this(null,null,null,null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}