package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Gyro;

/**
 *
 */
public class ResetGyro extends Command {
	private Gyro gyro;
	
    public ResetGyro() {
    	super("ResetGyro");
    	
    	requires(this.gyro = Gyro.getGyro());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gyro.reset();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
