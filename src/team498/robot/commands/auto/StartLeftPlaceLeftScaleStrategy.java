package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceLeftScaleStrategy extends CommandGroup {

	public StartLeftPlaceLeftScaleStrategy() {
		System.out.println("Start Left, Left Scale Strategy!");
		// drive forward 324 inches
		addSequential(new AutoDrive(.7, 324));
		// rotate right
		addSequential(new AutoTurn(90));
		// raises arm
		// addSequential(new AutoIntake(165, 0, 0, false));
		// raises tower
		// addSequential(new AutoIntake(165, 0, 0, true));
		// releases cube into tower
		// addSequential(new AutoIntake(165, -.8, -.8, true));
		// rotate right
		addSequential(new AutoTurn(90));
		// drive forward
		addSequential(new AutoDrive(.7, 81));
		// rotate left
		addSequential(new AutoTurn(-90));
		// drive forward
		addSequential(new AutoDrive(.7, 64.8));
		// rotate right
		addSequential(new AutoTurn(90));
		// drive forward
		addSequential(new AutoDrive(.7, 32.4));
		// intake in cube
		// addSequential(new AutoIntake(0, true, false, false, false));
		// release cube
		// addSequential(new AutoIntake(90, false, true, false, false));
	}
}
