package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.ManualIntake;
import team498.robot.commands.ToggleClamps;
import team498.robot.commands.auto.common.AutoArmTimed;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoDriveTimed;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoOldTurn;
import team498.robot.commands.auto.common.AutoTurn;
import team498.robot.commands.auto.common.AutoWait;

/**
 *
 */
public class StartRightPlaceRightSwitchStrategy extends CommandGroup {

	public StartRightPlaceRightSwitchStrategy() {
		System.out.println("Start Right, Right Switch Strategy!");
		addSequential(new AutoDriveTimed(.9,.15, 2.6)); 
		//THIS IS REALLY FROM NOTHING TO LEFT SWITCH
		addSequential(new AutoOldTurn(-.8,90));
		addSequential(new AutoDriveTimed(.8,.15,2));
		addSequential(new AutoIntake(-.9,.9));
		addSequential(new AutoWait(3));
		addSequential(new AutoIntake(0,0));
		
		
		
		//addSequential(new ToggleClamps());
		//addSequential(new ToggleClamps());
		//addSequential(new AutoDrive(-.8, -136));
		/*addSequential(new AutoDriveTimed(.9, 0, 3.5));    	
		addSequential(new AutoIntake(1,-1));
		addSequential(new AutoWait(3));*/
/*		// drive forward 10 units
		addSequential(new AutoDrive(.7, 162));
		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 3 units
		addSequential(new AutoDrive(.7, 48.6));
		// raises arm
		addSequential(new AutoArmPosition(45));
		// releases cube into switch
		addSequential(new AutoIntake(-.4, -.4));
		// ends in same place
*/	}
}
