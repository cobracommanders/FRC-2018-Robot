package team498.robot.dynamicAuto;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Timer;
import team498.robot.*;

public class DynamicAutoRecorder implements ButtonListener {
	private List<String> buttonChanges;
	private String megaLog;
	private RecordingThread thread;
	private Timer timer;

	public DynamicAutoRecorder() {
		thread = new RecordingThread();
	}

	public void StartRecording() {
		buttonChanges = new ArrayList<String>();
		thread = new RecordingThread();
		thread.AddListener(this);
		thread.start();
	}
	
	public void StopRecording(String filename) throws FileNotFoundException {
		thread.interrupt();
		megaLog = "";
		for(String s : buttonChanges) {
			megaLog += s + ";";
		}
		try {
			PrintWriter write = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	@Override
	public void buttonChange(String changed) {
		String log;
		char[] changedChars = changed.toCharArray();
		char define = changedChars[changedChars.length - 1];
		String button = changed.substring(0, changed.length() - 2);
		log = String.format("{0}_{1}_{2}", timer.get(), button, define);
		buttonChanges.add(log);
	}
}
