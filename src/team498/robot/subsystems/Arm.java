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
	private DoubleSolenoid lift = new DoubleSolenoid(Mappings.LiftForward, Mappings.LiftReverse);
	private Victor armMotor = new Victor(Mappings.ArmPort);
	private Victor intakeLeft = new Victor(Mappings.IntakeLeftPort);
	private Victor intakeRight =  new Victor(Mappings.IntakeRightPort);
	private AnalogPotentiometer pot = new AnalogPotentiometer(Mappings.ArmPotChannel, 314, 0);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualArm(0));    	
    }
        
    public void setArm(double power) {
    	armMotor.set(power);
    }
    public void setIntake(double leftPower, double rightPower) {
    	intakeLeft.set(leftPower);
    	intakeRight.set(rightPower);
    	
    	if (timer.get() > Mappings.IntakeFailSafeDelay) {
    		isIntakeActive = true;
    		timer.stop();
    	}
    	
    	if(isIntakeActive) {
    		intakeLeft.set(leftPower);
        	intakeRight.set(rightPower);
    	} else {
    		intakeLeft.set(0);
        	intakeRight.set(0);
    	}
    }
    
    public void setLift(boolean isUp) {
    	if(isUp) {
    		lift.set(Value.kReverse);
    	}else {
    		lift.set(Value.kForward);
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
    	System.out.println(lift.get());
    	SmartDashboard.putString(Dashboard.LiftState, lift.get().toString());
    	SmartDashboard.putNumber(Dashboard.ArmPosition, getPosition());
    	SmartDashboard.putBoolean(Dashboard.CubeIn, cubeIn.get());
    }
}

