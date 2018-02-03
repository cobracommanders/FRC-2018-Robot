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

	public RampDrive() {
		super("RampDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());
		requires(this.gyro = Gyro.getGyro());
	}

	protected void initialize() {
	}

	public double AngleComp() {
		if (Math.abs(operator.controller.axisLeftX.getAxisValue()) > 0.1) {
			gyro.reset();
			return 0;
		}
		
		return gyro.getAngleZ() / 45; //Should make to go between -1 and 1, unless it goes past 45 without correcting which is a problem
	}

	protected void execute() {

		double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue()
				- operator.controller.axisLeftTrigger.getAxisValue());
		double rotate = turnAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());
		double comp = AngleComp();
		System.out.println(comp);
		this.drivetrain.drive(move, rotate);

		SmartDashboard.putNumber("move value", move);
		SmartDashboard.putNumber("rotate value", rotate + comp);
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
