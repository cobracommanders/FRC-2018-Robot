package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;
import team498.robot.subsystems.Drivetrain;

public class DriveTask extends Task {

	Drivetrain drivetrain;
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		drivetrain = Drivetrain.getDrivetrain();
	}

	@Override
	public void Run() {
		// TODO Auto-generated method stub
		double move = DriveForwardTask.move - DriveBackwardTask.move;
		double rotate = RotateTask.rotate;
		
		drivetrain.drive(move, rotate);
	}

}
