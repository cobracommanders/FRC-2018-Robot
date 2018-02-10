package team498.robot.commands;

//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import team498.robot.subsystems.Catapult;

public class LaunchCatapult extends TimedCommand {
	private Catapult catapult;

	public LaunchCatapult(String LaunchCatapult, double time) {
		super("LaunchCatapult", time);
		requires(this.catapult = Catapult.getInstance());		
	}

	protected void initialize() {

	}

	protected void execute() {
		catapult.launchCatapult();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		catapult.resetCatapult();
	}

	protected void interrupted() {
		end();
	}
}
