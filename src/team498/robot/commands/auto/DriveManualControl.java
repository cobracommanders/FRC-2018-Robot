package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class DriveManualControl extends Command {
	
	private Drivetrain drivetrain;
	private double move;
	private double rotate;
	
	
    public DriveManualControl(double move, double rotate) {
    	super("DriveManual");
   
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	this.move = move;
    	this.rotate = rotate;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(move, rotate);
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
