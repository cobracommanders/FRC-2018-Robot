package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;

public class ManualDrive extends Command {

	private Operator operator = Operator.getOperator();
	private Drivetrain drivetrain;
	private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.0005);
	private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.0005);

	public ManualDrive() {
		super("RampDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());
	}

	protected void initialize() {
	}

	protected void execute() {

		double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue()
				- operator.controller.axisLeftTrigger.getAxisValue());
		double rotate = turnAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());

		this.drivetrain.manualDrive(move, rotate);

		SmartDashboard.putNumber("move value", move);
		SmartDashboard.putNumber("rotate value", rotate);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.manualDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
