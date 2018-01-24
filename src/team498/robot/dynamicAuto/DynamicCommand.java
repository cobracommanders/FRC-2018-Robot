package team498.robot.dynamicAuto;

import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team498.robot.dynamicAuto.dynamic;
import team498.robot.subsystems.*;

public class DynamicCommand extends Command {

	private String formatter;
	private Drivetrain drivetrain;
	private Timer timer;

	private double leftTMove = 0;
	private double rightTMove = 0;
	private double rotate = 0;

	private dynamic[] commands;

	private byte phase;

	public DynamicCommand(String formatter) {
		super("DynamicCommand");

		this.formatter = formatter;
		requires(this.drivetrain = Drivetrain.getDrivetrain());
	}

	@Override
	protected void initialize() {
		System.out.println(formatter);
		String[] a = formatter.split(Pattern.quote(";"));
		System.out.println("================");
		for (String s : a) {
			System.out.println(s);
		}
		System.out.println("================");
		String[] b;
		commands = new dynamic[a.length];
		for (int i = 0; i < a.length; i++) {
			commands[i] = new dynamic();
			b = a[i].split("_");
			for (String s : b) {
				System.out.println(s);
			}

			commands[i].timeStamp = Double.parseDouble(b[0]);
			System.out.println("TIMESTAMP SHIT " + commands[i].timeStamp + " " + b[0]);

			commands[i].button = b[1];
			switch (b[1]) {
			case "rt":
			case "lt":
			case "ljx":
			case "ljy":
			case "rjx":
			case "rjy":
				commands[i].isAxis = true;
				commands[i].axisVal = Double.parseDouble(b[2]);
				break;
			default:
				commands[i].isAxis = false;
				commands[i].buttonVal = b[2] == "1" ? true : false;
				break;
			}
			System.out.println("i = " + i);
		}
		timer = new Timer();
		timer.start();
		phase = 0;
	}

	@Override
	protected void execute() {
		System.out.println(phase + " " + commands[phase].timeStamp);
		if (phase >= commands.length) {
			phase = -1;
			end();
		}
		if (phase != -1) {
			if (commands[phase].timeStamp < timer.get()) {
				System.out.println("Next Command " + timer.get());
				phase++;
			} else {
				switch (commands[phase].button) {
				case "rt":
					rightTMove = commands[phase].axisVal;
					break;
				case "lt":
					leftTMove = commands[phase].axisVal;
					break;
				case "ljx":
					rotate = commands[phase].axisVal;
					break;
				default:

				}
			}
		}
		drivetrain.drive(rightTMove - leftTMove, rotate);
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
