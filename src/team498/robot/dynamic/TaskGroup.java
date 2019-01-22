package team498.robot.dynamic;

import java.util.ArrayList;
import java.util.Map;

import edu.wpi.first.wpilibj.Timer;

/**
 * Behaves similar to the way CommandGroup does for Commands, is built by {@link Recorder} when it builds a recorded autonomous file
 * 
 * @version 1.0
 * 
 * @author Micah Neitz<br/>
 *         Team 498
 *
 */
public final class TaskGroup extends PassiveTask {
	private Map<String, Task> tasks;
	private ArrayList<PassiveTask> passives;
	private ArrayList<InputLog> logs;
	private ArrayList<String> keys;
	private int index = 0;
	private Timer timer;

	/**
	 * Used by Recorder to build the task group, it doesn't make sense to be used
	 * outside of its usage in Recorder
	 * 
	 * @param tasks
	 *            The list of tasks sorted by their names
	 * @param passives
	 *            The list of passive tasks
	 * @param logs
	 *            The list of log recordings to use
	 */
	public TaskGroup(Map<String, Task> tasks, ArrayList<PassiveTask> passives, ArrayList<InputLog> logs) {
		this.tasks = tasks;
		this.passives = passives;
		this.logs = logs;
		this.keys = new ArrayList<>(this.tasks.keySet());
		timer = new Timer();
	}
	
	/**
	 * Should be put in autoPeriodic, or ran on loop during autonomous
	 */
	@Override
	protected void run() {
		// Checks if there is no logs. If so, don't continue
		if (logs.size() == 0) {
			return;
		}

		// Checks if you have been going longer than the autonomous period. If so, don't
		// continue
		if (timer.get() > 15)
			return;

		// If the timer has passed your timestamp, change the value in your task
		// accordingly
		while (timer.get() > logs.get(index).getTime()) {
			InputLog log = logs.get(index);
			if (tasks.get(log.getName()).isButton()) {
				tasks.get(log.getName()).change(log.getBoolean());
				System.out.println(log.getBoolean());
			} else {
				tasks.get(log.getName()).change(log.getDouble());
				System.out.println(log.getDouble());
			}
			++index;
			System.out.println(index + "/" + logs.size());
		}

		// Execute all the active tasks
		for (String s : keys) {
			tasks.get(s).execute();
		}

		// Execute all the passive tasks
		for (int i = 0; i < passives.size(); ++i) {
			passives.get(i).execute();
		}
	}

	@Override
	protected void init() {
		timer.start();
	}

}
