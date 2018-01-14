package team498.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class Button extends Subsystem {
	
	private static Button button = null;

	public static Button getButton() {
        button = button == null ? new Button() : button;
        return button;
    }
	private DigitalInput theButton = new DigitalInput(0);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public boolean get() {
    	return button.get();
    }
}

