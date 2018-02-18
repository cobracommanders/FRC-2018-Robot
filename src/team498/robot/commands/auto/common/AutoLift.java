package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class AutoLift extends Command {

	private Arm arm;
	private boolean isLiftUp;
	private boolean done = false;

	public AutoLift(boolean isLiftUp) {
		super("AutoLift");
		requires(this.arm = Arm.getArm());

		this.isLiftUp = isLiftUp;
	}

	protected void initialize() {
	}

	protected void execute() {
		// lift control
		if (isLiftUp) {
			arm.setLift(isLiftUp);
		} else { // if isLiftUp = false
			arm.setLift(isLiftUp);
		}

		done = true;
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
