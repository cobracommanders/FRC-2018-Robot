/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Test
package team498.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
//import team498.robot.commands.Auto;
import team498.robot.commands.auto.TestingAuto;
//import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Vision;
import team498.robot.commands.auto.AutoTurnRandyPid;
import team498.robot.subsystems.PidAutoTurnSubsystem;
//import team498.robot.subsystems.DigitBoard;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();
	private PidAutoTurnSubsystem pidAutoTurnSubsystem = PidAutoTurnSubsystem.getPidAutoTurnSubsystem();

	// Subsystems
	
	//private Drivetrain drivetrain = Drivetrain.getDrivetrain();
	private Vision vision = Vision.getVision();
	private Gyro gyro = Gyro.getGyro();
	//private DigitBoard digitBoard = DigitBoard.getDigitBoard();

	//private Auto auto = new Auto();
	private TestingAuto testAuto = new TestingAuto();
	
	
	@Override
	public void robotInit() {
		//digitBoard.displayVoltageDigitBoard();
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
		//this.auto.start();
		this.testAuto.start(); //pid currently on
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		updateDashboard();
		this.testAuto.cancel();
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
		pidAutoTurnSubsystem.updateDashboard();
		operator.updateDashboard();
		gyro.updateDashboard();
		//TODO add other subsystems 
	}
}
