package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceRightScaleStrategy extends CommandGroup {

	public StartLeftPlaceRightScaleStrategy() {
		System.out.println("Start Left, Right Scale Strategy!");
		
		addSequential(new AutoDrive(.8, 90));
		addSequential(new AutoOldTurn(.8, 90));
	}
}
