package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.subsystems.Gyro;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Prefs;
import team498.robot.commands.RampDrive;
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
 * Trial 1: 97
 * Trial 2:
 * Trial 3: 
 * PID( , , )
 * Trial 1:
 * Trial 2:
 * Trial 3:
 */
public class PidAutoTurnSubsystem extends PIDSubsystem {
	
	private static PidAutoTurnSubsystem pidAutoTurnSubsystem = null;
    public static PidAutoTurnSubsystem getPidAutoTurnSubsystem() {
    	pidAutoTurnSubsystem = pidAutoTurnSubsystem == null ? new PidAutoTurnSubsystem() : pidAutoTurnSubsystem;
    	return pidAutoTurnSubsystem;
    }    

	static Prefs prefs = Prefs.getPrefs();
	
	private double theOutput = 0; 
	private ConstantAccelerationCalculator ramp = new ConstantAccelerationCalculator(prefs.getRamp_C());	
	private Gyro gyro = Gyro.getGyro();
	private Drivetrain drivetrain = Drivetrain.getDrivetrain();

    public PidAutoTurnSubsystem() {
		super("PidAutoTurnSubsystem", prefs.getPID_P(), prefs.getPID_I(), prefs.getPID_D()); //the PID values are here! TODO CHANGE VALUES FOR OPTIMALNESS
		
		System.out.println("Prefs - P: " + prefs.getPID_P() + " I: " + prefs.getPID_I() + " D: " + prefs.getPID_D() + " C: " + prefs.getRamp_C());
		
		setAbsoluteTolerance(1);
		getPIDController().setContinuous(false);
		setInputRange(-180, 180);
		setOutputRange(-0.5, 0.5);
	}

	public void initDefaultCommand() {
    }
	
	public void updateDashboard() {
		SmartDashboard.putNumber("output values (PID)", theOutput);
		SmartDashboard.putNumber("Angle for PID", pidAutoTurnSubsystem.getPosition()); //same as returning the pid input
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngleZ();
	}

	@Override
	protected void usePIDOutput(double output) {
		//drivetrain.rampDrive(0, output);
		drivetrain.pidWrite(ramp.getNextDataPoint(output));
		System.out.println("Prefes - P: " + prefs.getPID_P() + " I: " + prefs.getPID_I() + " D: " + prefs.getPID_D() + " C: " + prefs.getRamp_C());
		System.out.println("Values for Jack: " + output);
		System.out.println("Ramp Drive Value: " + ramp.getNextDataPoint(output));
		output = theOutput;
	}
}

