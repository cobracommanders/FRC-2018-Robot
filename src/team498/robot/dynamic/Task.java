package team498.robot.dynamic;

/**
 * The Task class that all tasks should extend
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public abstract class Task  implements ITask {

	private boolean initialized = false;

	/**
	 * Used by TaskGroup for the execution of tasks, ran on a loop. It runs {@link init()} once and {@link run()} every run of this method
	 */
	public final void execute() {
		if (initialized == false) {
			initialized = true;
			init();
		}
		run();
	}

	/**
	 * This is what runs on loop, override this when creating a {@link Task}
	 */
	protected abstract void run();
	/**
	 * This runs once when the {@link Task} first gets run, override this when creating a {@link Task}
	 */
	protected abstract void init();
	/**
	 * This is used under the hood to change the value of something you need the auto to change, such as whether a button is pressed or not. Override this if the {@link Task} is a button or other boolean input
	 */
	public abstract void change(boolean value);
	/**
	 * This is used under the hood to change the value of something you need the auto to change, such as how far you've pulled a trigger. Override this if the {@link Task} is an axis or other analog input
	 */
	public abstract void change(double value);
	/**
	 * This is used under the hood to check whether the task uses a boolean (button) or a double (axis, analog). Override this to either return {@code true} (if it's a boolean) or {@code false} (if it's not a boolean)
	 */
	public abstract boolean isButton();
}
