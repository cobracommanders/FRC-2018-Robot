package team498.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import team498.robot.Mappings;

public class Catapult extends Subsystem {
		
	private static Catapult instance;	
	public static Catapult getInstance() {
       instance = instance == null ? new Catapult(Mappings.CatapultForward, Mappings.CatapultReverse) : instance;
        return instance;
    }
	
	private DoubleSolenoid catapult; 
	
	private Catapult(int solenoidForward, int solenoidReverse) {
		catapult = new DoubleSolenoid(solenoidForward, solenoidReverse);
	}

	public void launchCatapult() {
		catapult.set(Value.kReverse);
	}

	public void resetCatapult() {
		catapult.set(Value.kOff);
	}

	public void initDefaultCommand() {

	}
}
