package team498.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Accelerometer;
import team498.robot.subsystems.HapticFeedback;

/**
 *
 */
public class MonitorRobotContact extends Command {

	public double rumblePower;

	private Accelerometer accelerometer;
	private HapticFeedback hapticFeedback;
	
	private Timer timer;

	public MonitorRobotContact() {
		requires(this.accelerometer = Accelerometer.getAccelerometer());
		requires(this.hapticFeedback = HapticFeedback.getHapticFeedback());
		timer = new Timer();
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
			timer.start();
		}
		
		if(timer.get() > 1) {
			rumblePower = 0;
			timer.reset();
		}
		this.hapticFeedback.Rumble(rumblePower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("End!");
		this.hapticFeedback.Rumble(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
