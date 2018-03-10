package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoArmTemporaryPosition;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoLift;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartRightPlaceLeftScaleStrategy extends CommandGroup {

	public StartRightPlaceLeftScaleStrategy() {
		System.out.println("Start Right, Left Scale Strategy!");
		addSequential(new AutoDrive(-.8,-195));
		addSequential(new AutoOldTurn(-.7,85));
		addSequential(new AutoDrive(-1, 200));
		addSequential(new AutoOldTurn(.9,85));
		addSequential(new AutoDrive(-.7,30));
		addSequential(new AutoArmTemporaryPosition(0.7));
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(0.4,1));
		addSequential(new AutoArmTimed(-.6,3));
		addSequential(new AutoIntake(0,0));
/*		addSequential(new ToggleClamps());
		// drive forward 15 units
		addSequential(new AutoDrive(.7, 243));
		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 15 units
		addSequential(new AutoDrive(.7, 243));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// drive forward 5 units
		addSequential(new AutoDrive(.7, 81));
		// rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		// raises tower
		addSequential(new AutoLift(true));
		// raises arm
		addSequential(new AutoArmPosition(165));
		// releases cube into tower
		addSequential(new AutoIntake(-.8, -.8));
		// ends in same spot
*/	}
}
