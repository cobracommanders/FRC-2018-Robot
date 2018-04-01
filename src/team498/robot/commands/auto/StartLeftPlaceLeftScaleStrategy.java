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
public class StartLeftPlaceLeftScaleStrategy extends CommandGroup {

	public StartLeftPlaceLeftScaleStrategy() {
		System.out.println("Start Left, Left Scale Strategy!");
		//addSequential(new ToggleClamps());
		addSequential(new AutoDrive(-.8,-220));
		addSequential(new AutoDrive(-.6,-20));
		addSequential(new AutoOldTurn(.7,25));
		//addSequential(new AutoArmTemporaryPosition(0.7));
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(0.4,1));
		addSequential(new AutoArmTimed(-.6,3));
		addSequential(new AutoDrive(.6,24));
		addSequential(new AutoIntake(0,0));
		// drive forward 324 inches
/*		addSequential(new AutoDrive(.7, 324));
		// rotate right
		addSequential(new AutoTurn(90));
		// drive forward one unit
		addSequential(new AutoDrive(.7, 16.2));
		// raises arm
		addSequential(new AutoArmPosition(165));
		// raises tower
		addSequential(new AutoLift(true));
		// releases cube into tower
		addSequential(new AutoIntake(-.8, -.8));
		// ends in same place
*/	}
}
