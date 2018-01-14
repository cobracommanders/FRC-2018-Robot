package team498.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto extends CommandGroup{
	
	
	public Auto() { 
		addSequential(new AutoDrive(1, 0, false), 2);
		addSequential(new AutoDrive(0, 0, false), 2);
		addSequential(new AutoDrive(1, -1, false), 3);
		addSequential(new AutoDrive(-1, 0, true));
		addSequential(new AutoDrive(0, 0, false), 1);
		addSequential(new AutoDrive(-.8, 0, true));
		
	}
	
	@Override
	protected void initialize() {
		
	}
	
	protected void execute() {
		
		
		
	}

	protected void end() {
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void interrupted() {
		end();
	}

}
