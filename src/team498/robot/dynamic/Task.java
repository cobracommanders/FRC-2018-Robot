package team498.robot.dynamic;

/**
 * The Task class that all tasks should extend
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public abstract class Task {

	private boolean initialized = false;

	/**
	 * Used by TaskGroup for the execution of tasks
	 */
	public final void Execute() {
		if (initialized == false) {
			initialized = true;
			Init();
		}
		Run();
	}

	protected abstract void Run();
	protected abstract void Init();
	public abstract void Change(boolean value);
	public abstract void Change(double value);
	public abstract boolean IsButton();
}
