package model;
import java.io.Serializable;

public class Result implements Serializable {
	boolean result;
	public Result(boolean result) {
		super();
		this.result = result;
	}
	public Result() {
		this(false);
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
}
