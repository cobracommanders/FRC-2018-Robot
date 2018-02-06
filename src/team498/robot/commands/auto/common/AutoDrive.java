package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {
	
	private Drivetrain drivetrain;
	private double distanceValue;
	
    public AutoDrive(double distanceValue) {
    	super("AutoDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	
    	distanceValue = drivetrain.getDistance();
    	this.distanceValue = distanceValue;
    }

    protected void initialize() {
    }

    protected void execute() {
    	drivetrain.drive(distanceValue, 0); //drives towards distance
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
