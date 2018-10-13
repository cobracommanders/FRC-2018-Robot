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
public class FromRightSwitchToLeftScaleStrategy extends CommandGroup {

	public FromRightSwitchToLeftScaleStrategy() {
		System.out.println("Going to Left Scale from Right Switch!");
		//THIS IS REALLY FROM NOTHING TO RIGHT SWITCH
		addSequential(new AutoOldTurn(-.8,90));
		addSequential(new AutoDriveTimed(.8,.15,2));
		addSequential(new AutoIntake(-.9,.9));
		addSequential(new AutoWait(3));
		addSequential(new AutoIntake(0,0));
		
	}
}
