/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot;

import team498.robot.commands.Rumble;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Operator {
	public Controller controller = new Controller(Mappings.ControllerPort);
	
	private static Operator operator = null;
	
	public Operator() {
		controller.buttonA.whileHeld(new Rumble(this.controller));
	}
	
	public static Operator getOperator() {
        operator = operator == null ? new Operator() : operator;
        return operator;
    }
}

