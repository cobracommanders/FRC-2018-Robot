package team498.robot.dynamic;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Logs input to prepare for string compile. Under the hood class, should never
 * be used
 * 
 * @author Micah Neitz<br/>
 * 		Team 498
 *
 */
final class InputLog {
	String name;
	String value;
	String time;
	private double _value;
	private double _time;
	private boolean _boolValue;
	private boolean _isButton;

	public String Log() {
		return String.format(";%s_%s_%s", name, value, time);
	}

	public String ToString() {
		return Log();
	}

	public double GetDouble() {
		return _value;
	}

	public boolean GetBoolean() {
		return _boolValue;
	}

	public double GetTime() {
		return _time;
	}

	public boolean IsButton() {
		return _isButton;
	}

	public InputLog(String name, double value, double time) {
		DecimalFormat df = new DecimalFormat("#.#####");
		df.setRoundingMode(RoundingMode.HALF_UP);
		this.name = name;
		this.value = df.format(value);
		this.time = df.format(value);
		this._time = time;
		this._boolValue = false;
		this._value = value;
		this._isButton = false;
	}

	public InputLog(String name, String value, String time) throws IllegalArgumentException {
		boolean valid = true;
		try {
			Double.parseDouble(value);
			Double.parseDouble(time);
		} catch (NumberFormatException e) {
			valid = false;
		}
		if (!valid)
			throw new IllegalArgumentException("Value or Time string was invalid!");
		this.name = name;
		this.value = value;
		this.time = time;
		this._isButton = value == "1" || value == "0";
		this._boolValue = value == "1";
		this._value = Double.parseDouble(value);
		this._time = Double.parseDouble(time);
	}

	public InputLog(String name, boolean value, double time) {
		DecimalFormat df = new DecimalFormat("#.#####");
		df.setRoundingMode(RoundingMode.HALF_UP);
		this.name = name;
		this.value = value ? "1" : "0";
		this.time = df.format(time);
		this._time = time;
		this._boolValue = value;
		this._value = 0.0;
		this._isButton = true;
	}
}
