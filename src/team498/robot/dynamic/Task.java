package team498.robot.dynamic;

/**
 * The Task class that all tasks should extend
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public abstract class Task implements ITask {

	private boolean initialized = false;

	/**
	 * Used by TaskGroup for the execution of tasks
	 */
	@Override
	public final void Execute() {
		if (initialized == false) {
			initialized = true;
			Init();
		}
		Run();
	}
}
