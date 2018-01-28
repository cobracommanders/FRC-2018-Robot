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
	boolean firstStart = true;
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
    	if(firstStart) {
    		System.out.println("Test3");
    		pidAutoTurnSubsystem.enable();
    		firstStart = false;
    	}else {
    	System.out.println("What's the setpoint? It is: " + pidAutoTurnSubsystem.getSetpoint());
    	System.out.println("Position? It is: " + pidAutoTurnSubsystem.getPosition());
    	System.out.println("Is it on the target? It is: " + pidAutoTurnSubsystem.onTarget());
    	System.out.println("Test4");
    	}
    	//drivetrain.drive(0, pidAutoTurnSubsystem.getSetpoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Finished? " + pidAutoTurnSubsystem.onTarget()); //TODO DELETE THIS LINE IF WORKS
    	return pidAutoTurnSubsystem.onTarget();
    	//return Math.abs(pidAutoTurnSubsystem.getSetpoint() - pidAutoTurnSubsystem.getPosition()) < 0.1;
        //return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Test1");
    	pidAutoTurnSubsystem.disable();
    	firstStart = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Test2");
    	end();
    	
    }
}
