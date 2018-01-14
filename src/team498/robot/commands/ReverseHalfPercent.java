package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.BumperButton;
import team498.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.DigitalInput;

public class ReverseHalfPercent extends Command {
	private Drivetrain drivetrain;
	private BumperButton button;
	
    public ReverseHalfPercent() {
    	super("ReverseHalfPercent");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	requires(this.button = BumperButton.getButton());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(-.6, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("button value pt 2: " + button.get());
        return !button.get();
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
