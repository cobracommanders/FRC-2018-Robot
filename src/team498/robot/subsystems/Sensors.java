package team498.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensors extends Subsystem {

	public DigitalInput limitSwitch;
	private static Sensors sensors = null;
	
	public static Sensors getSensors() {
		sensors = sensors == null ? new Sensors() : sensors;
		return sensors;
	}
	
	private Sensors() {
		this.limitSwitch =  new DigitalInput(0);
	}

    public void initDefaultCommand() {
        
    }
}

