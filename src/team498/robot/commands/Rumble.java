package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Controller;

/**
 *
 */
public class Rumble extends Command {
	
	private Controller controller;
    public Rumble(Controller controller) {
        this.controller = controller;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Rumble Command!");
    		this.controller.setRumble(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    		this.controller.setRumble(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		this.end();
    }
}
