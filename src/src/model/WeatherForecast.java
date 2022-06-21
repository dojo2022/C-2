package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherForecast {
	int weatherCode;
	double rain; //1時間あたり
	double wind;
	double highestTemperature;
	double lowestTemperature;
	String dateStr;
	Date date;

	public WeatherForecast(int weatherCode, double rain, double wind, double highestTemperature,
			double lowestTemperature, String dateStr, Date date) {
		super();
		this.weatherCode = weatherCode;
		this.rain = rain;
		this.wind = wind;
		this.highestTemperature = highestTemperature;
		this.lowestTemperature = lowestTemperature;
		this.dateStr = dateStr;
		this.date = date;
	}
	public WeatherForecast(int weatherCode, double rain, double wind, double highestTemperature,
			double lowestTemperature, Date date) {
		super();
		this.weatherCode = weatherCode;
		this.rain = rain;
		this.wind = wind;
		this.highestTemperature = highestTemperature;
		this.lowestTemperature = lowestTemperature;
		this.dateStr = (Integer.parseInt(new SimpleDateFormat("MM").format(date))) + "/" + (Integer.parseInt(new SimpleDateFormat("dd").format(date)));
		this.date = date;


	}
	public WeatherForecast(int weatherCode, double rain, double wind, double highestTemperature,
			double lowestTemperature) {
		super();
		this.weatherCode = weatherCode;
		this.rain = rain;
		this.wind = wind;
		this.highestTemperature = highestTemperature;
		this.lowestTemperature = lowestTemperature;
		this.dateStr = null;
		this.date = null;
	}
	public WeatherForecast() {
		this(-10000, -10000.0, -10000.0, -10000.0, -10000.0);
	}
	public int getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(int weatherCode) {
		this.weatherCode = weatherCode;
	}
	public double getRain() {
		return rain;
	}
	public void setRain(double rain) {
		this.rain = rain;
	}
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
	public double getHighestTemperature() {
		return highestTemperature;
	}
	public void setHighestTemperature(double highestTemperature) {
		this.highestTemperature = highestTemperature;
	}
	public double getLowestTemperature() {
		return lowestTemperature;
	}
	public void setLowestTemperature(double lowestTemperature) {
		this.lowestTemperature = lowestTemperature;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
