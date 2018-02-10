package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartCenterPlaceRightSwitchStrategy extends CommandGroup {

	public StartCenterPlaceRightSwitchStrategy() {
		System.out.println("Start Center, Right Switch Strategy!");
		// drive forward 5 units on graph (81 inches)
		addSequential(new AutoDrive(.8, 81));
		// rotate to the right
		addSequential(new AutoTurn(90));
		// drives forward 2 units (32.4 inches)
		addSequential(new AutoDrive(.8, 32.4));
		// turns left
		addSequential(new AutoTurn(-90));
		// drives forward
		addSequential(new AutoDrive(.8, 64.8));
		// reverses intake so releases cube into switch
		addSequential(new AutoIntake(0, -.8, -.8, false));
	}
}
