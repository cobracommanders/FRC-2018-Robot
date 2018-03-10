package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;

/**
 * Toggles the flipper arms on the intake
 */
public class ToggleArmRestriction extends InstantCommand {

	private Arm arm;

	public ToggleArmRestriction() {
		super("ToggleArmRestriction");
		requires(this.arm = Arm.getArm());

	}

	// Called once when the command executes
	protected void initialize() {
		arm.ToggleArmRestrict();
	}
}
