package team498.robot.dynamic;

public abstract class PassiveTask extends Task {

	@Override
	public final void Change(boolean value) throws Exception {
	}

	@Override
	public final void Change(double value) throws Exception {
	}

	@Override
	public final boolean IsButton() {
		return false;
	}
}
