package model;
public class Season {
    int code;
    String name;
    int upperlimitTemperature;
    int lowerlimitTemperature;
    int startMonth;
    int endMonth;
    public Season(int code, String name, int upperlimitTemperature, int lowerlimitTemperature, int startMonth,
            int endMonth) {
        super();
        this.code = code;
        this.name = name;
        this.upperlimitTemperature = upperlimitTemperature;
        this.lowerlimitTemperature = lowerlimitTemperature;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }
    public Season() {
        this(-10000,null,-10000,-10000,-10000,-10000);
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
    public int getUpperlimitTemperature() {
        return upperlimitTemperature;
    }
    public void setUpperlimitTemperature(int upperlimitTemperature) {
        this.upperlimitTemperature = upperlimitTemperature;
    }
    public int getLowerlimitTemperature() {
        return lowerlimitTemperature;
    }
    public void setLowerlimitTemperature(int lowerlimitTemperature) {
        this.lowerlimitTemperature = lowerlimitTemperature;
    }
    public int getStartMonth() {
        return startMonth;
    }
    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }
    public int getEndMonth() {
        return endMonth;
    }
    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }
}