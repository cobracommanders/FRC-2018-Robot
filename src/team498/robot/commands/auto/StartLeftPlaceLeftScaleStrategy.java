package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceLeftScaleStrategy extends CommandGroup {

	public StartLeftPlaceLeftScaleStrategy() {
		System.out.println("Start Left, Left Scale Strategy!");
				addSequential(new AutoDrive(.8, 106)); //20 feet
		addSequential(new AutoIntake(.5,-.5));
		addSequential(new AutoArmTimed(.9, 2)); // raising arm for scale 
		addSequential(new AutoDrive(.6, 8)); //nudge the robot forward
		addSequential(new AutoIntake(-.4, .4)); // releasing cube into the scale 
		addSequential(new AutoDrive(-.5, 10)); //drive back
		addSequential(new AutoArmTimed(-.4, 1.6)); //bring arm down
		addSequential(new AutoOldTurn(.9, 125)); //turn around to face switch
		addSequential(new AutoIntake(1, -1)); //turn intake wheels on
		addSequential(new AutoDriveTimed(.9, .15, 2)); //drive up to cube
		//addSequential(new AutoDrive(-.8, 4)); //back up a little
		//addParallel(new AutoArmTimed(.9, 1));
		//addSequential(new AutoOldTurn(-.9, 125)); //turn back around to scale
		//addSequential(new AutoIntake(0, 0)); //turn intake wheels off
		
		
		
	}
}
