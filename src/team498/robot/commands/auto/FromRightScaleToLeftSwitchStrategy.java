package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromRightScaleToLeftSwitchStrategy extends CommandGroup {

	public FromRightScaleToLeftSwitchStrategy() {
		System.out.println("Going to Left Switch from Right Scale!");
		addSequential(new AutoIntake(0,0));
		

	}
}
