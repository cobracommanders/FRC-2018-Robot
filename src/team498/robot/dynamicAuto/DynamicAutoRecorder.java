package team498.robot.dynamicAuto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Timer;

public class DynamicAutoRecorder implements ButtonListener {
	private List<String> buttonChanges;
	private String megaLog;
	public ButtonRecorder buttonRec;
	private Timer timer;

	private static DynamicAutoRecorder dar;

	public static DynamicAutoRecorder getAutoRecorder() {
		return dar = dar == null ? new DynamicAutoRecorder() : dar;
	}

	private DynamicAutoRecorder() {
		buttonRec = new ButtonRecorder();
	}

	public DynamicCommand CreateDynamic(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String mega = "";
		mega = br.readLine();
		System.out.println(mega);
		return new DynamicCommand(mega);
	}

	
	public void StartRecording() {
		buttonChanges = new ArrayList<String>();
		buttonRec = new ButtonRecorder();
		buttonRec.AddListener(this);             
	}

	public void StopRecording(String filename) throws IOException {
		megaLog = "";
		for (String s : buttonChanges) {
			megaLog += s + ";";
		}
		FileWriter fw = new FileWriter(filename);

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(megaLog);

		bw.close();
	}
	
	//

	@Override
	public void buttonChange(String changed) {
		String log;
		char[] changedChars = changed.toCharArray();
		char define = changedChars[changedChars.length - 1];
		String button = changed.substring(0, changed.length() - 2);
		log = String.format("{0}_{1}_{2}", timer.get(), button, define);
		buttonChanges.add(log);
		System.out.println(log);
	}
}
