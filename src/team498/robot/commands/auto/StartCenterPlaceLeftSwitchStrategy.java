package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.IntakeCorrection;
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
public class StartCenterPlaceLeftSwitchStrategy extends CommandGroup {

	public StartCenterPlaceLeftSwitchStrategy() {
		System.out.println("Start Center, Left Switch Strategy!");
		addSequential(new AutoDriveTimed(.7,.15, .8)); //get off playerstation wall
		addSequential(new AutoOldTurn(-.9, 18)); //Faces Switch
		//addSequential(new AutoArmTimed(.8, 1)); //Lifts arm
		addSequential(new AutoDriveTimed(.9,.15, 2.6));// drive into switch
		addSequential(new AutoIntake(-.8, .8)); //releases cube into the switch
		addSequential(new AutoWait(1));
		addSequential(new AutoIntake(0,0));
		
		//TEST
		addSequential(new AutoDriveTimed(-.8,-.15,1.2)); //reverse
		addSequential(new AutoOldTurn(1, 40)); // Turn towards pyramid
		addSequential(new AutoArmTimed(.8,.5));
		addSequential(new AutoArmTimed(-.3, 1)); // Arm down
		addSequential(new AutoIntake(.9,-.9)); // intake cube
		addSequential(new AutoDriveTimed(.8, .15, 1.3));//drive into pyramid
		addSequential(new AutoDriveTimed(.8,-.8,1)); // V2
		addSequential(new AutoDriveTimed(-.8,-.15, 1.2));//Leave pyramid
		//addSequential(new AutoDriveTimed(.8, .15, .8));//drive into pyramid
		//addSequential(new AutoDriveTimed(-.8,-.15, .4));//Agitate pyramid
		//addSequential(new AutoDriveTimed(.8,.15,.8));
		//addSequential(new AutoDriveTimed(-.8,-.15, .8));//Leave pyramid
		addSequential(new AutoOldTurn(-1, 40)); // Turn Towards switch
		addSequential(new AutoArmTimed(.8,1.1)); // Lift Arm
		addSequential(new AutoDriveTimed(.8,.15,1.7)); // Go into switch
		addSequential(new AutoIntake(-.4,.4)); // Shoot Cube number 2
		addSequential(new AutoDriveTimed(-.8,-.15,1));
		
		
		
		
		
		
		
		
		
		
		
		
		/*addSequential(new AutoDriveTimed(.9,.1, .4));
		addSequential(new AutoOldTurn(-.9,45));
		addSequential(new AutoDriveTimed(.9,.1,2));
		addSequential(new AutoOldTurn(.9,50));
		addSequential(new AutoDriveTimed(.9,.1,2));
		addSequential(new AutoDriveTimed(-.9,0,.35));
		addSequential(new AutoArmTimed(.6,1.5));
		addSequential(new AutoIntake(1,-1));
		addSequential(new AutoWait(3));*/
/*		addSequential(new AutoDrive(.9, 12), .8);
		addSequential(new AutoOldTurn(.9, 90));
		addSequential(new AutoDrive(.9, 36), 1.5);
		addSequential(new AutoOldTurn(-.9,90));
		//addSequential(new ToggleClamps());
		//addSequential(new AutoArmTimed(1,1));
		addSequential(new AutoDriveTimed(.9, 0, 1.5));
		addSequential(new AutoIntake(.7, -.7));
		addSequential(new AutoWait(1));
		addSequential(new AutoDrive(-.9, 12),.8);
		addSequential(new AutoOldTurn(-.9, 90));
		//addSequential(new AutoArmTimed(-.2,.8));
		addSequential(new ToggleClamps());
		addSequential(new AutoIntake(-1,1));
		addSequential(new AutoDriveTimed(.9,0,1));
		addSequential(new AutoDriveTimed(-.9,0,.9));
		//addSequential(new IntakeCorrection(.4));
		//addSequential(new AutoWait(.4));
		addSequential(new AutoIntake(0,0));
		addSequential(new AutoOldTurn(.9,90));*/
		
		
		
		
		
		
		/*addSequential(new AutoArmTimed(1,1));
		addSequential(new AutoDriveTimed(.7, 0, 1));
		addSequential(new AutoIntake(-.3, .3));*/
		//addSequential(new ToggleClamps());
		/*addSequential(new ToggleClamps());
		addSequential(new ToggleClamps());
		addSequential(new AutoDrive(-.7,-14));
		addSequential(new AutoOldTurn(-.7,90));
		addSequential(new AutoDrive(-.7,-50));
		addSequential(new AutoOldTurn(.7,96));
		addSequential(new AutoDriveTimed(-.9,0,3));
		addSequential(new AutoArmTimed(.6, 2));
		addSequential(new AutoIntake(-1,-1));
		addSequential(new AutoArmTimed(-.5,3));
		addSequential(new AutoIntake(0,0));*/
		// 5 units on Katy's graph
		// drives forward
/*		addSequential(new AutoDrive(.7, 81)); // 81 inches
		// rotates to the left
		addSequential(new AutoTurn(-90));
		// 4 units on katy's graph
		// drives forward
		addSequential(new AutoDrive(.7, 64.8));
		// rotates to the right
		addSequential(new AutoTurn(90));
		// 4 units drives forward
		addSequential(new AutoDrive(.7, 64.8));
		// moves arm higher up
		addSequential(new AutoArmPosition(45));
		// reverses intake, releases cube into switch
		addSequential(new AutoIntake(-.4, -.4));*/

	}
}
