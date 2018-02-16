package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromLeftScaleToRightSwitchStrategy extends CommandGroup {

	public FromLeftScaleToRightSwitchStrategy() {
		System.out.println("Going to Right Switch from Left Scale!");
		
		//rotates right
		addSequential(new AutoTurn(90));
		//drive forward 5 units 
		addSequential(new AutoDrive(.7, 81));
		//rotates left 
		addSequential(new AutoTurn(-90));
		//drives forward 12 units
		addSequential(new AutoDrive(.7, 194.4));
		//rotates right
		addSequential(new AutoTurn(90));
		//drive forward 2 units
		addSequential(new AutoDrive(.7, 32.4));
		//intake cube 
		addSequential(new AutoIntake(.6, .6));
		//raises arm
		addSequential(new AutoArmPosition(45));
		//releases cube 
		addSequential(new AutoIntake(.4, -.4));
		
		
	}
}
