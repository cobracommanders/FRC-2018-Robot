package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Intake;

/**
 *
 */
public class ManualIntake extends Command {

	private Intake intake;
	private Operator operator = Operator.getOperator();
	
	public ManualIntake() {
		super("ManualIntake");
		// Required Subsystems
		requires(this.intake = Intake.getIntake());
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPower = operator.controller.axisLeftTrigger.getAxisValue();
    	double rightPower = -operator.controller.axisRightTrigger.getAxisValue();
    	this.intake.set(leftPower, rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
