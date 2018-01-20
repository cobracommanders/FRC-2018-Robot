/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Operator;
import team498.robot.Robot;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

/**
 * An example command. You can replace me with your own command.
 */
public class Drive extends Command {

	private Drivetrain drivetrain;
	private Gyro gyro;
	private Operator operator = Operator.getOperator();

	public Drive() {
		super("Drive");
		// Required Subsystems
		requires(this.drivetrain = Drivetrain.getDrivetrain());
		requires(this.gyro = Gyro.getGyro());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		// this.drivetrain.drive(1, 0);
		double move = operator.controller.axisRightTrigger.getAxisValue()
				- operator.controller.axisLeftTrigger.getAxisValue();
		double rotate = operator.controller.axisLeftX.getAxisValue();

		double angle = gyro.getAngle();
		double angleRatio = -90 / angle;
		if (rotate == 0) {
			gyro.resetAngle();
			this.drivetrain.drive(move, rotate);
		} else
			this.drivetrain.drive(move, angleRatio);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		drivetrain.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
