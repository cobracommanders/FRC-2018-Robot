package team498.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Controller;

/**
 *
 */
public class Rumble extends Command {

	private Timer timer;

	private Controller controller;

	public Rumble(Controller controller) {
		this.controller = controller;
		timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Rumble Command!");
		double rumblePower = 0;
		double temporaryTime = timer.get();
		int currentTime = (int) temporaryTime;
		if (currentTime % 10 > 5) {
			rumblePower = (5 - (currentTime % 5)) / 5.0;
		} else {
			rumblePower = (currentTime % 5) / 5.0;
		}

		this.controller.setRumble(rumblePower);

		System.out.println(timer.get());
		System.out.println(currentTime);
		System.out.println(5000 - (currentTime % 5000));
		System.out.println(rumblePower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		this.controller.setRumble(0);
		timer.reset();
		timer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
