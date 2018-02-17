package team498.robot.commands.auto.common;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class AutoArmTimed extends Command {

	public double power;
	public double time;
	private Timer timer = new Timer();
	private Arm arm;
	
    public AutoArmTimed(double power, double time) {
    	super("AutoArmTimed");
    	
    	requires(this.arm = Arm.getArm());
    	
    	this.power = power;
    	this.time = time;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.setArm(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.setArm(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
