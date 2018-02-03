package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

/**
 *
 */
public class RampDrive extends Command {
	private Drivetrain drivetrain;
	private Operator operator = Operator.getOperator();
	private Gyro gyro;
	private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.0005);
	private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.0005);
	private boolean turning = false;
	private boolean oldTurning = false;
	private double comp = 0.0;
	private double oldComp = 0.0;

	public RampDrive() {
		super("RampDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());
		requires(this.gyro = Gyro.getGyro());
	}

	protected void initialize() {
		gyro.reset();
	}

	public double AngleComp() {
		int degreeRange = 3; // The higher this number, the less it'll try to correct
		double angle = 0.0;
		oldTurning = turning;
		if (Math.abs(operator.controller.axisLeftX.getAxisValue()) > 0.1) turning = true; else turning = false;
		if (oldTurning != turning) gyro.reset();
		return !turning ? -gyro.getAngleZ() / degreeRange : 0;
	}

	protected void execute() {

		double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue()
				- operator.controller.axisLeftTrigger.getAxisValue());
		double rotate = turnAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());
		oldComp = comp;
		comp = AngleComp();
		if(Math.abs(comp - oldComp) > 0.1) gyro.reset();
		System.out.println(comp);
		this.drivetrain.drive(move, turning ? rotate : comp);

		SmartDashboard.putNumber("move value", move);
		SmartDashboard.putNumber("rotate value", turning ? rotate : comp);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
