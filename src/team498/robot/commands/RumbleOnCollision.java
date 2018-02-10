package team498.robot.commands;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Mappings;
import team498.robot.subsystems.HapticFeedback;

/**
 *
 */
public class RumbleOnCollision extends Command {
	
	private HapticFeedback hapticFeedback;
	private Timer timer;
	
	private double accelerometerX;
	private double accelerometerY;
	private double accelerometerZ;
	
	public double leftRumblePower;
	public double rightRumblePower;
	
	private double rearPower = 0.5;
	private double frontPower = 1;

	public RumbleOnCollision() {
		requires(this.hapticFeedback = HapticFeedback.getHapticFeedback());
	}
	
	public void setAccelerometerData(double accelerometerX, double accelerometerY, double accelerometerZ) {
		this.accelerometerX = accelerometerX;
		this.accelerometerY = accelerometerY;
		this.accelerometerZ = accelerometerZ;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 when collisions occur on the back of the robot the rumble pulses
		 when collisions occur on the front of the robot it just rumbles for a certain time.
		 */
		if (accelerometerZ > Mappings.CollisionThreshold) {
			//back side has been hit
			leftRumblePower = rearPower;
			rightRumblePower = rearPower;
		} else if (accelerometerZ < -Mappings.CollisionThreshold) {
			//front side has been hit
			leftRumblePower = frontPower;
			rightRumblePower = frontPower;
		}
	
		/*
		 when collisions occur on the left side of the robot the left side of the controller vibrates and 
		 when collisions occur on the right side of the robot the right side of the controller vibrates.
		*/
		if (accelerometerX > Mappings.CollisionThreshold) {
			//left side has been hit
			leftRumblePower = accelerometerX - Mappings.CollisionThreshold;
		} else if (accelerometerX < -Mappings.CollisionThreshold) {
			//right side has been hit
			rightRumblePower = Math.abs(accelerometerX) - Mappings.CollisionThreshold;
		}
		
		this.hapticFeedback.Rumble(leftRumblePower, rightRumblePower);
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timer.get() > 0.5) {
			timer.stop();
			timer.reset();
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		leftRumblePower = 0;
		rightRumblePower = 0;
		this.hapticFeedback.Rumble(leftRumblePower, rightRumblePower);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
