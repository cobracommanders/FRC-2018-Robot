package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;

public class RotateTask extends Task {

	public static double rotate;

	@Override
	public void Change(double value) {
		System.out.println("Setting rotate value");
		rotate = value;
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Run() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean IsButton() {
		return false;
	}

	@Override
	public void Change(boolean value) throws Exception {
		throw new Exception("Boy this ain't a button");
	}
}
