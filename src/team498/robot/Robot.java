/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.auto.*;
import edu.wpi.first.wpilibj.DriverStation;
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
	SendableChooser<Command> chooser = new SendableChooser<>();
	Command autonomousCommand;

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
		autonomousCommand = chooser.getSelected();

		// Start autonomous command
		String gameData;
		gameData = ds.getGameSpecificMessage(); // not doing anything with game specific message yet

		/*if (autonomousCommand != null) {
			if (gameData.charAt(0) == 'L') {
				autonomousCommand.start();
			}
		}*/
		
		// For Left position and switch
		if (autonomousCommand.getName() == "Robot Start Left, Switch") {
			if (gameData.charAt(0) == 'L') {
				new StartLeftPlaceLeftSwitchStrategy(); //command for Left position, Left Switch
			} else if (gameData.charAt(0) == 'R') {
				new StartLeftPlaceRightSwitchStrategy(); //command for Left position, Right Switch
			} else {
				System.out.println("Error. No auto for switch, left position");
			}
		}
		
		//for Left position and scale
		if (autonomousCommand.getName() == "Robot Start Left, Scale") {
			if (gameData.charAt(1) == 'L') {
				new StartLeftPlaceLeftScaleStrategy(); //command for Left position, Left Scale
			} else if (gameData.charAt(1) == 'R') {
				new StartLeftPlaceRightScaleStrategy(); //command for Left position, Right Scale
			} else {
				System.out.println("Error. No auto for scale, left position");
			}
		}
		
		//for Right position and switch
		if (autonomousCommand.getName() == "Robot Start Right, Switch") {
			if (gameData.charAt(0) == 'L') {
				new StartRightPlaceLeftSwitchStrategy(); //command for Right position, Left Switch
			} else if (gameData.charAt(0) == 'R') { 
				new StartRightPlaceRightSwitchStrategy(); //command for Right position, Right Switch
			} else {
				System.out.println("Error. No auto for switch, right position");
			}
		}
		
		//for right position and scale
		if (autonomousCommand.getName() == "Robot Start Right, Scale") {
			if (gameData.charAt(1) == 'L') {
				new StartRightPlaceLeftScaleStrategy(); //command for Right position, Left Scale
			} else if (gameData.charAt(1) == 'R') {
				new StartRightPlaceRightScaleStrategy(); //command for Right position, Right Scale
			} else {
				System.out.println("Error. No auto for scale, right position");
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();

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

		// Add available autonomous commands with the SmartDashboard
		// chooser.addDefault("None", null);
		// TODO add actual commands to command groups
		/*chooser.addDefault("Start Left Place Left Switch", new StartLeftPlaceLeftSwitchStrategy());
		chooser.addObject("Start Left Place Left Scale", new StartLeftPlaceLeftScaleStrategy());
		chooser.addObject("Start Left Place Right Switch", new StartLeftPlaceRightSwitchStrategy());
		chooser.addObject("Start Left Place Right Scale", new StartLeftPlaceRightScaleStrategy());
		chooser.addObject("Start Right Place Left Switch", new StartRightPlaceLeftSwitchStrategy());
		chooser.addObject("Start Right Place Left Scale", new StartRightPlaceLeftScaleStrategy());
		chooser.addObject("Start Right Place Right Switch", new StartRightPlaceRightSwitchStrategy());
		chooser.addObject("Start Right Place Right Scale", new StartRightPlaceRightScaleStrategy());*/
		chooser.addDefault("Robot Starting Left", new StartLeftPlaceLeftSwitchStrategy()); //TODO change command!!
		// chooser.addObject("AutoCrossLine", new AutoCrossLine());
		SmartDashboard.putData("AutonomousChooser", chooser);
		// SmartDashboard.putData(Dashboard.AutonomousChooser, chooser);
	}

	public void updateDashboard() {
		operator.updateDashboard();
		gyro.updateDashboard();
		// TODO add other subsystems
	}
}
