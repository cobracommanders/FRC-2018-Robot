package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;


/**
 *
 */
public class ShootBall extends InstantCommand {

	private Arm arm;

	public ShootBall() {
		super("ShootBall");
		requires(this.arm = Arm.getArm());
	}

    // Called once when the command executes
    protected void initialize() {
    	arm.shoot();
    }

}
