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

	// Autonomous Selections
	SendableChooser<RobotStartPosition> chooserPosition = new SendableChooser<>();
	SendableChooser<AutoStrategy> chooserStrategy = new SendableChooser<>();
	RobotStartPosition autonomousPosition;
	AutoStrategy autonomousStrategy;
	CommandGroup autoCommand;

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

		// Getting game data
		GameData gameData = new GameData(ds.getGameSpecificMessage());

		// Left switch for either position
		System.out.println("choosing auto modes!");
		
		//SWITCH 
		if (autonomousStrategy == AutoStrategy.Switch) { 
			//SWITCH LEFT POSITION
			if (gameData.getOurSwitchPosition() == SwitchPosition.Left) {
				//ROBOT ON LEFT
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceLeftSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM LEFT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartLeftSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartLeftSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Left Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON CENTER
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceLeftSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM LEFT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartLeftSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartLeftSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Left Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON RIGHT
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceLeftSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM LEFT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartLeftSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartLeftSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Left Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//NO ROBOT POSITION
				} else {
					System.out.println("Error: No Robot Position, for Left Switch"); // shouldn't run
				}
			//SWITCH RIGHT POSITION
			} else if (gameData.getOurSwitchPosition() == SwitchPosition.Right) {
				//ROBOT ON LEFT
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceRightSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM RIGHT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartRightSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartRightSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Right Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON CENTER
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceRightSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM RIGHT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartRightSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartRightSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Right Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON RIGHT
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceRightSwitchStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SCALE FROM RIGHT SWTICH
					if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Left) {
						//autoCommand = new StartRightSwitchLeftScaleStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurScalePosition() == ScalePosition.Right) {
						//autoCommand = new StartRightSwitchRightScaleStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Scale from Right Switch");
						System.out.println("Incorrect If/else logic?");
					}*/
				//NO ROBOT POSITION
				} else {
					System.out.println("Error: No Robot Position, for Right Switch"); // shouldn't run
				}
			//NEITHER LEFT NOR RIGHT SWITCH POSITION
			} else {
				System.out.println("Error: No Switch Position"); // shouldn't run
			}
			
		//SCALE
		} else if (autonomousStrategy == AutoStrategy.Scale) { 	
			//SCALE LEFT POSITION
			if (gameData.getOurScalePosition() == ScalePosition.Left) {
				//ROBOT ON LEFT
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceLeftScaleStrategy();
					autoCommand.start();
					//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM LEFT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartLeftScaleLeftSwitchStrategy(); // LLL
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartLeftScaleRightSwitchStrategy(); // RLR
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}
				//ROBOT ON CENTER
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceLeftScaleStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM LEFT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartLeftScaleLeftSwitchStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartLeftScaleRightSwitchStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON RIGHT
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceLeftScaleStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM LEFT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartLeftScaleLeftSwitchStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartLeftScaleRightSwitchStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}*/
				//NO ROBOT POSITION
				} else {
					System.out.println("Error: No Robot Position, for Left Scale"); // shouldn't run
				}	
			//SCALE RIGHT POSITION
			} else if (gameData.getOurScalePosition() == ScalePosition.Right) {
				//ROBOT ON LEFT
				if (autonomousPosition == RobotStartPosition.Left) {
					autoCommand = new StartLeftPlaceRightScaleStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM RIGHT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartRightScaleLeftSwitchStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartRightScaleRightSwitchStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON CENTER
				} else if (autonomousPosition == RobotStartPosition.Center) {
					autoCommand = new StartCenterPlaceRightScaleStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM RIGHT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartRightScaleLeftSwitchStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartRightScaleRightSwitchStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}*/
				//ROBOT ON RIGHT
				} else if (autonomousPosition == RobotStartPosition.Right) {
					autoCommand = new StartRightPlaceRightScaleStrategy();
					autoCommand.start();
					/*//WHEN AUTO IS DONE, CANCEL
					if (autoCommand.isCompleted()) {
						autoCommand.cancel();
					}
					//START LEFT SWITCH AUTO FROM RIGHT SCALE
					if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Left) {
						//autoCommand = new StartRightScaleLeftSwitchStrategy();
						//autoCommand.start();
					} else if (autoCommand.isCanceled() && gameData.getOurSwitchPosition() == SwitchPosition.Right) {
						//autoCommand = new StartRightScaleRightSwitchStrategy();
						//autoCommand.start();
					} else {
						System.out.println("Not moving to Switch from Left Scale");
						System.out.println("Incorrect If/else logic?");
					}*/
				//NO ROBOT POSITION
				} else {
					System.out.println("Error: No Robot Position, for Right Scale");
				}		
			//NEITHER LEFT NOR RIGHT SCALE POSITION
			} else {
				System.out.println("Error: No Scale Position"); // shouldn't run
			}	
		//NEITHER SWITCH NOR SCALE
		} else {
			System.out.println("Error: No Auto Strategy?"); // shouldn't run
		}
		//END OF AUTO LOOP 
		updateDashboard();
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//if auto is on, turn it off; else dont do anything
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
		chooserPosition.addObject("Robot in: Center", RobotStartPosition.Center);
		chooserPosition.addObject("Robot in: Right", RobotStartPosition.Right);
		
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
		drivetrain.updateDashboard();
		accelerometer.updateDashboard();
		//TODO add other subsystems 
	}
}
