package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoLift;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceRightScaleStrategy extends CommandGroup {

	public StartLeftPlaceRightScaleStrategy() {
		System.out.println("Start Left, Right Scale Strategy!");
		//drive forward 15 units 
		addSequential(new AutoDrive(.7, 243));
		//rotate 90 degrees clockwise
		addSequential(new AutoTurn(90));
		//drive forward 14 units
		addSequential(new AutoDrive(.7, 226.8));
		//rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		//drive forward 5 units
		addSequential(new AutoDrive(.7, 81));
		//rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		//raises tower
		addSequential(new AutoLift(true));
		//raises arm
		addSequential(new AutoArmPosition(165));
		//releases cube in scale
		addSequential(new AutoIntake(-.8, -.8));
	}
}
