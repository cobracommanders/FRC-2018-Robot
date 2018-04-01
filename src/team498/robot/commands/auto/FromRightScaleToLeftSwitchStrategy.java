package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromRightScaleToLeftSwitchStrategy extends CommandGroup {

	public FromRightScaleToLeftSwitchStrategy() {
		System.out.println("Going to Left Switch from Right Scale!");

		// resets arm positions
		//addSequential(new AutoLift(false));
		//addSequential(new AutoArmPosition(0));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives 5 units forward
		addSequential(new AutoDrive(.7, 81));
		// rotates right
		addSequential(new AutoTurn(90));
		// drives forward 9.5 units
		addSequential(new AutoDrive(.7, 153.9));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives forward 2 units
		addSequential(new AutoDrive(.7, 32.4));
		// intakes cube
		addSequential(new AutoIntake(.6, .6));
		// raises arm
		//addSequential(new AutoArmPosition(45));
		// releases cube
		addSequential(new AutoIntake(-.4, -.4));

		// done for now :)

	}
}
