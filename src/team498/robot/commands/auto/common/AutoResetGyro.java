package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.*;

public class AutoResetGyro extends Command {
	
	private Gyro gyro;
	
    public AutoResetGyro() {
    	super("ResetGyro");
    	
    	requires(this.gyro = Gyro.getGyro());
    }

    protected void initialize() {
    }

    protected void execute() {
    	gyro.reset();
    }

    protected boolean isFinished() {
    	   return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
