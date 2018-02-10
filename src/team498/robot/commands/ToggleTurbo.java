package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Drivetrain;

/**
 * Toggles turbo mode
 * 
 * @author Micah Neitz
 */
public class ToggleTurbo extends InstantCommand {

	private Drivetrain drivetrain;

	public ToggleTurbo() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain = Drivetrain.getDrivetrain());
	}

	// Called once when the command executes
	protected void initialize() {
		drivetrain.toggleTurbo();
	}

}
