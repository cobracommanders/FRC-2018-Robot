package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;
import team498.robot.commands.ManualArm;

public class Arm extends Subsystem {
	
	private static Arm arm = null;
	public static Arm getArm() {
		arm = arm == null ? new Arm() : arm;
		return arm;
	}
	
	private boolean isIntakeActive = true;
	
	private Timer timer = new Timer();
	
	private DigitalInput cubeIn = new DigitalInput(Mappings.LimitSwitch);
	//private DoubleSolenoid lift = new DoubleSolenoid(Mappings.LiftForward, Mappings.LiftReverse);
	private Victor armMotor1 = new Victor(Mappings.ArmPort1);
	private Victor armMotor2 = new Victor(Mappings.ArmPort2);
	private Victor intakeLeft = new Victor(Mappings.IntakeLeftPort);
	private Victor intakeRight =  new Victor(Mappings.IntakeRightPort);
	private AnalogPotentiometer pot = new AnalogPotentiometer(Mappings.ArmPotChannel, 314, 0);
	
	private double lastLeft = 0;
	private double lastRight = 0;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualArm(0));    	
    }
        
    public void setArm(double power) {
    	armMotor1.set(power);
    	armMotor2.set(-power);
    }
    public void setIntake(double leftPower, double rightPower) {
    	
    	if(Math.abs(leftPower - getLastLeft()) > 1.01){
    		failSafe();
    	}
    	
    	
    	if (timer.get() > Mappings.IntakeFailSafeDelay) {
    		isIntakeActive = true;
    		timer.stop();
    	}
    	if(isIntakeActive) {
    		intakeLeft.set(leftPower);
        	intakeRight.set(rightPower);
        	lastLeft = leftPower;
        	lastRight = rightPower;
    	} else {
    		intakeLeft.set(0);
        	intakeRight.set(0);
        	lastLeft = 0;
        	lastRight = 0;
    	}
    }
    public double getLastLeft(){
    	return lastLeft;
    }
    public double getLastRight(){
    	return lastRight;
    }
    public void setLift(boolean isUp) {
    	if(isUp) {
    		//lift.set(Value.kReverse);
    	}else {
    		//lift.set(Value.kForward);
    	}
    }
    
    public double getPosition() {
    	return pot.get();
    }
    public void failSafe() {
    	timer.reset();
    	timer.start();
    	isIntakeActive = false;
    }
    public void updateDashboard(){
    	//SmartDashboard.putString(Dashboard.LiftState, lift.get().toString());
    	SmartDashboard.putNumber(Dashboard.ArmPosition, getPosition());
    	SmartDashboard.putBoolean(Dashboard.CubeIn, cubeIn.get());
    }
}