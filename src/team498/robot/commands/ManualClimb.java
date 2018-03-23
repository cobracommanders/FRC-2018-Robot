package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
//import team498.robot.Operator;
import team498.robot.subsystems.Arm;

public class ManualClimb extends InstantCommand {

	// private Operator operator = Operator.getOperator();
	private Arm arm;
	public double climbPower;

	public ManualClimb(double climbPower) {
		super("ManualClimb");
		this.climbPower = climbPower;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.arm.setClimbPower(climbPower);

		SmartDashboard.putNumber(Dashboard.ClimbPower, climbPower);
		arm.updateDashboard();
	}
}
