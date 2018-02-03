/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.auto.*;
import team498.robot.commands.auto.common.GameData;
import team498.robot.commands.auto.common.ScalePosition;
import team498.robot.commands.auto.common.SwitchPosition;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();

	private DriverStation ds = DriverStation.getInstance();

	// Subsystems
	//private Drivetrain drivetrain = Drivetrain.getDrivetrain();
	private Vision vision = Vision.getVision();
	private Gyro gyro = Gyro.getGyro();

	// Autonomous Selections
	SendableChooser<RobotStartPosition> chooserPosition = new SendableChooser<>();
	SendableChooser<AutoStrategy> chooserStrategy = new SendableChooser<>();
	RobotStartPosition autonomousPosition;
	AutoStrategy autonomousStrategy;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		vision.startCapture();
		updateDashboard();
		addAutonomousChoices();
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

		// Start autonomous command
		GameData gameData = new GameData(ds.getGameSpecificMessage());
		// not doing anything with game specific message yet
		
		// For Left position and switch
		/*if (autonomousOption.getName() == "Robot Start Left, Switch") {
			if (gameData.charAt(0) == 'L') {
				new StartLeftPlaceLeftSwitchStrategy(); //command for Left position, Left Switch
			} else if (gameData.charAt(0) == 'R') {
				new StartLeftPlaceRightSwitchStrategy(); //command for Left position, Right Switch
			} else {
				System.out.println("Error. No auto for switch, left position");
			}
		}*/
		
		//Left switch for either position
		if (gameData.getOurSwitchPosition() == SwitchPosition.Left) {
			if (autonomousPosition == RobotStartPosition.Left) {
				if (autonomousStrategy == AutoStrategy.LeftSwitch) {
					new StartLeftPlaceLeftSwitchStrategy().start();
				}
			} else if (autonomousPosition == RobotStartPosition.Right) {
				if (autonomousStrategy == AutoStrategy.LeftSwitch) {
					new StartRightPlaceLeftSwitchStrategy().start();
				}
			} else {
				System.out.println("Error, no auto for Left Switch for either position??");
			}
		}
		
		//Right switch for either position
		if (gameData.getOurSwitchPosition() == SwitchPosition.Right) {
			if (autonomousPosition == RobotStartPosition.Left) {
				if (autonomousStrategy == AutoStrategy.RightSwitch) {
					new StartLeftPlaceRightSwitchStrategy().start();
				}
			} else if (autonomousPosition == RobotStartPosition.Right) {
				if (autonomousStrategy == AutoStrategy.RightSwitch) {
					new StartRightPlaceRightSwitchStrategy().start();
				}
			} else {
				System.out.println("Error, no auto for Right Switch for either position??");
			}
		}
		
		//Left scale for either position
		if (gameData.getOurScalePosition() == ScalePosition.Left) {
			if (autonomousPosition == RobotStartPosition.Left) {
				if (autonomousStrategy == AutoStrategy.LeftScale) {
					new StartLeftPlaceLeftScaleStrategy().start();
				}
			} else if (autonomousPosition == RobotStartPosition.Right) {
				if (autonomousStrategy == AutoStrategy.LeftScale) {
					new StartRightPlaceLeftScaleStrategy().start();
				}
			} else {
				System.out.println("Error, no auto for Left Scale for either position??");
			}
		}
		
		//Right scale for either position
		if (gameData.getOurScalePosition() == ScalePosition.Right) {
			if (autonomousPosition == RobotStartPosition.Left) {
				if (autonomousStrategy == AutoStrategy.RightScale) {
					new StartLeftPlaceRightScaleStrategy().start();

				}
			} else if (autonomousPosition == RobotStartPosition.Right) {
				if (autonomousStrategy == AutoStrategy.RightScale) {
					new StartRightPlaceRightScaleStrategy().start();
				}
			} else {
				System.out.println("Error, no auto for Right Scale for either position??");
			}
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
		/*if (autonomousOption != null)
			autonomousOption.cancel();*/

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

		chooserPosition.addObject("Robot Left", RobotStartPosition.Left);
		chooserPosition.addObject("Robot Right", RobotStartPosition.Right);
		
		chooserStrategy.addObject("Left Switch", AutoStrategy.LeftSwitch);
		chooserStrategy.addObject("Right Switch", AutoStrategy.RightSwitch);
		chooserStrategy.addObject("Left Scale", AutoStrategy.LeftScale);
		chooserStrategy.addDefault("Right Scale", AutoStrategy.RightScale);
		// chooser.addDefault("None", null);
		// chooser.addObject("AutoCrossLine", new AutoCrossLine());
		SmartDashboard.putData("Autonomous Position", chooserPosition);
		SmartDashboard.putData("Autonomous Strategy", chooserStrategy);
	}

	public void updateDashboard() {
		operator.updateDashboard();
		gyro.updateDashboard();
		// TODO add other subsystems
	}
}
