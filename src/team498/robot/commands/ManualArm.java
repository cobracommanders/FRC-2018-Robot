package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
//import team498.robot.Operator;
import team498.robot.subsystems.Arm;

public class ManualArm extends InstantCommand {

	// private Operator operator = Operator.getOperator();
	private Arm arm;
	private double cap = 1.0; // TODO: Change this
	private double armPower = 0;

	public ManualArm(double armPower) {
		super("ManualArm");
		this.armPower = armPower;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.arm.setArmPower(armPower * cap);

		SmartDashboard.putNumber(Dashboard.ArmPower, armPower * cap);
		arm.updateDashboard();
	}
}
