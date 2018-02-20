package team498.robot.dynamic.tasks;

import team498.robot.dynamic.PassiveTask;
import team498.robot.subsystems.Drivetrain;

public class DriveTask extends PassiveTask {

	Drivetrain drivetrain;

	@Override
	public void Init() {
		drivetrain = Drivetrain.getDrivetrain();
	}

	@Override
	public void Run() {
		double move = DriveForwardTask.move - DriveBackwardTask.move;
		double rotate = RotateTask.rotate;
		System.out.println("M" + move);
		drivetrain.drive(move, rotate);
	}
}
