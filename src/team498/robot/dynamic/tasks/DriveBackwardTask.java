package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;

public class DriveBackwardTask extends Task {

	public static double move;

	@Override
	public void Change(double value) {
		move = value;
	}

	@Override
	public void Change(boolean value) {
	}

	@Override
	public void Init() {
	}

	@Override
	public void Run() {
	}

	@Override
	public boolean IsButton() {
		return false;
	}

}
