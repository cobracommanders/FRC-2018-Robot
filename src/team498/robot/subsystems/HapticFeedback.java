package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Controller;
import team498.robot.Mappings;
import team498.robot.commands.MonitorRobotContact;

public class HapticFeedback extends Subsystem {

	private static HapticFeedback hapticFeedback = null;
	private Controller controller;

	public static HapticFeedback getHapticFeedback() {
		hapticFeedback = hapticFeedback == null ? new HapticFeedback() : hapticFeedback;
		return hapticFeedback;
	}

	private HapticFeedback() {
		controller = new Controller(Mappings.ControllerPort);
	}

	public void Rumble(double rumblePower) {
		// Finish rumble encapsulation, with left and right seperation
		controller.setRumble(rumblePower);
	}

	public void Rumble(double leftPower, double rightPower) {
		controller.setRumble(leftPower, rightPower);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MonitorRobotContact());

	}
}
