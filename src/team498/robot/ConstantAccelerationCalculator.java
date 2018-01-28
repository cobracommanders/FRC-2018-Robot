package team498.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConstantAccelerationCalculator {
	
	/*private double lastValue = 0;
	private long lastTime = -1;*/
	//private double m_RC;
	private double tN = 0;
	private double tN1 = 0;
	private double tN2 = 0;
	private double c = 0; //change acceleration (was .000002)
	private double FtN = 0;
	private double FtN1 = 0;
	private double FtN2 = 0;
	
	public ConstantAccelerationCalculator(double targetAcceleration) {
		c = targetAcceleration;
	}
	
	public double getNextDataPoint(double targetValue) {
	    	long currentTime = new Date().getTime();
			
			tN = (double)currentTime;
			
			if(tN1 == 0) {
				//nothing
			} else if (tN2 == 0) {
				//nothing
			} else {
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
	
	public void updateDashboard() {
		SmartDashboard.putNumber("c (constant acceleration value): ", c);
		SmartDashboard.putNumber("Difference between two points", (FtN1 - FtN2));
		SmartDashboard.putNumber("FtN: ", FtN);
		SmartDashboard.putNumber("FtN1: " , FtN1);
		SmartDashboard.putNumber("FtN2: " , FtN2);
		SmartDashboard.putNumber("tN: " , Math.round((tN * 1000)/1000));
		SmartDashboard.putNumber("tN1: " , Math.round((tN1 * 1000)/1000));
		SmartDashboard.putNumber("tN2: " , Math.round((tN2 * 1000)/1000));
	}
	
}
