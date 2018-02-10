package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoTurn extends Command {

	private Drivetrain drivetrain;
	private double targetAngle = 0;

	public AutoTurn(double targetAngle) {
		super("AutoTurn");

		requires(this.drivetrain = Drivetrain.getDrivetrain());

		this.targetAngle = targetAngle;
	}

	protected void initialize() {
		drivetrain.resetGyro();
		drivetrain.setSetpoint(targetAngle);
		drivetrain.enable();
	}

	protected void execute() {
		System.out.println("What's the setpoint? It is: " + drivetrain.getSetpoint());
		System.out.println("Position? It is: " + drivetrain.getPosition());
		System.out.println("Is it on the target? It is: " + drivetrain.onTarget());
	}

	protected boolean isFinished() {		
		System.out.println("Finished? " + drivetrain.onTarget());		
		return drivetrain.onTarget();
	}

	protected void end() {
		drivetrain.disable();
	}

	protected void interrupted() {
		end();
	}
}
