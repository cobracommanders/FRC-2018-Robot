package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Operator;
import team498.robot.subsystems.Arm;

public class ManualArm extends Command {

	private Operator operator = Operator.getOperator();
	private Arm arm;
	private double cap = 1.0; // TODO: Change this

	public ManualArm() {
		super("ManualArm");
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = operator.controller.axisRightY.getAxisValue();
    	this.arm.armSet(power);
    	SmartDashboard.putNumber(Dashboard.ArmPower, power);
    	arm.updateDashboard();
    }

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double power = operator.controller.axisRightY.getAxisValue();
		this.arm.setArm(power * cap);
		SmartDashboard.putNumber(Dashboard.ArmPower, power * cap);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
