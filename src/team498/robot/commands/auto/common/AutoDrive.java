package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {
	
	private Drivetrain drivetrain;
	private double moveValue;
	
    public AutoDrive(double moveValue) {
    	super("AutoDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	
    	this.moveValue = moveValue;
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.drive(moveValue, 0); //drives a value
    }

    protected boolean isFinished() {
        return false; //stops when command time expires
    }

    protected void end() {
    	drivetrain.drive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
