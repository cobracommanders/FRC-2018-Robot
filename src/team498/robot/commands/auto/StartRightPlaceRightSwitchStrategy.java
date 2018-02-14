package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartRightPlaceRightSwitchStrategy extends CommandGroup {

	public StartRightPlaceRightSwitchStrategy() {
		System.out.println("Start Right, Right Switch Strategy!");
		//drive forward 10 units 
		addSequential(new AutoDrive(.7, 162));
		//rotate 90 degrees counterclockwise 
		addSequential(new AutoTurn(-90));
		//drive forward 3 units 
		addSequential(new AutoDrive(.7, 48.6));
		//raises arm
		addSequential(new AutoArmPosition(45));
		//releases cube into switch 
		addSequential(new AutoIntake(-.4, -.4));
	}
}
