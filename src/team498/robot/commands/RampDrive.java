package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;

/**
 *
 */
public class RampDrive extends Command {
	private Drivetrain drivetrain;
	private Operator operator = Operator.getOperator();
	private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.000005);
	private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.000005);
	
    public RampDrive() {
    	super("RampDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    }

    protected void initialize() {
    }
    
    public void angleComp() {
    	
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
