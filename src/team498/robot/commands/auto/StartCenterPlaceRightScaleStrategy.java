package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoTurn;

public class StartCenterPlaceRightScaleStrategy extends CommandGroup {

	public StartCenterPlaceRightScaleStrategy() {
		System.out.println("Start Center, Right Scale Strategy!");
		addSequential(new AutoDriveTimed(-.7, 0, 3));    
	}
}
