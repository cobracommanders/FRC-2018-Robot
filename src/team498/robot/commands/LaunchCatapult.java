package team498.robot.commands;

//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import team498.robot.subsystems.Catapult;

/**
 *
 */
public class LaunchCatapult extends TimedCommand {
	private Catapult catapult;

	public LaunchCatapult(String LaunchCatapult, double time) {
		super("LaunchCatapult", time);
		
		requires(this.catapult = Catapult.getInstance());
		
	}

	//private Timer clock;
	
	//private boolean firstTime = true;

	// Called just before this Command runs the first time
	protected void initialize() {
		//clock = new Timer();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*if (firstTime == true) {
			clock.start();
			firstTime = false;
		}*/
		System.out.println("execute is met");
		catapult.launchCatapult();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		/*if (clock.get() > 5) {
			return true;
		} else {
			return false;
		}*/
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		/*clock.stop();
		clock.reset();*/
		//firstTime = true;
		System.out.println("end is met");
		catapult.resetCatapult();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
