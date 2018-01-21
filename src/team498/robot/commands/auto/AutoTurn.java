package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDInterface;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pidTypes.AutoTurnOutput;
import pidTypes.AutoTurnSource;
import team498.robot.Dashboard;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

/**
 *
 */
public class AutoTurn extends Command {
	
		
		
		
	private AutoTurnSource pidSource = new AutoTurnSource();
	private AutoTurnOutput pidOutput = new AutoTurnOutput();
	private PIDController rampController;
	    
	private static Drivetrain driveTrain = null;
    private Gyro gyro = null;
    private double targetAngle = 0;
    public AutoTurn(double targetAngle) {
    	requires(this.driveTrain = Drivetrain.getDrivetrain());
    	requires(this.gyro = Gyro.getGyro());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetAngle = targetAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rampController = new PIDController(0.6, 0, 0, pidSource, pidOutput);
    	rampController.enable();
    	rampController.setContinuous(false);
    	System.out.println("SetContinuousPassed");
    	rampController.setInputRange(-180, 180);
    	rampController.setOutputRange(-1000, 1000);
    	rampController.setSetpoint(targetAngle);
    	rampController.setAbsoluteTolerance(1);
    	
    	
    	System.out.println("endInitialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Execute: " + rampController.get());
    	System.out.println("Input: "  + pidSource.pidGet());
    	System.out.println("Output: " + pidOutput.getOutput());
    	driveTrain.drive(0, rampController.get());
    	
    	updateDashboard();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("isFinished()" + rampController.onTarget());
        return rampController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//rampController.disable();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
    public void pidTurn(double targetAngle, double rotate) {
    	
    	rampController.setSetpoint(targetAngle);
    	
    	
    }
	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.PIDOutput, rampController.get());
		SmartDashboard.putNumber(Dashboard.IsPIDOnTarget, rampController.get());
	}
}
