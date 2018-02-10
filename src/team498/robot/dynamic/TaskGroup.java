package team498.robot.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Timer;

public class TaskGroup {
	private Map<String, Task> tasks;
	private ArrayList<Task> passives;
	private ArrayList<InputLog> logs;
	private ArrayList<String> keys;
	private int index = 0;
	private Timer timer;

	private void _log(String s) {
		System.out.println(s);
	}

	public TaskGroup(Map<String, Task> tasks, ArrayList<Task> passives, ArrayList<InputLog> logs) {
		_log("===/TaskGroup\\===");
		_log("Setting tasks");
		this.tasks = tasks;
		_log("Setting passives");
		this.passives = passives;
		_log("Setting logs");
		this.logs = logs;
		_log("Grabbing keys");
		this.keys = new ArrayList<>(this.tasks.keySet());
		_log("Creating timer");
		timer = new Timer();
		_log("===\\TaskGroup/===");
	}

	public void Execute() throws Exception {
		_log("===/Execute\\===");
		if (logs.size() == 0) {
			_log("Log size was 0");
			return;
		}
		if (timer.get() > 15)
			return;
		if (timer.get() == 0) {
			_log("Started timer");
			timer.start();
		}
		while (timer.get() > logs.get(index).GetTime()) {
			InputLog log = logs.get(index);
			Task task = tasks.get(log.name);
			if (task.IsButton()) {
				task.Change(log.GetBoolean());
			} else {
				task.Change(log.GetDouble());
			}
			++index;
		}
		for (String s : keys) {
			tasks.get(s).Execute();
		}
		for (int i = 0; i < passives.size(); ++i) {
			passives.get(i).Execute();
		}
		_log("===\\Execute/===");
	}
}
