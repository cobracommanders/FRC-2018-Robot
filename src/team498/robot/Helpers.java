package team498.robot;

public class Helpers {
	public static double normalize (double value, double tolerance ) {
		if(Math.abs(value) <= tolerance ) {
			return 0;
		}
			return value;
	}

}
