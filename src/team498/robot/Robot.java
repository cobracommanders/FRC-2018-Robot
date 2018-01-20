/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.Auto;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();

	// Subsystems
	private Drivetrain drivetrain = Drivetrain.getDrivetrain();
	private Vision vision = Vision.getVision();
	private Gyro gyro = Gyro.getGyro();

	private Auto auto = new Auto();
	
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
		updateDashboard();
		this.auto.start();
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		updateDashboard();
		this.auto.cancel();
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
		gyro.updateDashboard();
		//TODO add other subsystems 
	}
}
