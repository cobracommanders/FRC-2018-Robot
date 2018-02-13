package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class AutoLift extends Command {
	
	private Arm arm;

	private boolean isLiftUp;
	private boolean done = false;
	
    public AutoLift(boolean isLiftUp) {
    	super("AutoLift");
    	
    	requires(this.arm = Arm.getArm());
    	
    	this.isLiftUp = isLiftUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		// lift control
		if (isLiftUp) {
			arm.setLift(isLiftUp);
		} else { // if isLiftUp = false
			arm.setLift(isLiftUp);
		}
		
		done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
    
}
