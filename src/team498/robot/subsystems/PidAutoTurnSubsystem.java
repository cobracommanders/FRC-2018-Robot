package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Drivetrain;

/**
 * PID Training Notes:
 * PID(0.01 ,0.1 ,0.04 )
 * Trial 1: overshoots then returns to 68.
 * Trial 2: goes to 120 then stops
 * Trial 3:
 * PID( .01,0 ,.1 )
 * Trial 1: goes to 20 degrees at max motor then goes down very fast ends at 60 degrees
 * Trial 2:
 * Trial 3: 
 * PID( .02,0 ,.06 )
 * Trial 1:
 * Trial 2:
 * Trial 3: 
 * PID( .02,0 ,.06 )
 * Trial 1: goes to 87
 * Trial 2: goes to 90
 * Trial 3: 
 * PID( .02,0 ,.04 )
 * Trial 1: 92
 * Trial 2: 94
 * Trial 3: 
 * PID( , , .02)
 * Trial 1:
 * Trial 2:
 * Trial 3: 
 * PID( , , )
 * Trial 1:
 * Trial 2:
 * Trial 3:
 */
public class PidAutoTurnSubsystem extends PIDSubsystem {

	double theOutput = 0;
	
	private static PidAutoTurnSubsystem pidAutoTurnSubsystem = null;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Gyro gyro = Gyro.getGyro();
	Drivetrain drivetrain = Drivetrain.getDrivetrain();

    public PidAutoTurnSubsystem() {
		super("PidAutoTurnSubsystem", 0.02, 0, 0.02); //the PID values are here! TODO CHANGE VALUES FOR OPTIMALNESS
		setAbsoluteTolerance(1);
		getPIDController().setContinuous(false);
		setInputRange(-180, 180);
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
		return gyro.getAngleZ(); //this is what jack changed
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		drivetrain.pidWrite(output);
		System.out.println("Values for Jack: " + output); //jack added this
		output = theOutput;
	}
}

