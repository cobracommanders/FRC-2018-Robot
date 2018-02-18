package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Drivetrain;

/**
 * Toggles turbo mode
 * 
 */
public class ToggleTurbo extends InstantCommand {

	private Drivetrain drivetrain;

	public ToggleTurbo() {
		super("ToggleTurbo");
		requires(drivetrain = Drivetrain.getDrivetrain());
	}

	// Called once when the command executes
	protected void initialize() {
		drivetrain.toggleTurbo();
	}
}
