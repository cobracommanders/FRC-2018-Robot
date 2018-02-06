package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import team498.robot.Controller;
import team498.robot.subsystems.Accelerometer;
import team498.robot.subsystems.HapticFeedback;

/**
 *
 */
public class MonitorRobotContact extends TimedCommand {
	private Controller controller;

	public double rumblePower;

	private Accelerometer accelerometer;
	private HapticFeedback hapticFeedback;

	public MonitorRobotContact(String MonitorRobotContact, double time ) {
		super("MonitorRobotContact", time);
		requires(this.accelerometer = Accelerometer.getAccelerometer());
		requires(this.hapticFeedback = HapticFeedback.getHapticFeedback());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// TODO When Accelerometer says to , rumble the correct amount in the correct
		// places
		if (Math.abs(accelerometer.getX()) > .3) {
			rumblePower = 1;
		}
		this.controller.setRumble(rumblePower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		this.controller.setRumble(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
