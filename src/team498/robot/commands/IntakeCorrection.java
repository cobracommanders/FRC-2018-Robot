package team498.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class IntakeCorrection extends TimedCommand {
	private Arm arm;
	private double leftPower;
	private double rightPower;

    public IntakeCorrection(double timeout) {
        super(timeout);
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		requires(this.arm = Arm.getArm());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.setIntake(.25,-.25);
    }

    // Called once after timeout
    protected void end() {
    	arm.setIntake(-1, 1);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
