package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceLeftSwitchStrategy extends CommandGroup {

	public StartLeftPlaceLeftSwitchStrategy() {
		System.out.println("Start Left, Left Switch Strategy!");
		//drive forward 10 units
		addSequential(new AutoDrive(.7, 162));
		//rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		//drive foward 3 units 
		addSequential(new AutoDrive(.7, 48.6));
		//raises arm
		addSequential(new AutoArmPosition(45));
		//place cube in switch
		addSequential(new AutoIntake(-.4, -.4));
	}
}
