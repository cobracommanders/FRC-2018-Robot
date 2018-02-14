package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
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
		//drive forward 15 units 
		addSequential(new AutoDrive(.7, 243));
		//rotate 90 degrees clockwise 
		addSequential(new AutoTurn(90));
		//drive forward 11.5 units 
		addSequential(new AutoDrive(.7, 186.3));
		//rotate 90 degrees clockwise 
		addSequential(new AutoTurn(90));
		//drive forward 3 units 
		addSequential(new AutoDrive(.7, 48.6));
		//raises arm
		addSequential(new AutoArmPosition(45));
		//releases cube in switch
		addSequential(new AutoIntake(-.4, -.4));
	}
	
}
