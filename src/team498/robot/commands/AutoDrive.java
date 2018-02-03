package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Sensors;

/**
 *
 */
public class AutoDrive extends Command {

	private Drivetrain drivetrain;
	private double move;
	private double rotate;
	private boolean watchButton;
	private boolean areWeDone = false;

	public AutoDrive(double move, double rotate, boolean watchButton) {
		requires(this.drivetrain = Drivetrain.getDrivetrain());
		this.move = move;
		this.rotate = rotate;
		this.watchButton = watchButton;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (watchButton == true) {
			System.out.println("Switch = " + Sensors.getSensors().limitSwitch.get());
		}
		if (watchButton == false || Sensors.getSensors().limitSwitch.get() == true) {
			this.drivetrain.drive(move, rotate);
		} else {
			end();
			areWeDone = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return areWeDone;
	}

	// Called once after isFinished returns true
	protected void end() {
		this.drivetrain.drive(0, 0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
