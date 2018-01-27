package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import java.util.Date;

/**
 *
 */
public class RampDrive extends Command {
	private Drivetrain drivetrain;
	private Operator operator = Operator.getOperator();
	
	//values for calculateRamp
	private double lastValue = 0;
	private long lastTime = -1;
	//private double m_RC;
	private double tN = 0;
	private double tN1 = 0;
	private double tN2 = 0;
	private double c = 0.000002; //change acceleration
	private double FtN = 0;
	private double FtN1 = 0;
	private double FtN2 = 0;
	
    public RampDrive() {
    	super("RampDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public void reset() {
    	lastValue = 0;
    	lastTime = -1;
    }
    
    public double calculateRamp(double targetValue) {
    	long currentTime = new Date().getTime();
		
		tN = (double)currentTime;
		
		if(tN1 == 0) {
			//nothing
		} else if (tN2 == 0) {
			//nothing
		} else {
			double a = (tN-tN1) * ((c * (tN - tN2)));
			double b = ((FtN1 - FtN2)/(tN1 - tN2)) + FtN1;
			if(FtN1 < targetValue) {
				FtN = (tN-tN1) * ((c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
				if(FtN > targetValue) {
					FtN = targetValue;
				}
			} else if (FtN1 > targetValue) {
				// The difference is the negative sign
				FtN = (tN-tN1) * ((-c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
				//System.out.println("     " + (FtN - FtN1));
				//System.out.println("FtN:" + FtN + " FtN1: " + FtN1 + " FtN2: " + FtN2 + " tN: " + Math.round((tN * 1000)/1000) + " tN1: " + Math.round((tN1 * 1000)/1000) + " tN2: " + Math.round((tN2 * 1000)/1000));
				if(FtN < targetValue) {
					FtN = targetValue;
				}
			} else {
				FtN = targetValue;
			}
			
		} 
		
		
		
		
		FtN2 = FtN1;
		FtN1 = FtN;
		tN2 = tN1;
		tN1 = tN;
		
		
		
		return FtN;
    }
    
    // Called just before this Command runs the first time\\
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double move = calculateRamp(operator.controller.axisRightTrigger.getAxisValue() - operator.controller.axisLeftTrigger.getAxisValue());
        double rotate = calculateRamp(operator.controller.axisLeftX.getAxisValue());
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
