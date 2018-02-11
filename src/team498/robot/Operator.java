/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import team498.robot.commands.ToggleLift;
import team498.robot.commands.ManualArm;
import team498.robot.commands.ManualIntake;
import team498.robot.commands.ToggleTurbo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Operator {

	private boolean xToggle = false;
	private boolean bToggle = false;
	private static Operator operator = null;

	public static Operator getOperator() {
		operator = operator == null ? new Operator() : operator;
		return operator;
	}

	public Controller controller = new Controller(Mappings.ControllerPort);

	public Operator() {
		// controller.buttonY.whenPressed(new LaunchCatapult("LaunchCatapult", 5));
		// //where we call time

		// Intake in
		// controller.buttonX.whenPressed(new ManualIntake(.6,.6));

		//controller.buttonB.whenPressed(new ManualIntake(-1, -1));
		
		if (controller.buttonX.get() == true && xToggle == false) {
			xToggle = true;
			new ManualIntake(.6, .6);
		} else if (controller.buttonX.get() == true && xToggle == true) {
			xToggle = false;
			new ManualIntake(0, 0);
		} 
		
		if (controller.buttonB.get() == true && bToggle == false) {
			bToggle = true;
			new ManualIntake(-1, -1);
		} else if (controller.buttonB.get() == true && bToggle == true) {
			bToggle = false;
			new ManualIntake(0, 0);
		}
		// controller.buttonA.whenPressed(new ManualIntake(0,0));
		controller.buttonY.whenPressed(new ToggleTurbo());

		controller.rightJoyPress.whenPressed(new ToggleLift());

		controller.leftBumper.whileHeld(new ManualArm(1));
		controller.rightBumper.whileHeld(new ManualArm(-1));

	}

	public void updateDashboard() {
		controller.updateDashboard();
	}
}
