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
import team498.robot.commands.Auto;
import team498.robot.subsystems.Arm;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Intake;
import team498.robot.subsystems.Vision;
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
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Accelerometer;
import team498.robot.subsystems.HapticFeedback;
import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();
	
	private DriverStation ds = DriverStation.getInstance();

	// Subsystems
	private Vision vision = Vision.getVision();
	private Accelerometer accelerometer = Accelerometer.getAccelerometer();
	private HapticFeedback hapticFeedback = HapticFeedback.getHapticFeedback();
	
	//private Vision vision = Vision.getVision();
	private Drivetrain drivetrain = Drivetrain.getDrivetrain();

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
		//vision.startCapture();
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
		
		drivetrain.resetGyro();
		
		// get selected command
		autonomousPosition = chooserPosition.getSelected();
		autonomousStrategy = chooserStrategy.getSelected();

		// Getting game data
		GameData gameData = new GameData(ds.getGameSpecificMessage());

		// Left switch for either position
		System.out.println("choosing auto modes!");
		
		if (gameData.getOurSwitchPosition() == SwitchPosition.Left) { //if first character is L
			if (autonomousPosition == RobotStartPosition.Left) { //check if robot is Left
				if (autonomousStrategy == AutoStrategy.Switch) { //check if Strategy is switch
					System.out.println("Running Left, Left Switch"); //debug line
					autoCommand = new StartLeftPlaceLeftSwitchStrategy(); 
					autoCommand.start(); //run left left switch
				}
			} else if (autonomousPosition == RobotStartPosition.Right) { //check if robot is Right
				if (autonomousStrategy == AutoStrategy.Switch) { //check if Strategy is switch
					System.out.println("Running Right, Left Switch"); //debug line
					autoCommand = new StartRightPlaceLeftSwitchStrategy();
					autoCommand.start(); //run right left switch
				}
			} else if (autonomousPosition == RobotStartPosition.Center) { //check if robot is Center
				if (autonomousStrategy == AutoStrategy.Switch) { //check if Strategy is switch
					System.out.println("Running Center, Left Switch"); //debug line
					autoCommand = new StartCenterPlaceLeftSwitchStrategy();
					autoCommand.start(); //run center left switch
				}
			} else {
				System.out.println("Error, no auto for Left Switch for either position??");
			}
		}

		// Right switch for either position
		else if (gameData.getOurSwitchPosition() == SwitchPosition.Right) { //if first character is R
			if (autonomousPosition == RobotStartPosition.Left) { //check if robot is Left
				if (autonomousStrategy == AutoStrategy.Switch) { //check if Strategy is switch
					System.out.println("Running Left, Right Switch"); //debug line
					autoCommand = new StartLeftPlaceRightSwitchStrategy();
					autoCommand.start(); //run left right switch
				}
			} else if (autonomousPosition == RobotStartPosition.Right) { //check if robot is Right
				if (autonomousStrategy == AutoStrategy.Switch) { //check if strategy is switch
					System.out.println("Running Right, Right Switch"); //debug line
					autoCommand = new StartRightPlaceRightSwitchStrategy();
					autoCommand.start(); //run right right switch
				}
			} else if (autonomousPosition == RobotStartPosition.Center) { //check if robot is Center
				if (autonomousStrategy == AutoStrategy.Switch) { //check if strategy is switch
					System.out.println("Running Center, Right Switch"); //debug line
					autoCommand = new StartCenterPlaceRightSwitchStrategy();
					autoCommand.start(); //run center right switch
				}
			} else {
				System.out.println("Error, no auto for Right Switch for either position??");
			}
		}

		// Left scale for either position
		else if (gameData.getOurScalePosition() == ScalePosition.Left) { //If second character is L
			if (autonomousPosition == RobotStartPosition.Left) { //check if robot is Left
				if (autonomousStrategy == AutoStrategy.Scale) { //check if Strategy is scale
					System.out.println("Running Left, Left Scale"); //debug line
					autoCommand = new StartLeftPlaceLeftScaleStrategy();
					autoCommand.start(); //run left left scale 
				}
			} else if (autonomousPosition == RobotStartPosition.Right) { //check if robot is Right
				if (autonomousStrategy == AutoStrategy.Scale) { //check if Strategy is scale
					System.out.println("Running Right, Left Scale"); //debug line
					autoCommand = new StartRightPlaceLeftScaleStrategy();
					autoCommand.start(); //run right left scale
				}
			} else if (autonomousPosition == RobotStartPosition.Center) { //check if robot is Center
				if (autonomousStrategy == AutoStrategy.Scale) { //check if Strategy is scale
					System.out.println("Running Center, Left Scale"); //debug line
					autoCommand = new StartCenterPlaceLeftScaleStrategy();
					autoCommand.start(); //run center left scale
				}
			} else {
				System.out.println("Error, no auto for Left Scale for either position??");
			}
		}

		// Right scale for either position
		else if (gameData.getOurScalePosition() == ScalePosition.Right) { // if second character is R
			if (autonomousPosition == RobotStartPosition.Left) { //check if robot is Left
				if (autonomousStrategy == AutoStrategy.Scale) { //check if Strategy is scale
					System.out.println("Running Left, Right Scale"); //debug line
					autoCommand = new StartLeftPlaceRightScaleStrategy();
					autoCommand.start(); //run left right scale

				}
			} else if (autonomousPosition == RobotStartPosition.Right) { //check if robot is Right
				if (autonomousStrategy == AutoStrategy.Scale) { //check if strategy is scale
					System.out.println("Running Right, Right Scale"); //debug line
					autoCommand = new StartRightPlaceRightScaleStrategy();
					autoCommand.start(); //run right right scale
				}
			} else if (autonomousPosition == RobotStartPosition.Center) { //check if robot is Center
				if (autonomousStrategy == AutoStrategy.Scale) { //check if strategy is scale
					System.out.println("Running Center, Right Scale"); //debug line
					autoCommand = new StartCenterPlaceRightScaleStrategy();
					autoCommand.start(); //run center right scale
				}
			} else {
				System.out.println("Error, no auto for Right Scale for either position??");
			}
		} 
		
		else {
			System.out.println("Error. Invalid combination of auto options"); //shouldn't run
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
		
		drivetrain.resetGyro();
		
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
		
		/*SmartDashboard.putData(chooserPosition);
		SmartDashboard.putData(chooserStrategy);*/
		
		SmartDashboard.putData("Autonomous Position", chooserPosition);
		SmartDashboard.putData("Autonomous Strategy", chooserStrategy);
		SmartDashboard.putString("Position Choice", autonomousPosition != null ? autonomousPosition.toString() : "");
		SmartDashboard.putString("Strategy Choice", autonomousStrategy != null ? autonomousStrategy.toString() : "");
		operator.updateDashboard();
		drivetrain.updateDashboard();
		accelerometer.updateDashboard();
		//TODO add other subsystems 
	}
}
