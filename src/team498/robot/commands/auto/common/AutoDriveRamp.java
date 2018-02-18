package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

/**
 * 
 * @author Micah Neitz<br/>
 *         Team 498
 *
 */
public class AutoDriveRamp extends Command {

	private Drivetrain drivetrain;
	private double moveValue;
	private double desiredDistance;
	private final double endPower = 0.5;
	private final double startRampDistance = 12;

	public AutoDriveRamp(double moveValue, double desiredDistance) {
		super("AutoDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());

		this.moveValue = moveValue;
		this.desiredDistance = desiredDistance;
	}

	protected void initialize() {
		drivetrain.resetEncoders();
	}

	protected void execute() {
		double dif = desiredDistance - drivetrain.getDistance();
		if (dif > startRampDistance)
			drivetrain.autoDrive(moveValue, 0);
		else
			drivetrain.autoDrive(moveValue * ((moveValue - endPower) - (moveValue - endPower) * (startRampDistance - dif) * (startRampDistance - dif) / (startRampDistance * startRampDistance)), 0);
	}

	protected boolean isFinished() {
		return Math.abs(drivetrain.getDistance()) >= Math.abs(desiredDistance);
	}

	protected void end() {
		drivetrain.autoDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
