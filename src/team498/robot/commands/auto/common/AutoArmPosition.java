package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;

public class AutoArmPosition extends InstantCommand {

	private Arm arm;
	private double targetArmPosition;

	public AutoArmPosition(double targetArmPosition) {
		super("AutoArmPosition");
		requires(this.arm = Arm.getArm());

		this.targetArmPosition = targetArmPosition;
	}

	protected void initialize() {
		arm.setArmAngle(targetArmPosition);
	}
}
