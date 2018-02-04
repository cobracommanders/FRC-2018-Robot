package team498.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;

/**
 *
 */
public class Accelerometer extends Subsystem {
	public AnalogAccelerometer accel;
	private static Accelerometer accelerometer = null;
	public static Accelerometer getAccelerometer() {
		accelerometer = accelerometer == null ? new Accelerometer() : accelerometer;
		return accelerometer;
	}	
	
	public Accelerometer() {
		this.accel = new AnalogAccelerometer(Mappings.AnalogInput);
		//test this 
		accel.setSensitivity(.018); 
		accel.setZero(2.5);
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.Acceleration, accel.getAcceleration());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

