package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Drivetrain;

/**
 * 
 */
public class PidAutoTurnSubsystem extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Gyro gyro = Gyro.getGyro();
	Drivetrain drivetrain = Drivetrain.getDrivetrain();

    public PidAutoTurnSubsystem() {
		super("PidAutoTurnSubsystem", .6, 0.0, 0);
		setAbsoluteTolerance(1);
		getPIDController().setContinuous(false);
		// TODO Auto-generated constructor stub
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
	}
}

