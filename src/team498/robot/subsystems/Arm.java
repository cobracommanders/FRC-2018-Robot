package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mappings;
import team498.robot.commands.CheckIntake;
import team498.robot.commands.ManualArm;

public class Arm extends PIDSubsystem {

	private static final double potFullRange = 3600;
	private static final double potOffset = 0;

	private static final double armMinAngle = 3472; // TODO: Make this a pref so
													// we can change whenever
													// arm is worked on
	private static final double armMaxAngle = 2597; // TODO:
																	// Calibrate
																	// based on
																	// arm swing

	private static Arm arm = null;

	public static Arm getArm() {
		arm = arm == null ? new Arm() : arm;
		return arm;
	}

	private double[] armPositions = {1600, 1450, 1205, 1034 };

	private int shootScaleIndex = 2;
	
	double startingArm;

	private boolean isIntakeActive = true;
	private boolean isLiftUp = false;
	private Timer timer = new Timer();

	private DigitalInput cubeIn = new DigitalInput(Mappings.LimitSwitch);
	//The clamp is now the intake flip!!!!
	private DoubleSolenoid clamp = new DoubleSolenoid(Mappings.PCMModuleNumber, Mappings.ClampReverse,Mappings.ClampForward);
	//private DoubleSolenoid armBrake = new DoubleSolenoid(Mappings.PCMModuleNumber, Mappings.ArmBrakeReverse, Mappings.ArmBrakeForward);
	private Victor armBottom = new Victor(Mappings.ArmBottomDeviceNumber);
	private Victor armTop = new Victor(Mappings.ArmTopDeviceNumber);
	private SpeedControllerGroup armMotorGroup = new SpeedControllerGroup(armBottom, armTop);
	private Victor intakeLeft = new Victor(Mappings.IntakeLeftPort);
	private Victor intakeRight = new Victor(Mappings.IntakeRightPort);
	private AnalogPotentiometer pot = new AnalogPotentiometer(Mappings.ArmPotChannel, potFullRange, potOffset);
	private Relay clampLight = new Relay(0);
	
	private Victor slider = new Victor(Mappings.Slider);
	private Victor climber = new Victor(Mappings.Climber);
	
	
	private double lastLeft = 0;
	private double lastRight = 0;
	private double stopThreshold = 150;
	public boolean isClamped;
	public boolean isIntakeIn1 = false;
	public boolean isIntakeIn2 = false;
	public boolean isArmRestricted = true;
	public boolean isArmBrake = true;
	
	private int index = 2;

	public Arm() {
		super("Arm", 0.05, 0, 0.2);

		// Hold starting position
		setArmAngle(pot.get());
		startingArm = pot.get();
		// Initialize PID
		setAbsoluteTolerance(30);
		setInputRange(armPositions[armPositions.length - 1], armPositions[0]);
		setOutputRange(-.5, .5);
		setSetpoint(armPositions[index]);
		//enable();
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new ManualArm(0));
		setDefaultCommand(new CheckIntake());
	}

	public void setIntake(double leftPower, double rightPower) {
		intakeLeft.set(leftPower);
		intakeRight.set(rightPower);
		lastLeft = leftPower;
		lastRight = rightPower;
		/*if (Math.abs(leftPower - getLastLeft()) > 1.01) {
			failSafe();
		}

		if (timer.get() > Mappings.IntakeFailSafeDelay) {
			isIntakeActive = true;
			timer.stop();
		}
		if (isIntakeActive && !cubeIn.get()) {
			intakeLeft.set(leftPower);
			intakeRight.set(rightPower);
			lastLeft = leftPower;
			lastRight = rightPower;
		}else if(isIntakeActive && cubeIn.get() && leftPower < 0){
			intakeLeft.set(leftPower);
			intakeRight.set(rightPower);
			lastLeft = leftPower;
			lastRight = rightPower;
		}else{
			intakeLeft.set(0);
			intakeRight.set(0);
			lastLeft = 0;
			lastRight = 0;
		}
		intakeLeft.set(leftPower);
		intakeRight.set(rightPower);
		if(leftPower == 0.6){
			isIntakeIn1 = true;
			isIntakeIn2 = true;
		}else if(leftPower == -1){
			isIntakeIn1 = true;
			isIntakeIn2 = false;
		}else{
			isIntakeIn1 = false;
			isIntakeIn2 = false;
		}*/
		SmartDashboard.putBoolean(Dashboard.IsIntakeIn1, isIntakeIn1);
		SmartDashboard.putBoolean(Dashboard.IsIntakeIn2, isIntakeIn2);
	}
	public void ToggleArmRestrict(){
		isArmRestricted = !isArmRestricted;
	}
	public void checkIntake() {
		
		intakeLeft.set(lastLeft);
		intakeRight.set(lastRight);
		
		if(lastLeft == 0.6){
			isIntakeIn1 = true;
			isIntakeIn2 = true;
		}else if(lastLeft == -1){
			isIntakeIn1 = true;
			isIntakeIn2 = false;
		}else{
			isIntakeIn1 = false;
			isIntakeIn2 = false;
		}
		SmartDashboard.putBoolean(Dashboard.IsIntakeIn1, isIntakeIn1);
		SmartDashboard.putBoolean(Dashboard.IsIntakeIn2, isIntakeIn2);
	}

	public double getLastLeft() {
		return lastLeft;
	}

	public double getLastRight() {
		return lastRight;
	}

	public void incrementArm() {
		if (index < armPositions.length - 1) {
			index++;
			setArmAngle(index);
		}

	}

	public void decrementArm() {
		if (index > 0) {
			index--;
			setArmAngle(index);
		}
	}
	
	public void armBrakeIn() {
		//armBrake.set(Value.kForward);
		isArmBrake = true;
		
	}
	public void armBrakeOut() {
		//armBrake.set(Value.kReverse);
		isArmBrake = false;
		
	}

	public void setArmAngle(double targetAngle) {
		this.setSetpoint(targetAngle);
	}

	public void setArmPower(double armPower) {
			armMotorGroup.set(armPower);
	}

	public void updateDashboard() {
		// SmartDashboard.putString(Dashboard.LiftState, lift.get().toString());
		SmartDashboard.putNumber(Dashboard.ArmPosition, getPosition());
		SmartDashboard.putBoolean(Dashboard.CubeIn, cubeIn.get());
	}

	public void setClamps(boolean isClamped) {
		if (isClamped) {
			clamp.set(Value.kReverse);
			this.isClamped = isClamped;
		} else {
			clamp.set(Value.kForward);
			this.isClamped = isClamped;
		}
	}
	
	public void setClimbPower(double climbPower){
		climber.set(climbPower);
	}
	public void setSliderPower(double sliderPower){
		slider.set(sliderPower);
	}
	
	public void setClampLight(){
		if(isClamped){
			clampLight.set(Relay.Value.kForward);
		}else{
			clampLight.set(Relay.Value.kReverse);
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

	@Override
	protected double returnPIDInput() {
		return pot.get();
	}
	public double getScalePot(){
		return startingArm;
	}

	@Override
	protected void usePIDOutput(double output) {
		/*if(Math.abs(pot.get() - armPositions[0]) < stopThreshold && -output < 0){
			armMotorGroup.pidWrite(0);
		}else if(Math.abs(pot.get() - armPositions[armPositions.length - 1]) < stopThreshold && -output > 0){
			armMotorGroup.pidWrite(0);
		}else{
			armMotorGroup.pidWrite(-output);

		}*/
		checkIntake();
		if(pot.get() - startingArm < 0 && -output < 0){
			armMotorGroup.pidWrite(-output);
			System.out.println(-output);
		}else if(pot.get() - startingArm > 0 && -output > 0){
			armMotorGroup.pidWrite(-output);
			System.out.println(-output);
		}else{
			armMotorGroup.pidWrite(0);
			System.out.println(0);
		}
		

	}
}