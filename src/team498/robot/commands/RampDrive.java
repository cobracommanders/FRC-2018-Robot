package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import java.util.Date;

/**
 *
 */
public class RampDrive extends Command {
	private Drivetrain drivetrain;
	private Operator operator = Operator.getOperator();
	private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.2);
	private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.2);
	
    public RampDrive() {
    	super("RampDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time\\
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue() - operator.controller.axisLeftTrigger.getAxisValue());
        double rotate = moveAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());
        this.drivetrain.drive(move, rotate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
