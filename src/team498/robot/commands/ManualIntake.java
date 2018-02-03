package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Intake;

/**
 *
 */
public class ManualIntake extends Command {

	private Intake intake;
	private Operator operator = Operator.getOperator();
	double leftPower = 0;
	double rightPower = 0;
	boolean intakeIn = true;
	
	boolean aPressed = false;
	boolean bPressed = false;
	boolean xPressed = false;
	boolean yPressed = false;
	
	
	public ManualIntake() {
		super("ManualIntake");
		// Required Subsystems
		requires(this.intake = Intake.getIntake());
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(operator.controller.buttonA.get() && !aPressed) {
    		leftPower += 0.01;
    		aPressed = true;
    	}
    	if(!operator.controller.buttonA.get() && aPressed) {
    		aPressed = false;
    	}
    	
    	if(operator.controller.buttonB.get() && !bPressed) {
    		leftPower -= 0.01;
    		bPressed = true;
    	}
    	if(!operator.controller.buttonB.get() && bPressed) {
    		bPressed = false;
    	}
    	
    	if(operator.controller.buttonX.get() && !xPressed) {
    		rightPower += 0.01;
    		xPressed = true;
    	}
    	if(!operator.controller.buttonX.get() && xPressed) {
    		xPressed = false;
    	}
    	
    	if(operator.controller.buttonY.get() && !yPressed) {
    		rightPower -= 0.01;
    		yPressed = true;
    	}
    	if(!operator.controller.buttonY.get() && yPressed) {
    		yPressed = false;
    	}
    		
    		
    		this.intake.set(leftPower, rightPower);
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