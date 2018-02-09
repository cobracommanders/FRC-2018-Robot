/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import team498.robot.commands.Auto;
import team498.robot.dynamic.JoystickInput;
import team498.robot.dynamic.Recorder;
import team498.robot.dynamic.TaskGroup;
import team498.robot.dynamic.tasks.DriveBackwardTask;
import team498.robot.dynamic.tasks.DriveForwardTask;
import team498.robot.dynamic.tasks.DriveTask;
import team498.robot.dynamic.tasks.RotateTask;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;
import team498.robot.subsystems.Vision;

public class Robot extends TimedRobot {

	private Operator operator = Operator.getOperator();
	// Subsystems
	private Vision vision = Vision.getVision();
	private Gyro gyro = Gyro.getGyro();

	private Recorder recorder;

	private Timer timer;

	private Auto auto = new Auto();
	private TaskGroup dynamicAuto;

	@Override
	public void robotInit() {
		// vision.startCapture();
		timer = new Timer();
		updateDashboard();
	}

	@Override
	public void disabledInit() {
		updateDashboard();
	}

	@Override
	public void disabledPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		updateDashboard();
		dynamicAuto = recorder.Build();
		// this.auto.start();
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		try {
			dynamicAuto.Execute();
		} catch (Exception e) {
			// TODO Auto-generated catch blockz
			e.printStackTrace();
		}
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		updateDashboard();
		timer.reset();
		timer.start();
		recorder = new Recorder();
		Joystick joystick = operator.controller.joystick;
		System.out.println("Fuck You");
		recorder.Assign("df", new JoystickInput(joystick, Mappings.RightTrigger, false), new DriveForwardTask());
		recorder.Assign("db", new JoystickInput(joystick, Mappings.LeftTrigger, false), new DriveBackwardTask());
		recorder.Assign("r", new JoystickInput(joystick, Mappings.LeftXAxis, false), new RotateTask());
		System.out.println("Fuck Me");
		recorder.AddPassive(new DriveTask());
		System.out.println("Fuck Us");
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		System.out.println("TelePeriod");
		if (timer.get() <= 15)
			recorder.Read();
		System.out.println("End TelePeriod");
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {

	}

	@Override
	public void testPeriodic() {
	}

	void updateDashboard() {
		operator.updateDashboard();
		gyro.updateDashboard();
		// TODO add other subsystems
	}
}
