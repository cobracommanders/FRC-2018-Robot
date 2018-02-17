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
public class FromRightScaleToRightSwitchStrategy extends CommandGroup {

	public FromRightScaleToRightSwitchStrategy() {
		System.out.println("Going to Right Switch from Right Scale!");

		//reset arm positions
		addSequential(new AutoLift(false));
		addSequential(new AutoArmPosition(0));
		
		//rotates left
		addSequential(new AutoTurn(-90));
		//drive forward 5 units
		addSequential(new AutoDrive(.7, 81));
		//rotate right 
		addSequential(new AutoTurn(90));
		//drive forward 2.75 units 
		addSequential(new AutoDrive(.7, 44.55));
		//rotate left 
		addSequential(new AutoTurn(-90));
		//drives forward 2 units
		addSequential(new AutoDrive(.7, 32.4));
		//intakes cube 
		addSequential(new AutoIntake(.6, .6));
		//raises arm
		addSequential(new AutoArmPosition(45));
		//releases cube 
		addSequential(new AutoIntake(-.4, -.4));
		
		
		//done for now :)
		
	}
}
