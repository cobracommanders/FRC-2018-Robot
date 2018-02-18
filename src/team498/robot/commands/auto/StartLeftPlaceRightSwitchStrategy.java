package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceRightSwitchStrategy extends CommandGroup {

	public StartLeftPlaceRightSwitchStrategy() {
		System.out.println("Start Left, Right Switch Strategy!");
		addSequential(new ToggleClamps());
		// drive forward 15 units
		addSequential(new AutoDrive(.7, 243));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// drive forward 14 units
		addSequential(new AutoDrive(.7, 226.8));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// drive forward 5 units
		addSequential(new AutoDrive(.7, 81));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// drive forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// raises arm
		addSequential(new AutoArmPosition(45));
		// releases cube in switch
		addSequential(new AutoIntake(-.4, -.4));
		// ends in same place
	}

}
