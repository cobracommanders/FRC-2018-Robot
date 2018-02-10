package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Intake;

public class ManualIntake extends Command {

	private Operator operator = Operator.getOperator();
	private Intake intake;	
	
	private double leftPower = 0;
	private double rightPower = 0;
	private boolean intakeIn = true;

	
	boolean leftBumperPressed = false;
	boolean intakeOn = true;
	
	
	public ManualIntake() {
		super("ManualIntake");
		requires(this.intake = Intake.getIntake());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
/*		if(operator.controller.leftBumper.get() && !leftBumperPressed) {
			intakeOn = !intakeOn;
			leftBumperPressed = true;
		}
		if(!operator.controller.leftBumper.get() && leftBumperPressed) {
			leftBumperPressed = false;
		}*/
		//double leftPower = operator.controller.axisLeftY.getAxisValue();
		//double rightPower = operator.controller.axisRightY.getAxisValue();
		
		//Intake In
		if(operator.controller.buttonA.get()) {
			leftPower = -.6;
			rightPower = -.6;
		}
		//Intake out
		else if (operator.controller.buttonY.get()) {
			leftPower = 1;
			rightPower = 1;
		}
		//Intake cube adjust intake
		else if(operator.controller.buttonX.get()) {
			leftPower = -.6;
			rightPower = -.3;
		}
		//Also intake cube adjust intake
		else if(operator.controller.buttonB.get()) {
			leftPower = -.3;
			rightPower = -.6;
		}
		//Intake off
		else{
			leftPower = 0;
			rightPower = 0;
		}
		

	
			this.intake.set(leftPower,rightPower);
		SmartDashboard.putNumber(Dashboard.IntakeLeftPower, leftPower);
		SmartDashboard.putNumber(Dashboard.IntakeRightPower, rightPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
