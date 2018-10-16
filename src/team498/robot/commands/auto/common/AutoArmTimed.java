package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class AutoArmTimed extends Command {
	public double time;
	public double armPower;
	private Arm arm;

	private Timer timer = new Timer();

	public AutoArmTimed(double armPower, double time) {
		super("AutoDriveTimed");
		requires(this.arm = Arm.getArm());

		this.armPower = armPower;
		this.time = time;
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		arm.setArmPower(-armPower);
	}

	protected boolean isFinished() {
		return timer.get() >= time;
	}

	protected void end() {
		arm.setArmPower(0);
	}

	protected void interrupted() {
		end();
	}
}