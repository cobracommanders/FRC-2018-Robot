package team498.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;

/**
 *
 */
public class Gyro extends Subsystem {
	
	private static Gyro gyro = null;
	
	public static Gyro getGyro() {
		gyro = gyro == null ? new Gyro() : gyro;
		return gyro;
	}	
	
	private ADXRS450_Gyro sensor = new ADXRS450_Gyro();
	
	public double getAngle() {
		return sensor.getAngle();
	}
	
	public void resetAngle() {
		sensor.reset();
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.GyroAngle, sensor.getAngle());
	}
	
    public void initDefaultCommand() {
        
    }
}

