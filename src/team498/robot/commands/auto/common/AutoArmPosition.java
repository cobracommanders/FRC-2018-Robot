package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class AutoArmPosition extends Command {

	private Arm arm;

	private double armPower = .6;
	private double targetArmPosition; // from 0 to 315 degrees
	private double armPosition;

	private boolean higherThanTarget = false;
	private boolean done = false;

	public AutoArmPosition(double targetArmPosition) {
		super("AutoArmPosition");
		requires(this.arm = Arm.getArm());

		this.targetArmPosition = targetArmPosition;
		this.armPosition = arm.getPosition();
		this.higherThanTarget = armPosition > targetArmPosition;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// arm with potentiometer
		if (higherThanTarget) {
			arm.setArm(-armPower);
			if(arm.getPosition() <= targetArmPosition) {
				done = true;
			}
		} else if(!higherThanTarget) {
			arm.setArm(armPower);
			if(arm.getPosition() >= targetArmPosition) {
				done = true;
			}
		} 

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		arm.setArm(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
