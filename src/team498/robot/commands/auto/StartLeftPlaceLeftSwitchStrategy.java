package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ManualIntake;
import team498.robot.commands.ToggleClamps;
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
public class StartLeftPlaceLeftSwitchStrategy extends CommandGroup {

	public StartLeftPlaceLeftSwitchStrategy() {
		System.out.println("Start Left, Left Switch Strategy!");
		addSequential(new AutoDriveTimed(.9,.15, 2.6)); 
		//THIS IS REALLY FROM NOTHING TO LEFT SWITCH
		addSequential(new AutoOldTurn(.8,90));
		addSequential(new AutoDriveTimed(.8,.15,2));
		addSequential(new AutoIntake(-.9,.9));
		addSequential(new AutoWait(3));
		addSequential(new AutoIntake(0,0));
		
		
		
		/*addSequential(new AutoDrive(.8, 12)); //3 feet 
		addSequential(new AutoOldTurn(.7, 25));
		addSequential(new AutoDrive(.8, 42)); //this should be 8 in a half feet 
		addSequential(new AutoIntake(-.6, -.6)); //releases cube into the switch

		*/
	}
}
