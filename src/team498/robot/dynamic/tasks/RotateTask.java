package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;

public class RotateTask extends Task {

	public static double rotate;

	@Override
	public void change(double value) {
		rotate = value;
	}

	@Override
	public void change(boolean value) {
	}

	@Override
	public void init() {
	}

	@Override
	public void run() {
	}

	@Override
	public boolean isButton() {
		return false;
	}

}
