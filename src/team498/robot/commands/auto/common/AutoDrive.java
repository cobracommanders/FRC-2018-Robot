package team498.robot.commands.auto.common;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {
	
	private Drivetrain drivetrain;
	private double moveValue;
	private double desiredDistance;
	
    public AutoDrive(double moveValue, double desiredDistance) {
    	super("AutoDrive");
    	
    	requires(this.drivetrain = Drivetrain.getDrivetrain());
    	this.moveValue = moveValue;
    	this.desiredDistance = desiredDistance;
    }

	protected void initialize() {
    }

    protected void execute() {
    	if (drivetrain.getDistance() != desiredDistance) { //uses encoder distance
    	drivetrain.drive(moveValue, 0); //drives until it hits distance
    	} else {
    		end();
    	}
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
