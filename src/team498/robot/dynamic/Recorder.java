package team498.robot.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.Timer;

/**
 * The Dynamic Autonomous recorder class for the recording, saving, loading, and
 * building of dynamic autonomous modes.
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 */
public final class Recorder {

	private static final String DIRECTORY = "/home/lvuser/frc/dynamicauto/";
	private Map<String, Task> tasks;
	private Map<String, JoystickInput> inputs;
	private ArrayList<Task> passives;
	private ArrayList<JoyState> oldStates;
	private ArrayList<JoyState> newStates;
	private ArrayList<InputLog> logs;
	private Timer timer;

	private void _log(String s) {
		System.out.println(s);
	}

	/**
	 * Creates a new recorder object
	 * 
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public Recorder() {
		_log("===/Recorder\\===");
		_log("Creating task map");
		tasks = new HashMap<String, Task>();
		_log("Creating input map");
		inputs = new HashMap<String, JoystickInput>();
		_log("Creating passives");
		passives = new ArrayList<Task>();
		_log("Creating states");
		oldStates = newStates = new ArrayList<>();
		_log("Creating timer");
		timer = new Timer();
		_log("Creating logs");
		logs = new ArrayList<InputLog>();
		_log("===\\Recorder/===");
	}

	/**
	 * Needs to be run on loop while recording inputs
	 * 
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void Read() {
		_log("===/Read\\===");
		if (timer.get() == 0) {
			timer.start();
			_log("Timer started");
		}
		double time = timer.get();
		oldStates = newStates;
		newStates = _grabValues();
		if (oldStates.size() != newStates.size()) {
			_log(String.format("Sizes were different: %s != %s", oldStates.size(), newStates.size()));
			return;
		}
		if (oldStates.size() == 0) {
			_log("Old states was size 0");
			return;
		}
		for (int i = 0; i < newStates.size(); i++) {
			JoyState newState = newStates.get(i);
			if (newState.isBool) {
				_log("Checking bool");
				if (newState.boolState != oldStates.get(i).boolState) {
					_log(newState.name + " changed to " + newState.boolState);
					InputLog input = new InputLog(newState.name, newState.boolState, time);
					logs.add(input);
				}
			} else {
				_log("Checking double");
				if (newState.doubleState != oldStates.get(i).doubleState) {
					_log(newState.name + " changed to " + newState.doubleState);
					InputLog input = new InputLog(newState.name, newState.doubleState, time);
					logs.add(input);
				}
			}
		}
		_log("===\\Read/===");
	}

	/**
	 * Saves current autonomous
	 * 
	 * @param name
	 *            The name of the autonomous you are saving
	 * @throws IOException
	 *             Throws when you don't have access, or another error occurs
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void Save(String name) throws IOException {
		String text = _compileString();
		File directoryFile = new File(DIRECTORY);
		if (!directoryFile.exists())
			directoryFile.mkdir();
		FileWriter fw = new FileWriter(DIRECTORY + name + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(text);
		bw.close();
	}

	/**
	 * Loads an autonomous from memory
	 * 
	 * @param name
	 *            The name of the autonomous you are loading
	 * @throws IOException
	 *             Happens when you have no autonomous to load, or you typed in the
	 *             name wrong
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void Load(String name) throws IOException {
		File directoryFile = new File(DIRECTORY);
		if (!directoryFile.exists())
			throw new IOException("You haven't saved a single autonomous yet!");
		FileReader fr = new FileReader(DIRECTORY + name + ".txt");
		BufferedReader br = new BufferedReader(fr);
		String text = br.readLine();
		br.close();
		if (text == null)
			throw new IOException("That file was empty");
		_parseString(text);
	}

	/**
	 * Clears current autonomous
	 * 
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void Clear() {
		oldStates = newStates = new ArrayList<>();
		timer.stop();
		timer.reset();
	}

	/**
	 * 
	 * Assigns a task to a button
	 * 
	 * @param name
	 *            The name of the task/button assignment. Could be anything, is just
	 *            used for identification
	 * @param input
	 *            The input button to assign to, can be an axis or a button.
	 * @param task
	 *            The {@link Task} to assign to the button
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void Assign(String name, JoystickInput input, Task task) {
		_log("===/Assign\\===");
		_log("Assigning " + name);
		tasks.put(name, task);
		_log("...");
		inputs.put(name, input);
		_log("Assigned");
		_log("===\\Assign/===");
	}

	/**
	 * Adds a task that will always run in the background of the auto until
	 * completion
	 * 
	 * @param task
	 *            The task to be added
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public void AddPassive(Task task) {
		_log("===/Passive\\===");
		passives.add(task);
		_log("===\\Passive/===");
	}

	/**
	 * Builds a TaskGroup out of the current recording.
	 * 
	 * @return The TaskGroup to be executed
	 * 
	 * @author Micah Neitz<br/>
	 *         Team 498
	 */
	public TaskGroup Build() {
		_log("===/Build\\===");
		_log((tasks == null) + " ; " + (passives == null) + " ; " + (logs == null));
		TaskGroup tg = new TaskGroup(tasks, passives, logs);
		_log("===\\Build/===");
		return tg;
	}

	private ArrayList<JoyState> _grabValues() {
		ArrayList<JoyState> states = new ArrayList<>();
		ArrayList<String> keys = new ArrayList<>(inputs.keySet());
		for (String s : keys) {
			JoyState state = new JoyState();
			if (inputs.get(s).isButton) {
				state.isBool = true;
				state.boolState = inputs.get(s).GetButton();
			} else {
				state.isBool = false;
				state.doubleState = inputs.get(s).GetAxis();
			}
			state.name = s;
			states.add(state);
		}
		return states;
	}

	private String _compileString() {
		StringBuilder builder = new StringBuilder();
		for (InputLog log : logs) {
			builder.append(log.Log());
		}
		return builder.toString().substring(1); // Substring removes first char
	}

	private void _parseString(String text) throws IllegalArgumentException {
		this.logs = new ArrayList<>();
		String[] logs = text.split(Pattern.quote(";")); // Each log is seperated by a semicolon, so we break it up
		for (int i = 0; i < logs.length; i++) {
			String[] elements = logs[i].split(Pattern.quote("_")); // seperate each element
			this.logs.add(new InputLog(elements[0], elements[1], elements[2]));
		}
	}

}
