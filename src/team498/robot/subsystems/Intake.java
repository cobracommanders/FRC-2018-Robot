package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Mappings;
import team498.robot.commands.ManualIntake;

public class Intake extends Subsystem {
	
	private static Intake intake = null;
	public static Intake getIntake() {
		intake = intake == null ? new Intake() : intake;
		return intake;
	}	
	
	private Spark intakeLeft = new Spark(Mappings.IntakeLeftPort);
	private Spark intakeRight =  new Spark(Mappings.IntakeRightPort);

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualIntake());
    }
    
    public void set(double leftPower, double rightPower) {
    	intakeLeft.set(leftPower);
    	intakeRight.set(rightPower);
    }
}

