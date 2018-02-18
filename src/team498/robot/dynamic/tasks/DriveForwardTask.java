package team498.robot.dynamic.tasks;

import team498.robot.dynamic.Task;

public class DriveForwardTask extends Task {

	public static double move;
	
	@Override
	public void Change(double value) {
		move = value;
	}
	
	@Override
	public void Change(boolean value) throws Exception {
		throw new Exception("Boy this ain't a button");
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
}
