package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;

public class StartCenterPlaceRightScaleStrategy extends CommandGroup {

	public StartCenterPlaceRightScaleStrategy() {
		System.out.println("Start Center, Right Scale Strategy!");
		
		addSequential(new AutoDrive(.7, 72));
	}
}
