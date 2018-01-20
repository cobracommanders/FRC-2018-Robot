package team498.robot.dynamicAuto;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.dynamicAuto.dynamic;
import team498.robot.subsystems.*;

public class DynamicCommand extends Command {

	private String formatter;
	private Drivetrain drivetrain;

	private dynamic[] commands;

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
	}

	@Override
	protected void execute() {

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
