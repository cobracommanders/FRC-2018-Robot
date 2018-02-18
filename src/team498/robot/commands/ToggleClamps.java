package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import team498.robot.subsystems.Arm;

/**
 * Toggles the flipper arms on the intake
 */
public class ToggleClamps extends InstantCommand {
	
	private Arm arm;
	
	public boolean isClamped = false;
	
    public ToggleClamps() {
    	super("ToggleClamps");
		requires(this.arm = Arm.getArm());
		
    }

    // Called once when the command executes
    protected void initialize() {
    	this.isClamped =!isClamped;
    	if(isClamped){
    		arm.setClamps(true);
    	}else{
    		arm.setClamps(false);
    	}
    	
    }

}
