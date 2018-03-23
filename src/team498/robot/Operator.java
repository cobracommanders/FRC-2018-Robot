/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;


import team498.robot.commands.ManualArm;
import team498.robot.commands.ManualClimb;
import team498.robot.commands.ToggleArmRestriction;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.ToggleIntake;
import team498.robot.commands.ToggleTurbo;
import team498.robot.commands.auto.common.AutoArmPosition;

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
		// Intake in
		controller.buttonX.whenPressed(new ToggleIntake(.6,.6));
		controller.buttonB.whenPressed(new ToggleIntake(-1,-1));
		controller.buttonA.whenPressed(new ToggleClamps());
		controller.buttonY.whenPressed(new ToggleTurbo());

		controller.rightJoyPress.whenPressed(new ManualClimb(-1));

		controller.leftBumper.whileHeld(new ManualArm(0.6));
		controller.rightBumper.whileHeld(new ManualArm(-0.6));
		
		controller.rightJoyPress.whileHeld(new ToggleArmRestriction());
		
		controller.start.whenPressed(new ToggleIntake(.24,-.18));
		controller.select.whenPressed(new ToggleIntake(-.18,.24));
		
		//controller.leftBumper.whenPressed(new AutoArmPosition(0));
		//controller.rightBumper.whenPressed(new AutoArmPosition(1));

	}

	public void updateDashboard() {
		controller.updateDashboard();
	}
}
