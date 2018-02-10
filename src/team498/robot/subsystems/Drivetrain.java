/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Dashboard;
import team498.robot.Helpers;
import team498.robot.Prefs;
import team498.robot.commands.ManualDrive;

/**
 * PID Training Notes: PID(0.01 ,0.1 ,0.04 ) Trial 1: overshoots then returns to
 * 68. Trial 2: goes to 120 then stops Trial 3: PID( .01,0 ,.1 ) Trial 1: goes
 * to 20 degrees at max motor then goes down very fast ends at 60 degrees Trial
 * 2: Trial 3: PID( .02,0 ,.06 ) Trial 1: Trial 2: Trial 3: PID( .02,0 ,.06 )
 * Trial 1: goes to 87 Trial 2: goes to 90 Trial 3: PID( .02,0 ,.04 ) Trial 1:
 * 92 Trial 2: 94 Trial 3: PID( , , .02) Trial 1: 97 Trial 2: Trial 3: PID( , ,
 * ) Trial 1: Trial 2: Trial 3:
 */

public class Drivetrain extends PIDSubsystem {

	private static Drivetrain drivetrain = null;
	public static Drivetrain getDrivetrain() {
		drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
		return drivetrain;
	}

	static Prefs prefs = Prefs.getPrefs();

	private Spark spark0 = new Spark(0);
	private Spark spark1 = new Spark(2);
	private ADIS16448_IMU gyro = new ADIS16448_IMU();
	private ConstantAccelerationCalculator ramp = new ConstantAccelerationCalculator(prefs.getRamp_C());

	private boolean correct;
	private boolean locked;
	private Timer timer;

	private final double delayTime = 0.3;
	private final double threshold = 0.4;
	private final double tolerence = 0.1;

	private DifferentialDrive drive = new DifferentialDrive(spark1, spark0);

	public Drivetrain() {
		super("Drivetrain", prefs.getPID_P(), prefs.getPID_I(), prefs.getPID_D());

		gyro.reset();
		timer = new Timer();
		timer.start();

		// Initialize PID
		setAbsoluteTolerance(1);
		getPIDController().setContinuous(false);
		setInputRange(-180, 180);
		setOutputRange(-0.5, 0.5);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualDrive());
	}

	public void drive(double move, double rotate) {

		// If driving, not turning
		if (Math.abs(move) > 0 && rotate == 0) {
			if (timer.get() == 0) {
				timer.start();
			}
			if (timer.get() > delayTime) {
				correct = true;
				if (!locked) {
					locked = true;
					gyro.reset();
				}
			}
			
		} else {
			correct = false;
			locked = false;
			if (timer.get() > 0) {
				timer.stop();
				timer.reset();
			}
		}

		rotate = correct ? Helpers.rotateToTarget(gyro.getAngleZ(), 0, tolerence, threshold) : rotate;

		SmartDashboard.putNumber("OUTPUT ROTATE", rotate);

		drive.arcadeDrive(move, rotate);
	}

	public void resetGyro() {
		gyro.reset();
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngleZ();
	}

	@Override
	protected void usePIDOutput(double output) {

		// Perform a ramping calculation on the PID output
		double rampedOutput = ramp.getNextDataPoint(output);

		// TODO: Can we use some arcade drive instead?
		this.spark0.pidWrite(rampedOutput);
		this.spark1.pidWrite(rampedOutput);

		System.out.println("Prefs - P: " + prefs.getPID_P() + " I: " + prefs.getPID_I() + " D: " + prefs.getPID_D()
				+ " C: " + prefs.getRamp_C());
		System.out.println("PID Output: " + output);
		System.out.println("PID Ramped Output: " + rampedOutput);
	}

	public void updateDashboard() {
		// SmartDashboard.putNumber("Output values (PID)", pidOutput);
		SmartDashboard.putNumber("Angle for PID", this.getPosition());

		SmartDashboard.putNumber(Dashboard.GyroAngle, gyro.getAngle());
		SmartDashboard.putNumber(Dashboard.GyroAngleX, gyro.getAngleX());
		SmartDashboard.putNumber(Dashboard.GyroAngleY, gyro.getAngleY());
		SmartDashboard.putNumber(Dashboard.GyroAngleZ, gyro.getAngleZ());
	}
}
