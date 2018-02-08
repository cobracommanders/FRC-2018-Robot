package team498.robot.dynamic;

/**
 * The Task class that all tasks should extend<br/>
 * By defualt, all tasks are button tasks unless specified
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public abstract class Task implements ITask {

	private boolean boolState;
	private double doubleState;
	private boolean initialized = false;

	@Override
	public void Change(boolean value) throws Exception {
		boolState = value;
	}

	@Override
	public void Change(double value) throws Exception {
		throw new Exception("This isn't an axis!");
	}

	@Override
	public final void Execute() {
		if (initialized == false) {
			initialized = true;
			Init();
		}
		Run();
	}

	@Override
	public boolean IsButton() {
		return true;
	}

	protected final boolean ButtonState() {
		return boolState;
	}

	protected final double AxisState() {
		return doubleState;
	}

}
