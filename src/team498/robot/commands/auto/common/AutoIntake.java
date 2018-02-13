package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

public class AutoIntake extends Command {

	private Arm arm;
	
	private double intakeLeftPower;
	private double intakeRightPower;
	
	private boolean done = false;

	public AutoIntake(double intakeLeftPower, double intakeRightPower) {
		super("AutoIntake");

		requires(this.arm = Arm.getArm());

		this.intakeLeftPower = intakeLeftPower;
		this.intakeRightPower = intakeRightPower;

	}

	protected void initialize() {

	}

	protected void execute() {
		arm.setIntake(intakeLeftPower, intakeRightPower);
	
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
