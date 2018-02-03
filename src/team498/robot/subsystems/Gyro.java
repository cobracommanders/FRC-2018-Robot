package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;

public class Gyro extends Subsystem {

	private static Gyro gyro = null;

	public static Gyro getGyro() {
		gyro = gyro == null ? new Gyro() : gyro;
		return gyro;
	}

	private ADIS16448_IMU sensor = new ADIS16448_IMU();

	public double getAngle() {
		return sensor.getAngle();
	}

	public double getAngleX() {
		return sensor.getAngleX();

	}

	public double getAngleY() {
		return sensor.getAngleY();

	}

	public double getAngleZ() {
		return sensor.getAngleZ();

	}

	public void reset() {
		sensor.reset();
	}

	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.GyroAngle, sensor.getAngle());
		SmartDashboard.putNumber(Dashboard.GyroAngleX, sensor.getAngleX());
		SmartDashboard.putNumber(Dashboard.GyroAngleY, sensor.getAngleY());
		SmartDashboard.putNumber(Dashboard.GyroAngleZ, sensor.getAngleZ());
	}

	public void initDefaultCommand() {

	}
}
