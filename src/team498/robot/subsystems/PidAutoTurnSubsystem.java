package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Drivetrain;

/**
 * 
 */
public class PidAutoTurnSubsystem extends PIDSubsystem {

	double theOutput = 0;
	
	private static PidAutoTurnSubsystem pidAutoTurnSubsystem = null;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Gyro gyro = Gyro.getGyro();
	Drivetrain drivetrain = Drivetrain.getDrivetrain();

    public PidAutoTurnSubsystem() {
		super("PidAutoTurnSubsystem", 0.01, 0.1, 0.04); //the PID values are here! TODO CHANGE VALUES FOR OPTIMALNESS
		setAbsoluteTolerance(1);
		getPIDController().setContinuous(false);
		setInputRange(0, 360);
		setOutputRange(-0.8, 0.8);
		
		// TODO Auto-generated constructor stubs
	}
    
    public static PidAutoTurnSubsystem getPidAutoTurnSubsystem() {
    	pidAutoTurnSubsystem = pidAutoTurnSubsystem == null ? new PidAutoTurnSubsystem() : pidAutoTurnSubsystem;
    	return pidAutoTurnSubsystem;
    }

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void updateDashboard() {
		SmartDashboard.putNumber("output values (PID)", theOutput);
		SmartDashboard.putNumber("Angle for PID", pidAutoTurnSubsystem.getPosition()); //same as returning the pid input
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		//return 0;
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		drivetrain.pidWrite(output);
		System.out.println("Values for Jack: " + output); //jack added this
		output = theOutput;
	}
}

