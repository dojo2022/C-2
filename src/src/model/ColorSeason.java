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
	public boolean isSpring() {
		return spring;
	}
	public void setSpring(boolean spring) {
		this.spring = spring;
	}
	public boolean isSummer() {
		return summer;
	}
	public void setSummer(boolean summer) {
		this.summer = summer;
	}
	public boolean isAutumn() {
		return autumn;
	}
	public void setAutumn(boolean autumn) {
		this.autumn = autumn;
	}
	public boolean isWinter() {
		return winter;
	}
	public void setWinter(boolean winter) {
		this.winter = winter;
	}
	public boolean isWhite() {
		return white;
	}
	public void setWhite(boolean white) {
		this.white = white;
	}
	public boolean isBlack() {
		return black;
	}
	public void setBlack(boolean black) {
		this.black = black;
	}
	public boolean isGrey() {
		return grey;
	}
	public void setGrey(boolean grey) {
		this.grey = grey;
	}
	public boolean isBeige() {
		return beige;
	}
	public void setBeige(boolean beige) {
		this.beige = beige;
	}
	public boolean isRed() {
		return red;
	}
	public void setRed(boolean red) {
		this.red = red;
	}
	public boolean isBlue() {
		return blue;
	}
	public void setBlue(boolean blue) {
		this.blue = blue;
	}
	public boolean isGreen() {
		return green;
	}
	public void setGreen(boolean green) {
		this.green = green;
	}
	public boolean isYellow() {
		return yellow;
	}
	public void setYellow(boolean yellow) {
		this.yellow = yellow;
	}
	public boolean isOther() {
		return other;
	}
	public void setOther(boolean other) {
		this.other = other;
	}
}
