package team498.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.subsystems.Arm;

/**
 *
 */
public class ToggleLift extends InstantCommand {
	private Arm arm;
	boolean isUp = false;
	boolean target = true;

    public ToggleLift() {
    	super("Lift");
    	
    	requires(this.arm = Arm.getArm());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.liftSet(isUp);
    	isUp = !isUp;
    	SmartDashboard.putBoolean(Dashboard.ElevatorUp,isUp);
    }
}
