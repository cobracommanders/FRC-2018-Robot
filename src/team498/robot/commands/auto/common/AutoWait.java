package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoWait extends Command {
	private Timer timer = new Timer();
	
	private double time;

    public AutoWait(double time) {
    	super("AutoWait");
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
