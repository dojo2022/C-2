package model;

public class ColorSeason {
	boolean spring;
	boolean summer;
	boolean autumn;
	boolean winter;
	boolean white;
	boolean black;
	boolean grey;
	boolean beige;
	boolean red;
	boolean blue;
	boolean green;
	boolean yellow;
	boolean other;
	public ColorSeason(boolean spring, boolean summer, boolean autumn, boolean winter, boolean white, boolean black,
			boolean grey, boolean beige, boolean red, boolean blue, boolean green, boolean yellow, boolean other) {
		super();
		this.spring = spring;
		this.summer = summer;
		this.autumn = autumn;
		this.winter = winter;
		this.white = white;
		this.black = black;
		this.grey = grey;
		this.beige = beige;
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.yellow = yellow;
		this.other = other;
	}
	public ColorSeason() {
		this(false, false, false, false, false, false, false, false, false, false, false, false, false);
	}
	public boolean getSpring() {
		return spring;
	}
	public void setSpring(boolean spring) {
		this.spring = spring;
	}
	public boolean getSummer() {
		return summer;
	}
	public void setSummer(boolean summer) {
		this.summer = summer;
	}
	public boolean getAutumn() {
		return autumn;
	}
	public void setAutumn(boolean autumn) {
		this.autumn = autumn;
	}
	public boolean getWinter() {
		return winter;
	}
	public void setWinter(boolean winter) {
		this.winter = winter;
	}
	public boolean getWhite() {
		return white;
	}
	public void setWhite(boolean white) {
		this.white = white;
	}
	public boolean getBlack() {
		return black;
	}
	public void setBlack(boolean black) {
		this.black = black;
	}
	public boolean getGrey() {
		return grey;
	}
	public void setGrey(boolean grey) {
		this.grey = grey;
	}
	public boolean getBeige() {
		return beige;
	}
	public void setBeige(boolean beige) {
		this.beige = beige;
	}
	public boolean getRed() {
		return red;
	}
	public void setRed(boolean red) {
		this.red = red;
	}
	public boolean getBlue() {
		return blue;
	}
	public void setBlue(boolean blue) {
		this.blue = blue;
	}
	public boolean getGreen() {
		return green;
	}
	public void setGreen(boolean green) {
		this.green = green;
	}
	public boolean getYellow() {
		return yellow;
	}
	public void setYellow(boolean yellow) {
		this.yellow = yellow;
	}
	public boolean getOther() {
		return other;
	}
	public void setOther(boolean other) {
		this.other = other;
	}

}
