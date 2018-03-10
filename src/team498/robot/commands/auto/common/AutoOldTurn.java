package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.ADIS16448_IMU;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class AutoOldTurn extends Command {
	public double gyroGoal;
	public double turnPower;
	private Drivetrain drivetrain;

	private Timer timer = new Timer();

	public AutoOldTurn(double turnPower, double gyroGoal) {
		super("AutoOldTurn");
		requires(this.drivetrain = Drivetrain.getDrivetrain());

		this.gyroGoal = gyroGoal;
		this.turnPower = turnPower;
	}

	protected void initialize() {
		drivetrain.resetGyro();
	}

	protected void execute() {
		drivetrain.manualDrive(0, turnPower);
	}

	protected boolean isFinished() {
		return Math.abs(drivetrain.getAngleX()) >= Math.abs(gyroGoal);
	}

	protected void end() {
		drivetrain.manualDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}