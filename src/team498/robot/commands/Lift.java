package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class Lift extends Command {
	private Arm arm;
	boolean isUp = false;
	boolean target = true;

    public Lift() {
    	super("Lift");
    	
    	requires(this.arm = Arm.getArm());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.liftSet(isUp);
    	isUp = !isUp;
    	target = isUp;
    	SmartDashboard.putBoolean(Dashboard.ElevatorUp,isUp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(target == isUp) {
    		return true;
    	}else{
    		return false;
    	}
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
