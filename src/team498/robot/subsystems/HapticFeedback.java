package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Controller;
import team498.robot.Mappings;
import team498.robot.Operator;


public class HapticFeedback extends Subsystem {

	private static HapticFeedback hapticFeedback = null;
	public static HapticFeedback getHapticFeedback() {
		hapticFeedback = hapticFeedback == null ? new HapticFeedback() : hapticFeedback;
		return hapticFeedback;
	}
	
	private Operator operator = Operator.getOperator();

	private HapticFeedback() {		
	}

	public void Rumble(double rumblePower) {
		// Finish rumble encapsulation, with left and right seperation
		operator.controller.setRumble(rumblePower);
	}

	public void Rumble(double leftPower, double rightPower) {
		operator.controller.setRumble(leftPower, rightPower);
	}

	public void initDefaultCommand() {

	}
}
