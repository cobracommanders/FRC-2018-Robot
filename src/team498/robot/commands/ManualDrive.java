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
	private boolean oldState = false;
	private boolean newState = false;
	private boolean turbo = false;
	private double turboCap = 0.8; // TODO: change, maybe?

	public ManualDrive() {
		super("RampDrive");

		requires(this.drivetrain = Drivetrain.getDrivetrain());
	}

	protected void initialize() {
	}

	protected void execute() {

		oldState = newState;
		newState = operator.controller.buttonY.get();
		if (newState && !oldState) {
			turbo = !turbo;
		}

		double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue()
				- operator.controller.axisLeftTrigger.getAxisValue());
		double rotate = turnAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());

		this.drivetrain.drive(move, rotate);

		SmartDashboard.putNumber("move value", move * (turbo ? 1 : turboCap));
		SmartDashboard.putNumber("rotate value", rotate);
		SmartDashboard.putBoolean("Turbo mode", turbo);
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
