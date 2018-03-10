package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.ToggleLift;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;
import team498.robot.commands.auto.common.AutoWait;

/**
 *
 */
public class StartCenterPlaceRightSwitchStrategy extends CommandGroup {

	public StartCenterPlaceRightSwitchStrategy() {
		System.out.println("Start Center, Right Switch Strategy!");
		//addSequential(new ToggleClamps());
		//addSequential(new ToggleClamps());
	    addSequential(new AutoDrive(-.7,-12)); 
	    addSequential(new AutoOldTurn(-.6,90)); 
	    addSequential(new AutoDrive(-.7,-30)); 
	    addSequential(new AutoOldTurn(.6,90)); 
	    addSequential(new AutoDriveTimed(-.7,0,2)); 
	    addSequential(new AutoArmTimed(-.6, 3)); 
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(-.5,3));
		addSequential(new AutoIntake(0,0));
/*		// drive forward 5 units on graph (81 inches)
		addSequential(new AutoDrive(.7, 81));
		// rotate to the right
		addSequential(new AutoTurn(90));
		// drives forward 2 units (32.4 inches)
		addSequential(new AutoDrive(.7, 32.4));
		// turns left
		addSequential(new AutoTurn(-90));
		// drives forward
		addSequential(new AutoDrive(.7, 64.8));
		// moves arm higher up
		addSequential(new AutoArmPosition(45));
		// reverses intake, releases cube into switch
		addSequential(new AutoIntake(-.4, -.4));
		//wait 2 seconds
		addSequential(new AutoWait(2));*/
	}
}
