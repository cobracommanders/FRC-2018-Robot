package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class BumperButton extends Subsystem {

	private static BumperButton instance = null;

	public static BumperButton getButton() {
		instance = instance == null ? new BumperButton() : instance;
		return instance;
	}

	private DigitalInput button = new DigitalInput(0);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean get() {
		return !button.get();
	}
}
