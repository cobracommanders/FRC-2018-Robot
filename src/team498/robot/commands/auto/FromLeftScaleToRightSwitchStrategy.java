package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromLeftScaleToRightSwitchStrategy extends CommandGroup {

	public FromLeftScaleToRightSwitchStrategy() {
		System.out.println("Going to Right Switch from Left Scale!");
		addSequential(new AutoIntake(0,0));
	}
}
