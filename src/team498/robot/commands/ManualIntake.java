package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Operator;
import team498.robot.subsystems.Arm;

public class ManualIntake extends Command {

	private Arm arm;
	
	private double leftPower = 0;
	private double rightPower = 0;
	private double targetPower = 0;
	boolean hasChanged;

	
	public ManualIntake(double setLeftPower, double setRightPower) {
		super("ManualIntake");
		leftPower = setLeftPower;
		rightPower = setRightPower;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*if(targetPower != leftPower) {
			hasChanged = true;
		}else {
			hasChanged = false;
		}
		if(!hasChanged) {
			targetPower = 0;
			leftPower = 0;
			rightPower = 0;
		}*/
			this.arm.setIntake(leftPower,rightPower);
			targetPower = leftPower;
			
		SmartDashboard.putNumber(Dashboard.IntakeLeftPower, leftPower);
		SmartDashboard.putNumber(Dashboard.IntakeRightPower, rightPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(targetPower == leftPower) {
			return true;
		}else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		

	}
}
