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

	private final byte comp_timeStamp = 0;
	private final byte comp_button = 1;
	private final byte comp_value = 2;

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
		String[] commandStrings = formatter.split(Pattern.quote(";"));
		commands = new dynamic[commandStrings.length];
		for (int i = 0; i < commandStrings.length; i++) {
			commands[i] = ParseCommand(commandStrings[i]);
		}
		timer = new Timer();
		timer.start();
		phase = 0;
	}

	//

	public dynamic ParseCommand(String command) {
		String[] components;
		components = command.split(Pattern.quote("_"));

		double timeStamp = Double.parseDouble(components[comp_timeStamp]);

		String button = components[comp_button];
		double axisVal = 0;
		boolean buttonVal = false;
		switch (components[comp_button]) {
		case "rt":
		case "lt":
		case "ljx":
		case "ljy":
		case "rjx":
		case "rjy":
			axisVal = Double.parseDouble(components[comp_value]);
			break;
		default:
			buttonVal = components[comp_value] == "1" ? true : false;
			break;
		}

		return new dynamic(timeStamp, button, axisVal, buttonVal);
	}

	@Override
	protected void execute() {
		if (commands.length >= phase) {
			phase = -1;
			end();
		}
		if (phase != -1) {
			if (commands[phase].timeStamp > timer.get()) {

				switch (commands[phase].button) {
				case "rt":
					rightTMove = commands[phase].axisVal;
					break;
				case "lt":
					leftTMove = commands[phase].axisVal;
					break;
				case "ljx":
					rotate = commands[phase].axisVal;
				default:
					System.out.println(commands[phase].button);
				}
			} else
				phase++;
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
