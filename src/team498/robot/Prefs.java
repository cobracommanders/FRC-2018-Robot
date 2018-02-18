package team498.robot;

import edu.wpi.first.wpilibj.Preferences;

public class Prefs {

	private static Prefs prefs = null;

	public static Prefs getPrefs() {
		prefs = prefs == null ? new Prefs() : prefs;
		return prefs;
	}

	Preferences preferences = Preferences.getInstance();

	public double getPID_P() {
		return Double.parseDouble(preferences.getString("pid_p", "0.0"));
	}

	public double getPID_I() {
		return Double.parseDouble(preferences.getString("pid_i", "0.0"));
	}

	public double getPID_D() {
		return Double.parseDouble(preferences.getString("pid_d", "0.0"));
	}

	public double getRamp_C() {
		return Double.parseDouble(preferences.getString("ramp_c", "0.0"));
	}
}
