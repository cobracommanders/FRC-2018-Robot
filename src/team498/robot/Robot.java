/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package team498.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;


import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Accelerometer;
import team498.robot.subsystems.Arm;
import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();

	private DriverStation ds = DriverStation.getInstance();

	// Subsystems
	private Vision vision = Vision.getVision();
	private Arm arm = Arm.getArm();
	private Accelerometer accelerometer = Accelerometer.getAccelerometer();
	private Drivetrain drivetrain = Drivetrain.getDrivetrain();

	@Override
	public void robotInit() {
		vision.startCapture();
		updateDashboard();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		// if auto is on, turn it off; else dont do anything
		updateDashboard();
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}

	public void updateDashboard() {
		operator.updateDashboard();
		drivetrain.updateDashboard();
		accelerometer.updateDashboard();
		arm.updateDashboard();
	}
}
