package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoStop extends Command {
	private Drivetrain drivetrain;

    public AutoStop() {
    	super("Stop");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(0, 0); //no drive
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //stops when command time expires
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
