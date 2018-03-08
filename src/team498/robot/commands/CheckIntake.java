package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
//import team498.robot.Operator;
import team498.robot.subsystems.Arm;

public class CheckIntake extends Command {

	// private Operator operator = Operator.getOperator();
	private Arm arm;
	private double cap = 1.0; // TODO: Change this
	private double armPower = 0;

	public CheckIntake() {
		super("CheckIntake");
		this.armPower = 0;
		requires(this.arm = Arm.getArm());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.arm.setArmPower(0);
		this.arm.checkIntake();
		SmartDashboard.putNumber(Dashboard.ArmPower, armPower * cap);
		arm.updateDashboard();
	}
	protected void execute(){
		this.arm.setArmPower(0);
		this.arm.checkIntake();
		SmartDashboard.putNumber(Dashboard.ArmPower, armPower * cap);
		arm.updateDashboard();
	}
	protected boolean isFinished(){
		return false;
	}
}
