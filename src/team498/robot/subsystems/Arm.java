package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Mappings;
import team498.robot.commands.ManualArm;
import team498.robot.commands.ManualIntake;

/**
 *
 */
public class Arm extends Subsystem {
	
	private static Arm arm = null;
	public static Arm getArm() {
		arm = arm == null ? new Arm() : arm;
		return arm;
	}
	
	private Spark motor = new Spark(Mappings.ArmPort);

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualArm());
    }
    public void set(double power) {
    	motor.set(power);
    }
}

