package team498.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class ShootBall extends TimedCommand {
	private Arm arm;
    public ShootBall(double timeout) {
        super(timeout);
		requires(this.arm = Arm.getArm());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.shoot();
    }

    // Called once after timeout
    protected void end() {
    	arm.stopShoot();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
