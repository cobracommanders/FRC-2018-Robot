package team498.robot.triggers;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;
import team498.robot.commands.RumbleOnCollision;

/**
 *
 */
public class CollisionDetector extends Trigger {

	private BuiltInAccelerometer accelerometer;

	RumbleOnCollision collisionCommand;

	public CollisionDetector() {
		collisionCommand = new RumbleOnCollision();
		accelerometer = new BuiltInAccelerometer();
		this.whenActive(collisionCommand);
	}

	public boolean get() {
		if (Math.abs(accelerometer.getX()) > Mappings.CollisionThreshold) {
			collisionCommand.setAccelerometerData(accelerometer.getX(), accelerometer.getY(), accelerometer.getZ());
			return true;
		}
		
		if (Math.abs(accelerometer.getZ()) > Mappings.CollisionThreshold) {
			collisionCommand.setAccelerometerData(accelerometer.getX(), accelerometer.getY(), accelerometer.getZ());
			return true;
		}
		return false;
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.AccelerometerX, accelerometer.getX());
		SmartDashboard.putNumber(Dashboard.AccelerometerY, accelerometer.getY());
		SmartDashboard.putNumber(Dashboard.AccelerometerZ, accelerometer.getZ());
	}

}