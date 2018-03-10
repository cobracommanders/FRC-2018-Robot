package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceLeftSwitchStrategy extends CommandGroup {

	public StartLeftPlaceLeftSwitchStrategy() {
		System.out.println("Start Left, Left Switch Strategy!");
		addSequential(new AutoDrive(-.8,-160));
		/*addSequential(new ToggleClamps());
		addSequential(new ToggleClamps());
		//addSequential(new AutoDrive(-.8, -136));
		addSequential(new AutoDriveTimed(-.8,0,5));
		addSequential(new AutoArmTimed(.5, 4));
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(-.5,4));
		addSequential(new AutoIntake(0,0));*/
	/*	// drive forward 10 units
		addSequential(new AutoDrive(.7, 162));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// drive foward 3 units
		addSequential(new AutoDrive(.7, 48.6));
		// raises arm
		addSequential(new AutoArmPosition(45));
		// place cube in switch
		addSequential(new AutoIntake(-.4, -.4));
		// ends in same place
*/	}
}
