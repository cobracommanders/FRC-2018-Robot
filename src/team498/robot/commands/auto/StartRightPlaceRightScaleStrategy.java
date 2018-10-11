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
public class StartRightPlaceRightScaleStrategy extends CommandGroup {

	public StartRightPlaceRightScaleStrategy() {
		System.out.println("Start Right, Right Scale Strategy!");
		addSequential(new AutoDrive(.8, 108)); //20 feet
		addSequential(new AutoIntake(.4,-.4));
		addSequential(new AutoArmTimed(.9, 2)); // raising arm for scale 
		addSequential(new AutoDrive(.6, 8)); //nudge the robot forward
		addSequential(new AutoIntake(-.4, .4)); // releasing cube into the scale 
		addSequential(new AutoDrive(-.5, 10)); //drive back
		addSequential(new AutoArmTimed(-.4, 1.6)); //bring arm down
		addSequential(new AutoOldTurn(.9, 125)); //turn around to face switch
		addSequential(new AutoIntake(1, -1)); //turn intake wheels on
		addSequential(new AutoDriveTimed(.8, .15, 1.5)); //drive up to cube
		addSequential(new AutoDrive(-.8, 4)); //back up a little
		addParallel(new AutoArmTimed(.9, 1));
		addSequential(new AutoOldTurn(.9, 125)); //turn back around to scale
		addSequential(new AutoIntake(0, 0)); //turn intake wheels off
		
		
		
		
		
		/*addSequential(new AutoDrive(-.8,-220));
		addSequential(new AutoDrive(-.6,20));
		addSequential(new AutoOldTurn(-.7,25));
		//addSequential(new AutoArmTemporaryPosition(0.7));
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(0.4,1));
		addSequential(new AutoArmTimed(-.6,3));
		addSequential(new AutoDrive(.6,24));
		addSequential(new AutoIntake(0,0));*/
		/*addSequential(new ToggleClamps());
		// drive forward 20 units
		addSequential(new AutoDrive(.7, 324));
		// rotate 90 degrees counterclockwise
		addSequential(new AutoTurn(-90));
		// drive forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// raises tower
		addSequential(new AutoLift(true));
		// raises arm
		addSequential(new AutoArmPosition(165));
		// releases cube into tower
		addSequential(new AutoIntake(-.8, -.8));*/
		// ends in same place
	}
}
