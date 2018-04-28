/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;


import team498.robot.commands.IntakeCorrection;
import team498.robot.commands.ManualArm;
import team498.robot.commands.ShootBall;
import team498.robot.commands.ToggleTurbo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Operator {

	private static Operator operator = null;

	public static Operator getOperator() {
		operator = operator == null ? new Operator() : operator;
		return operator;
	}

	public Controller controller = new Controller(Mappings.ControllerPort);

	public Operator() {
		//Turbo mode toggle
		controller.buttonY.whenPressed(new ToggleTurbo());


		//Arm Up
		controller.leftBumper.whileHeld(new ManualArm(.6));
		//Arm Down
		controller.rightBumper.whileHeld(new ManualArm(-.6));
		
		//Climb
		controller.rightJoyPress.whileHeld(new ManualArm(-1));

		//Intake Automatic Correction
		controller.select.whenPressed(new IntakeCorrection(.4));
		
		//shoot baseball
		controller.buttonA.whenPressed(new ShootBall(.3));
		
	}

	public void updateDashboard() {
		controller.updateDashboard();
	}
}
