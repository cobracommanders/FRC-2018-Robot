package team498.robot;

import java.util.Date;

public class Program {

	public static void main(String[] args) throws java.lang.Exception {
		
		
		double joystick = 1.0;

		LowPassFilter f = new LowPassFilter(2);

		for (int i = 0; i < 100; i++) {

			Thread.sleep(20);
			double result = f.calculateCurt(joystick);
			System.out.print("i:" + i + "   ");
			System.out.println(result);
		}

		joystick = -1.0;
		System.out.println("flip");

		for (int i = 0; i < 100; i++) {

			Thread.sleep(20);
			double result = f.calculateCurt(joystick);
			System.out.print("i:" + i + "   ");
			System.out.println(result);

		}

	}

}

class LowPassFilter {

	private double lastValue = 0;
	private long lastTime = -1;
	private double m_RC;
	
	private double tN = 0;
	private double tN1 = 0;
	private double tN2 = 0;
	private double c = 0.000002;
	private double FtN = 0;
	private double FtN1 = 0;
	private double FtN2 = 0;

	public void reset() {
		lastValue = 0;
		lastTime = -1;
	}

	public LowPassFilter(double RC) {
		m_RC = RC;
	}

	
	public void setRC(double RC) {
		m_RC = RC;
	}

	public double getRC() {
		return m_RC;
	}

	public double calculate(double value) {
		if (lastTime > 0) {
			long currentTime = new Date().getTime();
			double a = currentTime - lastTime;
			a /= (a + m_RC);
			lastTime = currentTime;
			lastValue = a * value + (1 - a) * lastValue;

		} else
			lastTime = new Date().getTime();

		return lastValue;
	}

	public double calculateCurt(double targetValue) {
		long currentTime = new Date().getTime();
		
		tN = (double)currentTime;
		
		if(tN1 == 0) {
			
		} else if (tN2 == 0) {
			
		} else {
			double a = (tN-tN1) * ((c * (tN - tN2)));
			double b = ((FtN1 - FtN2)/(tN1 - tN2)) + FtN1;
			if(FtN1 < targetValue) {
				FtN = (tN-tN1) * ((c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
				if(FtN > targetValue) {
					FtN = targetValue;
				}
			} else if (FtN1 > targetValue) {
				// The difference is the negative sign
				FtN = (tN-tN1) * ((-c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
				//System.out.println("     " + (FtN - FtN1));
				//System.out.println("FtN:" + FtN + " FtN1: " + FtN1 + " FtN2: " + FtN2 + " tN: " + Math.round((tN * 1000)/1000) + " tN1: " + Math.round((tN1 * 1000)/1000) + " tN2: " + Math.round((tN2 * 1000)/1000));
				if(FtN < targetValue) {
					FtN = targetValue;
				}
			} else {
				FtN = targetValue;
			}
			
		} 
		
		
		
		
		FtN2 = FtN1;
		FtN1 = FtN;
		tN2 = tN1;
		tN1 = tN;
		
		
		
		return FtN;
	}

	
	
	
//	double acceleration = 1.0; // modifier for response
//	long lastloop = 0;
//	double currentValue = 0.0;
//
//	public double calculateOther(double desiredValue) {
//
//		if(lastloop == 0) {
//			lastloop = System.currentTimeMillis();
//			return currentValue;
//		}
//		
//		long currentLoop = System.currentTimeMillis();
//		
//		double delta = (double)(currentLoop - lastloop) / 1000;
//		double error = desiredValue - currentValue;
//		//double errorPercent = (desiredValue - currentValue) / desiredValue;
//		
//		System.out.print("delta: " + delta + " - ");
//		
//		currentValue += error * delta * acceleration;
//		
//		//if (errorPercent <= .1) {
//		//	currentValue = desiredValue;
//		//}
//		
//		//lastloop = currentLoop;
//		return currentValue;
//
//	}
	
	
	
}
