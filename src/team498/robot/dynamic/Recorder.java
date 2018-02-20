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
 * building of dynamic autonomous modes.<br/>
 * <br/>
 * If you would like to see changes made to this, please contact me at
 * micah.team498@gmail.com
 * 
 * @version 1.0
 * 
 * @author Micah Neitz <br/>
 *         Team 498
 * 
 */
public final class Recorder {

	private static final String DIRECTORY = "/home/lvuser/frc/dynamicauto/";
	private Map<String, Task> tasks;
	private Map<String, JoystickInput> inputs;
	private ArrayList<PassiveTask> passives;
	private ArrayList<JoyState> oldStates;
	private ArrayList<JoyState> newStates;
	private ArrayList<InputLog> logs;
	private Timer timer;

	/**
	 * Creates a new recorder object
	 */
	public Recorder() {
		tasks = new HashMap<String, Task>();
		inputs = new HashMap<String, JoystickInput>();
		passives = new ArrayList<>();
		oldStates = newStates = new ArrayList<>();
		timer = new Timer();
		logs = new ArrayList<>();
	}

	/**
	 * Needs to be run on loop while recording inputs
	 */
	public void Read() {
		// Gets the timestamp
		double time = timer.get();
		//Start the timer
		if (time == 0) {
			timer.start();
		}
		// Keeps old states and grabs the new ones so we can see if they've changed
		oldStates = newStates;
		newStates = _grabValues();
		// If this is the first time we've recorded values, log all the starting values
		if (oldStates.size() == 0) {
			for (JoyState newState : newStates) {

				if (newState.isBool)
					logs.add(new InputLog(newState.name, newState.boolState, time));
				else
					logs.add(new InputLog(newState.name, newState.doubleState, time));

			}
			return;
		}
		if (oldStates.size() != newStates.size()) {
			return;
		}
		for (int i = 0; i < newStates.size(); i++) {
			JoyState newState = newStates.get(i);
			if (newState.isBool) {
				if (newState.boolState != oldStates.get(i).boolState) {
					InputLog input = new InputLog(newState.name, newState.boolState, time);
					logs.add(input);
				}
			} else {
				if (newState.doubleState != oldStates.get(i).doubleState) {
					InputLog input = new InputLog(newState.name, newState.doubleState, time);
					logs.add(input);
				}
			}
		}
	}

	/**
	 * Saves current autonomous
	 * 
	 * @param name
	 *            The name of the autonomous you are saving
	 * @throws IOException
	 *             Throws when you don't have access, or another error out of my
	 *             control occurs
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
	 *             Happens when you have no autonomous to load, or when you typed in
	 *             the name wrong
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
	 */
	public void Clear() {
		oldStates = newStates = new ArrayList<>();
		timer.stop();
		timer.reset();
	}

	/**
	 * Assigns a task to a button<br/>
	 * <br/>
	 * Does not accept {@link PassiveTask}, will effectively do nothing if you try
	 * to add one this way. Use {@link #AddPassive()} for that
	 * 
	 * @param name
	 *            The name of the {@link JoystickInput}/{@link Task} assignment.
	 *            Could be anything, is just used for identification
	 * @param input
	 *            The {@link JoystickInput} to assign the {@link Task} to, can be an
	 *            axis or a button.
	 * @param task
	 *            The {@link Task} to assign to the {@link JoystickInput}
	 */
	public void Assign(String name, JoystickInput input, Task task) {
		if (task instanceof PassiveTask)
			return;
		tasks.put(name, task);
		inputs.put(name, input);
	}

	/**
	 * Adds a {@link PassiveTask} that will always run in the background of the auto
	 * until completion. <br/>
	 * <br/>
	 * Will accept {@link Task}, although will never change the value that it
	 * defaults to.
	 * 
	 * @param task
	 *            The {@link PassiveTask} to be added
	 */
	public void AddPassive(PassiveTask task) {
		passives.add(task);
	}

	/**
	 * Builds a {@link TaskGroup} out of the current recording.
	 * 
	 * @return The {@link TaskGroup} to be executed
	 */
	public TaskGroup Build() {
		TaskGroup tg = new TaskGroup(tasks, passives, logs);
		return tg;
	}

	/**
	 * Checks if an autonomous exists on file
	 * 
	 * @param name
	 *            The name of the autonomous
	 * @return <b>{@code true}</b> if the autonomous exists
	 */
	public static boolean Check(String name) {
		File file = new File(DIRECTORY + name + ".txt");
		return file.exists();
	}

	// I don't document the private methods, because I don't need to. No one is
	// going to see this besides me, or the people trying to figure out how this
	// works so they can make changes.
	// If you would like to request a change or addition, please contact me at
	// micah.team498@gmail.com

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
		String[] logs = text.split(Pattern.quote(";"));
		for (int i = 0; i < logs.length; i++) {
			String[] elements = logs[i].split(Pattern.quote("_")); // seperate each element
			// 0 is the name you assigned to the task
			// 1 is the value of the change it is making
			// 2 is whether the input is a button or an axis
			// 3 is the time stamp the change was made
			this.logs.add(new InputLog(elements[0], elements[1], elements[2], elements[3]));
		}
	}
}
