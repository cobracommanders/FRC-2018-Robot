package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;

public class AutoArmPosition extends InstantCommand {

	private Arm arm;

	private double targetArmDirection;

	public AutoArmPosition(double targetArmDirection) {
		super("AutoArmPosition");
		requires(this.arm = Arm.getArm());
		this.targetArmDirection = targetArmDirection;
	}

	protected void initialize() {
		if(targetArmDirection == 1){
			arm.incrementArm();
		}else if(targetArmDirection == 0){
			arm.decrementArm();
		}
		
	}
}
