package team498.robot.dynamicAuto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.Timer;

public class DynamicAutoRecorder {
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
		br.close();
		System.out.println(mega);
		if (mega == null)
			throw new NullPointerException("You f*cked the formatter again");
		return new DynamicCommand(mega);
	}

	public void StartRecording() {
		buttonChanges = new ArrayList<>();
		buttonRec = new ButtonRecorder();
		timer = new Timer();
		timer.start();
	}

	public void StopRecording(String directory, String fileName) throws IOException {
		try {
			File file = new File(directory + fileName);
			System.out.println(file.delete());
		} catch (Exception e) {
			System.out.println(String.format("============\nFailed to delete %s; \n%s\n============", e.toString(),
					e.getMessage()));
		}
		System.out.println("Stopped recording");
		timer.stop();
		megaLog = "";
		for (int i = 0; i < buttonChanges.size(); i++) {
			System.out.println(buttonChanges.get(i));
			megaLog += buttonChanges.get(i) + ";";
		}
		System.out.println(megaLog);
		File directoryFile = new File(directory);
		if (!directoryFile.exists())
			directoryFile.mkdir();
		FileWriter fw = new FileWriter(directory + fileName);

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(megaLog);

		bw.close();
	}

	public void buttonChange(String changed) {
		String log;
		String[] comps = changed.split(Pattern.quote("^"));
		String button = comps[0];
		String value = comps[1];
		System.out.println("Timer = " + timer);
		log = String.format("%s_%s_%s", timer.get(), button, value);
		buttonChanges.add(log);
		System.out.println(log);
	}
}
