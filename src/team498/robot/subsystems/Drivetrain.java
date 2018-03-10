/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.ConstantAccelerationCalculator;
import team498.robot.Dashboard;
import team498.robot.Helpers;
import team498.robot.Mappings;
import team498.robot.Prefs;
import team498.robot.commands.ManualDrive;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {

	// Field is 16.4592 meters across
	private static final double WheelDiameter = 6; // 6 inch wheels.
	private static final double PulsePerRevolution = 2048; // all switches on
															// the encoder are
															// off
	private static final double WheelCircumference = WheelDiameter * Math.PI;
	private static final double InchesPerPulse = WheelCircumference / PulsePerRevolution;

	private static Drivetrain drivetrain = null;

	public static Drivetrain getDrivetrain() {
		drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
		return drivetrain;
	}

	static Prefs prefs = Prefs.getPrefs();

	private Spark frontLeftDrive = new Spark(Mappings.FrontLeftMotorChannel);
	private Spark frontRightDrive = new Spark(Mappings.FrontRightMotorChannel);
	private Spark backLeftDrive = new Spark(Mappings.BackLeftMotorChannel);
	private Spark backRightDrive = new Spark(Mappings.BackRightMotorChannel);

	private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
	private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

	private ADIS16448_IMU gyro = new ADIS16448_IMU();
	private ConstantAccelerationCalculator ramp = new ConstantAccelerationCalculator(prefs.getRamp_C());

	private boolean applyCorrection;
	private boolean directionLocked;
	private Timer correctionDelayTimer;
	private final double correctionDelayTime = 0.3;
	private final double correctionGain = 0.03;
	private final double correctionAngleTolerence = 0.0;

	private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

	private boolean turbo = false;
	private double turboCap = 0.8;
	private double turnCap = 0.8;

	public Encoder leftEncoder = new Encoder(Mappings.LeftEncoderDigitalSource1, Mappings.LeftEncoderDigitalSource2);
	public Encoder rightEncoder = new Encoder(Mappings.RightEncoderDigitalSource1, Mappings.RightEncoderDigitalSource2);

	public Drivetrain() {
		super("Drivetrain", prefs.getPID_P(), prefs.getPID_I(), prefs.getPID_D());

		leftEncoder.setDistancePerPulse(InchesPerPulse);
		rightEncoder.setDistancePerPulse(InchesPerPulse);

		gyro.reset();
		correctionDelayTimer = new Timer();
		correctionDelayTimer.start();

		// Initialize PID
		setAbsoluteTolerance(1);
		setInputRange(-180, 180);
		setOutputRange(-0.5, 0.5);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualDrive());
	}

	public void manualDrive(double move, double rotate) {

		// If driving and not turning then apply correction
		if (Math.abs(move) > 0 && rotate == 0) {
			if (correctionDelayTimer.get() == 0) {
				correctionDelayTimer.start();
			}
			if (correctionDelayTimer.get() > correctionDelayTime) {
				applyCorrection = true;
				if (!directionLocked) {
					gyro.reset();
					directionLocked = true;
				}
			}
			// If manually turning then disable correction
		} else {
			applyCorrection = false;
			directionLocked = false;
			if (correctionDelayTimer.get() > 0) {
				correctionDelayTimer.stop();
				correctionDelayTimer.reset();
			}
		}

		// Apply correction if needed
		rotate = applyCorrection ? Helpers.rotateToTarget(gyro.getAngleX(), 0, correctionAngleTolerence, correctionGain)
				: rotate;
		move *= (turbo ? 1 : turboCap);
		rotate *= turnCap;
		aracadeDrive(move, rotate);
	}

	public void autoDrive(double move, double rotate) {

		// If driving and not turning then apply correction
		if (Math.abs(move) > 0 && rotate == 0) {
			// Apply correction if needed
			rotate = Helpers.rotateToTarget(gyro.getAngleX(), 0, correctionAngleTolerence, correctionGain);
			// rotate *= turnCap;
			aracadeDrive(move, rotate);
		} else {
			aracadeDrive(move, rotate);
		}
	}
	public double getAngleX(){
		return gyro.getAngleX();
	}

	private void aracadeDrive(double move, double rotate) {
		SmartDashboard.putNumber(Dashboard.MoveValue, move);
		SmartDashboard.putNumber(Dashboard.RotateValue, rotate);
		drive.arcadeDrive(move, rotate);
	}

	public double getDistance() {
		// averages the encoders distance
		return (leftEncoder.getDistance() + -rightEncoder.getDistance()) / 2;
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void toggleTurbo() {
		turbo = !turbo;
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngleX();
	}

	@Override
	protected void usePIDOutput(double output) {

		// Perform a ramping calculation on the PID output
		double rampedOutput = ramp.getNextDataPoint(output);

		// TODO: Can we use some arcade drive instead?
		this.leftGroup.pidWrite(-rampedOutput);
		this.rightGroup.pidWrite(-rampedOutput);

		System.out.println("Prefs - P: " + prefs.getPID_P() + " I: " + prefs.getPID_I() + " D: " + prefs.getPID_D()
				+ " C: " + prefs.getRamp_C());
		System.out.println("PID Output: " + output);
		SmartDashboard.putNumber(Dashboard.PIDOutput, output);
		SmartDashboard.putNumber(Dashboard.PIDRamp, rampedOutput);
		System.out.println("PID Ramped Output: " + rampedOutput);
	}

	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.DistanceTraveled, getDistance());
		SmartDashboard.putNumber("Angle for PID", this.getPosition());
		SmartDashboard.putNumber(Dashboard.PIDP, prefs.getPID_P());
		SmartDashboard.putNumber(Dashboard.GyroAngle, gyro.getAngle());
		SmartDashboard.putNumber(Dashboard.GyroAngleX, gyro.getAngleX());
		SmartDashboard.putNumber(Dashboard.GyroAngleY, gyro.getAngleY());
		SmartDashboard.putNumber(Dashboard.GyroAngleZ, gyro.getAngleZ());
		SmartDashboard.putNumber(Dashboard.DistanceTraveled, getDistance());
	}
}
