package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class AutoDriveTimed extends Command {
	public double movePower;
	public double time;
	public double turnPower;
	private Drivetrain drivetrain;

	private Timer timer = new Timer();

	public AutoDriveTimed(double movePower, double turnPower, double time) {
		super("AutoDriveTimed");
		requires(this.drivetrain = Drivetrain.getDrivetrain());

		this.movePower = movePower;
		this.time = time;
		this.turnPower = turnPower;
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		drivetrain.manualDrive(movePower, turnPower);
	}

	protected boolean isFinished() {
		return timer.get() >= time;
	}

	protected void end() {
		drivetrain.manualDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
