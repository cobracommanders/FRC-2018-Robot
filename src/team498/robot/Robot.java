/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Test
package team498.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.auto.AutoStrategy;
import team498.robot.commands.auto.GameData;
import team498.robot.commands.auto.RobotStartPosition;
import team498.robot.commands.auto.ScalePosition;
import team498.robot.commands.auto.StartCenterPlaceLeftScaleStrategy;
import team498.robot.commands.auto.StartCenterPlaceLeftSwitchStrategy;
import team498.robot.commands.auto.StartCenterPlaceRightScaleStrategy;
import team498.robot.commands.auto.StartCenterPlaceRightSwitchStrategy;
import team498.robot.commands.auto.StartLeftPlaceLeftScaleStrategy;
import team498.robot.commands.auto.StartLeftPlaceLeftSwitchStrategy;
import team498.robot.commands.auto.StartLeftPlaceRightScaleStrategy;
import team498.robot.commands.auto.StartLeftPlaceRightSwitchStrategy;
import team498.robot.commands.auto.StartRightPlaceLeftScaleStrategy;
import team498.robot.commands.auto.StartRightPlaceLeftSwitchStrategy;
import team498.robot.commands.auto.StartRightPlaceRightScaleStrategy;
import team498.robot.commands.auto.StartRightPlaceRightSwitchStrategy;
import team498.robot.commands.auto.SwitchPosition;
import team498.robot.subsystems.Gyro;
//import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();

	private DriverStation ds = DriverStation.getInstance();

	// Subsystems
	// private Drivetrain drivetrain = Drivetrain.getDrivetrain();
	// private Vision vision = Vision.getVision();
	private Gyro gyro = Gyro.getGyro();
	// private DigitBoard digitBoard = DigitBoard.getDigitBoard();

	// Autonomous Selections
	SendableChooser<RobotStartPosition> chooserPosition = new SendableChooser<>();
	SendableChooser<AutoStrategy> chooserStrategy = new SendableChooser<>();
	RobotStartPosition autonomousPosition;
	AutoStrategy autonomousStrategy;
	Command autoCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// vision.startCapture();
		addAutonomousChoices();
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
		// get selected command
		autonomousPosition = chooserPosition.getSelected();
		autonomousStrategy = chooserStrategy.getSelected();

		// Getting game data
		GameData gameData = new GameData(ds.getGameSpecificMessage());

		// Left switch for either position
		System.out.println("choosing auto modes!");

		if (autonomousStrategy == AutoStrategy.Switch) {
			if (gameData.getOurSwitchPosition() == SwitchPosition.Left) {
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceLeftSwitchStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceLeftSwitchStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceLeftSwitchStrategy();
					autoCommand.start();
				} else {
					System.out.println("Error: No Robot Position, for Left Switch"); // shouldn't run
				}
			} else if (gameData.getOurSwitchPosition() == SwitchPosition.Right) {
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceRightSwitchStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceRightSwitchStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceRightSwitchStrategy();
					autoCommand.start();
				} else {
					System.out.println("Error: No Robot Position, for Right Switch"); // shouldn't run
				}
			} else {
				System.out.println("Error: No Switch Position"); // shouldn't run
			}
		} else if (autonomousStrategy == AutoStrategy.Scale) {
			if (gameData.getOurScalePosition() == ScalePosition.Left) {
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceLeftScaleStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceLeftScaleStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceLeftScaleStrategy();
					autoCommand.start();
				} else {
					System.out.println("Error: No Robot Position, for Left Scale"); // shouldn't run
				}
			} else if (gameData.getOurScalePosition() == ScalePosition.Right) {
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceRightScaleStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceRightScaleStrategy();
					autoCommand.start();
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceRightScaleStrategy();
					autoCommand.start();
				} else {
					System.out.println("Error: No Robot Position, for Right Scale");
				}
			} else {
				System.out.println("Error: No Scale Position"); // shouldn't run
			}
		} else {
			System.out.println("Error: No Auto Strategy?"); // shouldn't run
		}

		updateDashboard();
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
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

	private void addAutonomousChoices() {

		chooserPosition.addDefault("Robot in: Left", RobotStartPosition.Left);
		chooserPosition.addObject("Robot in: Right", RobotStartPosition.Right);
		chooserPosition.addObject("Robot in: Center", RobotStartPosition.Center);

		chooserStrategy.addDefault("Going for: Switch", AutoStrategy.Switch);
		chooserStrategy.addObject("Going for: Scale", AutoStrategy.Scale);
	}

	public void updateDashboard() {

		/*
		 * SmartDashboard.putData(chooserPosition);
		 * SmartDashboard.putData(chooserStrategy);
		 */

		SmartDashboard.putData("Autonomous Position", chooserPosition);
		SmartDashboard.putData("Autonomous Strategy", chooserStrategy);
		SmartDashboard.putString("Position Choice", autonomousPosition != null ? autonomousPosition.toString() : "");
		SmartDashboard.putString("Strategy Choice", autonomousStrategy != null ? autonomousStrategy.toString() : "");
		operator.updateDashboard();
		gyro.updateDashboard();
		// TODO add other subsystems
	}
}
