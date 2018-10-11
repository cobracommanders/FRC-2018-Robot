package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromLeftScaleToLeftSwitchStrategy extends CommandGroup {

	public FromLeftScaleToLeftSwitchStrategy() {
		System.out.println("Going to Left Switch from Left Scale!");
		addSequential(new AutoDriveTimed(.8,0,-.5));
		addSequential(new AutoArmTimed(.9, .8));
		addSequential(new AutoDriveTimed(.9,.15,1));
		addSequential(new AutoIntake(-.4, .4)); // releasing cube into the scale 
	}
}
