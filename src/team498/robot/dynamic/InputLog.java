package team498.robot.dynamic;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Logs input to prepare for string compile. Under the hood class, should never
 * be used
 * 
 * @version 1.0
 * 
 * @author Micah Neitz<br/>
 *         Team 498
 *
 */
final class InputLog {

	private String _name;
	private double _doubleValue;
	private double _time;
	private boolean _boolValue;
	private boolean _isButton;

	public String Log() {
		DecimalFormat df = new DecimalFormat("0.0####");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return String.format(";%s_%s_%s_%s", _name, _isButton ? _boolValue ? "1" : "0" : df.format(_doubleValue),
				_isButton ? "1" : "0", _time);
	}

	public String ToString() {
		return Log();
	}

	public double GetDouble() {
		return _doubleValue;
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

	public String GetName() {
		return _name;
	}

	public InputLog(String name, double value, double time) {
		this._name = name;
		this._doubleValue = value;
		this._time = time;
		this._boolValue = false;
		this._isButton = false;
	}

	public InputLog(String name, String value, String isButton, String time) throws IllegalArgumentException {
		boolean valid = true;
		try {
			Double.parseDouble(value);
			Double.parseDouble(time);
		} catch (NumberFormatException e) {
			valid = false;
		}
		if (!valid)
			throw new IllegalArgumentException("Value or Time string was invalid!");
		this._name = name;
		this._boolValue = value == "1";
		this._isButton = isButton == "1";
		this._doubleValue = Double.parseDouble(value);
		this._time = Double.parseDouble(time);
	}

	public InputLog(String name, boolean value, double time) {
		this._name = name;
		this._time = time;
		this._boolValue = value;
		this._doubleValue = 0.0;
		this._isButton = true;
	}
}
