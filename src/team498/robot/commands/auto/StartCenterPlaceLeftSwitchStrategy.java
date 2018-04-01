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
		addSequential(new AutoDrive(.7, 12), 1);
		addSequential(new AutoOldTurn(.7, 90));
		addSequential(new AutoDrive(.7, 45), 1.5);
		addSequential(new AutoOldTurn(-.7,90));
		addSequential(new AutoDriveTimed(1, 0, 1));
		addSequential(new AutoIntake(1, -1));
		addSequential(new AutoWait(.5));
		addSequential(new AutoDrive(-.7, 12));
		addSequential(new AutoOldTurn(-.7, 90));
		addSequential(new ToggleClamps());
		addSequential(new AutoIntake(-1,1));
		addSequential(new AutoDriveTimed(.7,0,.8));
		addSequential(new AutoDriveTimed(-.7,0,.7));
		addSequential(new IntakeCorrection(.4));
		addSequential(new AutoWait(.4));
		addSequential(new AutoIntake(0,0));
		addSequential(new AutoOldTurn(-.7,90));
		addSequential(new AutoDriveTimed(1,0,.6));
		addSequential(new ToggleClamps());
		addSequential(new AutoIntake(1,-1));
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
