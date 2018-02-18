package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;

public class RotateTask extends Task {

	public static double rotate;

	@Override
	public void Change(double value) {
		rotate = value;
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
