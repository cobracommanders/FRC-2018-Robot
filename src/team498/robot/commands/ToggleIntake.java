package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class ToggleIntake extends InstantCommand {

	private Arm arm;
	private double power;

	public ToggleIntake(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("ManualIntake");
		this.power = power;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (arm.getLastLeft() == power) {
			arm.setIntake(0, 0);
		} else {
			arm.setIntake(power, power);
		}
	}
}
