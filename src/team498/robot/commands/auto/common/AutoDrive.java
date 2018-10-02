package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {

	private Drivetrain drivetrain;
	private Timer clock = new Timer();
	private double moveValue;
	private double turnValue;
	private double desiredDistance;
	private double time;

	public AutoDrive(double moveValue, double desiredDistance/*double time*/) {
		super("AutoDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());
		
		this.turnValue = turnValue;
		this.moveValue = moveValue;
		this.desiredDistance = desiredDistance;
		//this.time = time;
	}

	protected void initialize() {
		drivetrain.resetEncoders();
		drivetrain.resetGyro();
	}

	protected void execute() {
		drivetrain.autoDrive(moveValue, .15);
	}
		
	protected boolean isFinished() {
		return Math.abs(drivetrain.getDistance()) >= Math.abs(desiredDistance) /*|| clock.get() >= time*/;
	}

	protected void end() {
		drivetrain.manualDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
