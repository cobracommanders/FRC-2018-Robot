/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;

public class Drive extends Command {

	private Operator operator = Operator.getOperator();
	private Drivetrain drivetrain;

	public Drive() {
		super("Drive");
		requires(this.drivetrain = Drivetrain.getDrivetrain());
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {

		double move = operator.controller.axisRightTrigger.getAxisValue() - operator.controller.axisLeftTrigger.getAxisValue();
		double rotate = operator.controller.axisLeftX.getAxisValue();
		
		drivetrain.drive(move, rotate);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
