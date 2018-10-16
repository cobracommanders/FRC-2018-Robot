package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;
import team498.robot.commands.auto.common.AutoWait;

/**
 *
 */
public class FromLeftSwitchToRightScaleStrategy extends CommandGroup {

	public FromLeftSwitchToRightScaleStrategy() {
		System.out.println("Going to Right Scale from Left Switch!");
		//THIS IS REALLY FROM NOTHING TO LEFT SWITCH
		addSequential(new AutoOldTurn(.9,90));
		addSequential(new AutoDriveTimed(.9,.15,2));
		addSequential(new AutoIntake(-.9,.9));
		addSequential(new AutoWait(3));
		addSequential(new AutoIntake(0,0));
		
	}
}
