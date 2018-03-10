package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class AutoArmTemporaryPosition extends Command {
	public double armPower;
	public double index;
	private Arm arm;

	private Timer timer = new Timer();

	public AutoArmTemporaryPosition(double armPower) {
		super("AutoArmTemporaryPosition");
		requires(this.arm = Arm.getArm());

		this.armPower = armPower;
		this.index = index;
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		arm.setArmPower(armPower);
	}

	protected boolean isFinished() {
		return arm.getPosition() + 100 <= arm.getScalePot();
	}

	protected void end() {
		arm.setArmPower(0);
	}

	protected void interrupted() {
		end();
	}
}