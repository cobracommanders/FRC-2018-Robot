package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.PidAutoTurnSubsystem;
//import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class AutoTurnRandyPid extends Command {
	private PidAutoTurnSubsystem pidAutoTurnSubsystem;
	//private Drivetrain drivetrain;
	
	private double targetAngle = 0;

    public AutoTurnRandyPid(double targetAngle) {
    	super("AutoTurnRandyPid");
    	
    	requires(this.pidAutoTurnSubsystem = PidAutoTurnSubsystem.getPidAutoTurnSubsystem());
    	//requires(this.drivetrain = Drivetrain.getDrivetrain());
    	
    	this.targetAngle = targetAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pidAutoTurnSubsystem.setSetpoint(targetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Executing!"); //TODO DELETE THIS LINE IF WORKS
    	//drivetrain.drive(0, pidAutoTurnSubsystem.getSetpoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Finished!"); //TODO DELETE THIS LINE IF WORKS
    	return pidAutoTurnSubsystem.onTarget();
    	//return Math.abs(pidAutoTurnSubsystem.getSetpoint() - pidAutoTurnSubsystem.getPosition()) < 0.1;
        //return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pidAutoTurnSubsystem.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
