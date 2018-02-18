package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;
import team498.robot.commands.ManualArm;
import team498.robot.commands.ManualDrive;

public class Arm extends PIDSubsystem {
	
	private static final double potFullRange = 3600;
	private static final double potOffset = 0;
	
	private static final double armMinAngle = 20; // TODO: Make this a pref so we can change whenever arm is worked on
	private static final double armMaxAngle = armMinAngle + 180; // TODO: Calibrate based on arm swing
	
	private static Arm arm = null;

	public static Arm getArm() {
		arm = arm == null ? new Arm() : arm;
		return arm;
	}
	
	private boolean isIntakeActive = true;
	
	private Timer timer = new Timer();
	
	private DigitalInput cubeIn = new DigitalInput(Mappings.LimitSwitch);
	private DoubleSolenoid lift = new DoubleSolenoid(Mappings.PCMModuleNumber, Mappings.LiftForward, Mappings.LiftReverse);
	private WPI_TalonSRX armBottom = new WPI_TalonSRX(Mappings.ArmBottomDeviceNumber);
	private WPI_TalonSRX armTop = new WPI_TalonSRX(Mappings.ArmTopDeviceNumber);
	private SpeedControllerGroup armMotorGroup = new SpeedControllerGroup(armBottom, armTop);
	private Victor intakeLeft = new Victor(Mappings.IntakeLeftPort);
	private Victor intakeRight =  new Victor(Mappings.IntakeRightPort);
	private AnalogPotentiometer pot = new AnalogPotentiometer(Mappings.ArmPotChannel, potFullRange, potOffset);
	
	private double lastLeft = 0;
	private double lastRight = 0;
	
	public Arm() {
		super("Arm", 0, 0, 0);
		
		// Hold starting position
		setArmAngle(pot.get());
		
		// Initialize PID
		setAbsoluteTolerance(1);
		setInputRange(armMinAngle, armMaxAngle);
		setOutputRange(-.7, .7);
		enable();
	}
	
    public void initDefaultCommand() {
    	// setDefaultCommand(new ManualArm(0));
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
    
    public void incrementArm(){
    	
    }
    public void decrementArm(){
    	
    }
    
    public void setArmAngle(double targetAngle) {
    	this.setSetpoint(targetAngle);
    }
    
    public void setArmPower(double armPower)
    {
    	armMotorGroup.set(armPower);
    }
    
    public void updateDashboard(){
    	//SmartDashboard.putString(Dashboard.LiftState, lift.get().toString());
    	SmartDashboard.putNumber(Dashboard.ArmPosition, getPosition());
    	SmartDashboard.putBoolean(Dashboard.CubeIn, cubeIn.get());
    }

	@Override
	protected double returnPIDInput() {
		return pot.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		armMotorGroup.pidWrite(output);		
	}
}