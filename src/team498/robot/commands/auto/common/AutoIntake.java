package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Arm;

public class AutoIntake extends Command {
	
	private Arm arm;
	private double targetLeftIntakePower;
	private double targetRightIntakePower;
	private double targetArmPower;
	private double armPower;
	private double intakeLeftPower;
	private double intakeRightPower;
	private boolean isLiftUp;

    public AutoIntake(double armPower, double intakeLeftPower, double intakeRightPower, boolean isLiftUp) {
    	super ("AutoIntake");
    	
    	requires(this.arm = Arm.getArm());
    	
    	this.armPower = armPower;
    	this.intakeLeftPower = intakeLeftPower;
    	this.intakeRightPower = intakeRightPower;
    	this.isLiftUp = isLiftUp;
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	//add armposition
    	if (Math.abs(armPower) > 0) {
    		targetArmPower = armPower;
    		arm.armSet(armPower);
    	} else {
    		arm.armSet(0);
    	}
    	//probably timer or limit switch idk
    	if (Math.abs(intakeLeftPower) > 0 || Math.abs(intakeRightPower) > 0) {
    		targetLeftIntakePower = intakeLeftPower;
    		targetRightIntakePower = intakeRightPower;
    		arm.intakeSet(intakeLeftPower, intakeRightPower);
    	}
    	
    	if (isLiftUp) {
    		arm.liftSet(isLiftUp);
    	} else { ///if isLiftUp = false
    		arm.liftSet(isLiftUp);
    	}
    }

    protected boolean isFinished() {
    	if (targetLeftIntakePower == intakeLeftPower || targetRightIntakePower == intakeRightPower) {
    		intakeLeftPower = 0;
    		intakeRightPower = 0;
    		return true;
    	}
    	
    	if (targetArmPower == armPower) {
    		armPower = 0;
    		return true;
    	}
    	
    	if (isLiftUp) {
    		isLiftUp = false;
    		return true;
    	}
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	this.end();
    }
}
