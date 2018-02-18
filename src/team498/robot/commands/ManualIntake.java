package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.subsystems.Arm;

public class ManualIntake extends InstantCommand {

	private Arm arm;

	private double leftPower = 0;
	private double rightPower = 0;

	public ManualIntake(double setLeftPower, double setRightPower) {
		super("ManualIntake");
		leftPower = setLeftPower;
		rightPower = setRightPower;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.arm.setIntake(leftPower, rightPower);

		SmartDashboard.putNumber(Dashboard.IntakeLeftPower, leftPower);
		SmartDashboard.putNumber(Dashboard.IntakeRightPower, rightPower);
	}
}