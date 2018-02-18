package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {

	private Drivetrain drivetrain;
	private double moveValue;
	private double desiredDistance;

	public AutoDrive(double moveValue, double desiredDistance) {
		super("AutoDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());

		this.moveValue = moveValue;
		this.desiredDistance = desiredDistance;
	}

	protected void initialize() {
		drivetrain.resetEncoders();
		drivetrain.resetGyro();
	}

	protected void execute() {
		drivetrain.autoDrive(moveValue, 0);
	}

	protected boolean isFinished() {
		return Math.abs(drivetrain.getDistance()) >= Math.abs(desiredDistance);
	}

	protected void end() {
		drivetrain.manualDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
