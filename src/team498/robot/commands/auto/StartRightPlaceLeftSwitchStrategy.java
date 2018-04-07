package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartRightPlaceLeftSwitchStrategy extends CommandGroup {

	public StartRightPlaceLeftSwitchStrategy() {
		System.out.println("Start Right, Left Switch Strategy!");
		//addSequential(new ToggleClamps());
		//addSequential(new ToggleClamps());
		// drive forward 15 units
		//addSequential(new AutoDrive(-.8, -136));
		addSequential(new AutoDriveTimed(.9, -0.1, 3.5));    	
/*		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 14 units
		addSequential(new AutoDrive(.7, 226.8));
		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 5 units
		addSequential(new AutoDrive(.7, 81));
		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// raises arm
		addSequential(new AutoArmPosition(45));
		// releases cube into switch
		addSequential(new AutoIntake(-.4, -.4));
		// ends in same place
*/	}
}
