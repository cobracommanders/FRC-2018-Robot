package team498.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.commands.MonitorRobotContact;

/**
 *
 */
public class Accelerometer extends Subsystem {
	public BuiltInAccelerometer accel;
	private static Accelerometer accelerometer = null;

	public static Accelerometer getAccelerometer() {
		accelerometer = accelerometer == null ? new Accelerometer() : accelerometer;
		return accelerometer;
	}

	public Accelerometer() {
		accel = new BuiltInAccelerometer();
	}

	public double getX() {
		return accel.getX();
	}

	public void updateDashboard() {
		System.out.println("Accelorometer Dashboard");
		SmartDashboard.putNumber(Dashboard.AccelerometerX, accel.getX());
		SmartDashboard.putNumber(Dashboard.AccelerometerY, accel.getY());
		SmartDashboard.putNumber(Dashboard.AccelerometerZ, accel.getZ());
		SmartDashboard.putData("Everything Accelorometer", accel);

	}

	public void initDefaultCommand() {

	}
}
