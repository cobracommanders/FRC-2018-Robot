/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.commands.auto.*;
import team498.robot.subsystems.*;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();
	private DriverStation ds = DriverStation.getInstance();

	// Subsystems
	private Drivetrain drivetrain = Drivetrain.getDrivetrain();
	
	// Stuff
	private double move;
	private double rotate;

	// Autonomous Selections
	SendableChooser<Command> chooser = new SendableChooser<>();
	Command autonomousCommand;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override

	public void autonomousInit() {
		// get selected command
		autonomousCommand = chooser.getSelected();
		
		// Start autonomous command
        if (autonomousCommand != null)
            autonomousCommand.start();
        
        String gameData;
		gameData = ds.getGameSpecificMessage();
    }
		// TestingAuto auto = new TestingAuto(); //randy's auto
		/*RotateLeft driveL = new RotateLeft();
		RotateRight driveR = new RotateRight();
		DriveStraight driveStraight = new DriveStraight();
		Stop stop = new Stop();*/
		
		// auto.start(); //starts auto
		 // gets game messgae on driver
												// station
		/*if (gameData.charAt(0) == 'L') {
			driveL.start();
		} else if (gameData.charAt(0) == 'R') {
			driveR.start();
		} else {
			stop.start();
		}*/
		
		/*if (gameData.charAt(1) == 'L') { //if scale is left, move forward
			driveStraight.start();
		} else if (gameData.charAt(1) == 'R') { //if scale is right, move backwards
			driveReverse.start();
		}*/

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	private void addAutonomousChoices() {

        // Add available autonomous commands with the SmartDashboard
        chooser.addDefault("None", null);
        //chooser.addObject("AutoCrossLine", new AutoCrossLine());
        // TODO: Add autonomous modes
        
       // SmartDashboard.putData(Dashboard.AutonomousChooser, chooser);
    }
	
	private void updateDashboard() {

        /*operator.updateDashboard();
        drivetrain.updateDashboard();
        vision.updateDashboard();
        gyro.updateDashboard();*/

    }
}
