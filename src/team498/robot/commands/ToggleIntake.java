package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

/**
 *
 */

public class ToggleIntake extends Command {

	private Arm arm;
	private double power;

    public ToggleIntake(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("ManualIntake");
		this.power = power;
		requires(this.arm = Arm.getArm());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(arm.getLastLeft() == power){
    		arm.setIntake(0, 0);
    	}else{
    		arm.setIntake(power, power);
    	}
    	
    	
    	
    	
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
    	arm.failSafe();
    }
}
