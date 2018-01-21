package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

/**
 *
 */
public class AutoTurn extends Command {
	 private PIDSource pidSource = new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				return gyro.getAngle();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		private PIDOutput pidOutput = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
			}
		};
		
	    private PIDController rampController = new PIDController(0.9,0,0,pidSource,pidOutput);
	    
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
    	rampController.setContinuous(true);
    	rampController.setInputRange(-180, 180);
    	rampController.setOutputRange(-1, 1);
    	rampController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.drive(0, rampController.get());
    	rampController.setSetpoint(targetAngle);
    	updateDashboard();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return rampController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	rampController.disable();
    
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
