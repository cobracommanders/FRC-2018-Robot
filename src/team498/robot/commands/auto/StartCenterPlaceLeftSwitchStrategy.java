package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartCenterPlaceLeftSwitchStrategy extends CommandGroup {

	public StartCenterPlaceLeftSwitchStrategy() {
		System.out.println("Start Center, Left Switch Strategy!");
		// 5 units on Katy's graph
		// drives forward
		addSequential(new AutoDrive(.7, 81)); // 81 inches
		// rotates to the left
		addSequential(new AutoTurn(-90));
		// 4 units on katy's graph
		// drives forward
		addSequential(new AutoDrive(.7, 64.8));
		// rotates to the right
		addSequential(new AutoTurn(90));
		// 4 units drives forward
		addSequential(new AutoDrive(.7, 64.8));
		// reverses intake, releases cube into switch
//		addSequential(new AutoIntake(0, -.4, -.4, false));
	
	}
}
