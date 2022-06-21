package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary {
	int id;
	Date date;
	String startDate;
	String endDate;
	String userId;
	String note;
	String photo;
	int weatherCode;
	double maxTemperature;
	double minTemperature;
	double windSpeed;
	double amountOfRain;
	String dateStr;

	public Diary(int id, Date date, String startDate, String endDate, String userId, String note, String photo,
			int weatherCode, double maxTemperature, double minTemperature, double windSpeed, double amountOfRain,
			String dateStr) {
		super();
		this.id = id;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.note = note;
		this.photo = photo;
		this.weatherCode = weatherCode;
		this.maxTemperature = maxTemperature;
		this.minTemperature = minTemperature;
		this.windSpeed = windSpeed;
		this.amountOfRain = amountOfRain;
		this.dateStr = dateStr;
	}
	public Diary(int id, Date date, String startDate, String endDate, String userId, String note, String photo, int weatherCode, double maxTemperature,
			double minTemperature, double windSpeed, double amountOfRain) {
		super();
		this.id = id;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.note = note;
		this.photo = photo;
		this.weatherCode = weatherCode;
		this.maxTemperature = maxTemperature;
		this.minTemperature = minTemperature;
		this.windSpeed = windSpeed;
		this.amountOfRain = amountOfRain;
		if (date != null) {
			this.dateStr = (Integer.parseInt(new SimpleDateFormat("MM").format(date))) + "/" + (Integer.parseInt(new SimpleDateFormat("dd").format(date)));
		} else {
			this.dateStr = null;
		}
	}
	public Diary() {
		this(-10000, null, null, null, null, null, null, -10000, -10000.0, -10000.0, -10000.0, -10000.0);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(int weatherCode) {
		this.weatherCode = weatherCode;
	}
	public double getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public double getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public double getAmountOfRain() {
		return amountOfRain;
	}
	public void setAmountOfRain(double amountOfRain) {
		this.amountOfRain = amountOfRain;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}