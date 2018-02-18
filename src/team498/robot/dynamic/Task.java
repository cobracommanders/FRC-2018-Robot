package team498.robot.dynamic;

/**
 * The Task class that all tasks should extend
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public abstract class Task implements ITask {

	private boolean boolState;
	private double doubleState;
	private boolean initialized = false;

	@Override
	public final void Execute() {
		if (initialized == false) {
			initialized = true;
			Init();
		}
		Run();
	}

	protected final boolean ButtonState() {
		return boolState;
	}

	protected final double AxisState() {
		return doubleState;
	}

}
