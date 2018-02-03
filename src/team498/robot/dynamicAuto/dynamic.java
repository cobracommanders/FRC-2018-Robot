package team498.robot.dynamicAuto;

public class dynamic {
	public String button;
	public double axisVal;
	public boolean buttonVal;
	public double timeStamp;

	public dynamic(double timeStamp, String button, double value) {
		this.button = button;
		this.timeStamp = timeStamp;
		axisVal = value;
	}

	public dynamic(double timeStamp, String button, boolean value) {
		this.button = button;
		this.timeStamp = timeStamp;
		buttonVal = value;
	}

	public dynamic(double timeStamp, String button, double axisVal, boolean buttonVal) {
		this.button = button;
		this.timeStamp = timeStamp;
		this.buttonVal = buttonVal;
		this.axisVal = axisVal;
	}
}
