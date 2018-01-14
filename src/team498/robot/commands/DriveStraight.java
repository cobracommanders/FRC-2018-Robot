package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class DriveStraight extends Command {
	private Drivetrain drivetrain;
	
    public DriveStraight() {
    	super("DriveStraight");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(.8, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
