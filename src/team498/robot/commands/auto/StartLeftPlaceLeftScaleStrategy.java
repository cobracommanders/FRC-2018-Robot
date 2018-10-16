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
		addSequential(new AutoIntake(.6, -.6)); //keep cube in
		addSequential(new AutoArmTimed(.9, 2)); // raising arm for scale `
		addSequential(new AutoOldTurn(.9, 15));
		addSequential(new AutoDrive(.6, 8)); //nudge the robot forward
		addSequential(new AutoIntake(-.6, .6)); // releasing cube into the scale
		addSequential(new AutoDrive(-.6, 10)); //drive back
		
		addSequential(new AutoArmTimed(-.4, 1.6)); //bring arm down
		addSequential(new AutoOldTurn(.9, 100)); //turn around to face switch
		addSequential(new AutoIntake(1, -1)); //turn intake wheels on
		addSequential(new AutoDriveTimed(.9, .15, 1.7)); //drive up to cube
		
	}
}
