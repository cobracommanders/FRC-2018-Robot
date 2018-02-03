/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;
import team498.robot.commands.RampDrive;

public class Drivetrain extends Subsystem {
	private static final double WheelDiameter = 0.1524; // 6 inch wheels. This was converted to meters
	private static final double PulsePerRevolution = 2048; // all switches on the encoder are off
	private static final double WheelCircumference = WheelDiameter * Math.PI;
	private static final double MetersPerPulse = WheelCircumference / PulsePerRevolution;

	private static Drivetrain drivetrain = null;

	/**
	 * Provides singleton access to the drivetrain subsystem
	 * 
	 * @return Drivetrain instance
	 */
	public static Drivetrain getDrivetrain() {
		drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
		return drivetrain;
	}
	
	//TODO update the channels in the Mappings.java
	//private SpeedControllerGroup leftGroup = new SpeedControllerGroup(new Spark(Mappings.FrontLeftMotorChannel), new Spark(Mappings.BackLeftMotorChannel));
	//private SpeedControllerGroup rightGroup = new SpeedControllerGroup(new Spark(Mappings.FrontRightMotorChannel), new Spark(Mappings.BackRightMotorChannel));
	//private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
	
	private DifferentialDrive drive = new DifferentialDrive(new Spark(Mappings.FrontLeftMotorChannel), new Spark(Mappings.FrontRightMotorChannel));

	public Encoder leftEncoder = new Encoder(Mappings.LeftEncoderDigitalSource1, Mappings.LeftEncoderDigitalSource2);
	public Encoder rightEncoder = new Encoder(Mappings.RightEncoderDigitalSource1, Mappings.RightEncoderDigitalSource2);

	public Drivetrain() {
		super("Drivetrain");
		leftEncoder.setDistancePerPulse(MetersPerPulse);
		rightEncoder.setDistancePerPulse(MetersPerPulse);
		resetEncoders();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new RampDrive()); // uses rampdrive
	}

	public void drive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public double getDistance() {
		//averages the encoders distance 
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber(Dashboard.DistanceTraveled, getDistance());
	}

}
