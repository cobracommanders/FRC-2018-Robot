package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class AutoDriveTimed extends Command {
	public double movePower;
	public double time;
	public double turnPower;
	private Drivetrain drivetrain;
	
	private Timer timer = new Timer();

    public AutoDriveTimed(double movePower, double turnPower, double time) {
    	super("AutoDriveTimed");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	
    	this.movePower = movePower;
    	this.time = time;
    	this.turnPower = turnPower;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(movePower, turnPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
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
