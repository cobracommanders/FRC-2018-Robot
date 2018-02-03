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
		if (operator.controller.axisRightTrigger.getAxisValue() >= 0.2 || operator.controller.axisLeftTrigger.getAxisValue() >= 0.2) {
			return -gyro.getAngleZ() / 9;
		} else {
			return 0;
		}
	}
    
    protected void execute() {
    	
        double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue() - operator.controller.axisLeftTrigger.getAxisValue());
        double rotate = turnAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());
        this.drivetrain.drive(move, rotate);
        
        SmartDashboard.putNumber("move value", move);
        SmartDashboard.putNumber("rotate value", rotate);
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
