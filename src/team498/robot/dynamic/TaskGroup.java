package team498.robot.dynamic;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;

public class TaskGroup {
	private HashMap<String, Task> tasks;
	private ArrayList<Task> passives;
	private ArrayList<InputLog> logs;
	private ArrayList<String> keys;
	private int index = 0;
	private Timer timer;

	public TaskGroup(HashMap<String, Task> tasks, ArrayList<Task> passives, ArrayList<InputLog> logs) {
		this.tasks = tasks;
		this.passives = passives;
		this.logs = logs;
		this.keys = new ArrayList<>(this.tasks.keySet());
		timer = new Timer();
	}

	public void Execute() throws Exception {
		if (timer.get() > 15)
			return;
		if (timer.get() == 0)
			timer.start();
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
		for (int i = 0; i < passives.size(); i++) {
			passives.get(i).Execute();
		}
	}
}
