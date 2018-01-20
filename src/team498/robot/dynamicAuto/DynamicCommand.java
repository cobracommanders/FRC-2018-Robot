package team498.robot.dynamicAuto;

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
		String[] a = formatter.split(";");
		String[] b;
		commands = new dynamic[a.length];
		dynamic d = new dynamic();
		for (int i = 0; i < a.length; i++) {
			b = a[i].split("_");

			d.timeStamp = Double.parseDouble(b[0]);

			d.button = b[1];
			switch (b[1]) {
			case "rt":
			case "lt":
			case "ljx":
			case "ljy":
			case "rjx":
			case "rjy":
				d.isAxis = true;
				d.axisVal = Double.parseDouble(b[2]);
				break;
			default:
				d.isAxis = false;
				d.buttonVal = b[2] == "1" ? true : false;
				break;
			}
			commands[i] = d;
		}
		timer = new Timer();
		timer.start();
		phase = 0;
	}

	@Override
	protected void execute() {
		if (phase == commands.length)
			end();
		if (commands[phase].timeStamp < timer.get()) {
			phase++;
		} else {
			switch(commands[phase].button) {
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
