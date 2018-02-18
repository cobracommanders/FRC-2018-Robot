package team498.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;

public class Accelerometer extends Subsystem {
		
	private static Accelerometer accelerometer = null;
	public static Accelerometer getAccelerometer() {
		accelerometer = accelerometer == null ? new Accelerometer() : accelerometer;
		return accelerometer;
	}
	
	private BuiltInAccelerometer accel;

	public Accelerometer() {
		accel = new BuiltInAccelerometer();
	}

	public double getX() {
		return accel.getX();
	}

	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.AccelerometerX, accel.getX());
		SmartDashboard.putNumber(Dashboard.AccelerometerY, accel.getY());
		SmartDashboard.putNumber(Dashboard.AccelerometerZ, accel.getZ());
	}

	public void initDefaultCommand() {
	}
}
