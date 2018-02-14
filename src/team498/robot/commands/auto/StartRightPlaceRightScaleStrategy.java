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
public class StartRightPlaceRightScaleStrategy extends CommandGroup {

    public StartRightPlaceRightScaleStrategy() {
    	System.out.println("Start Right, Right Scale Strategy!");
    		//drive forward 20 units 
    		addSequential(new AutoDrive(.7, 324));
    		//rotate 90 degrees counterclockwise 
    		addSequential(new AutoTurn(-90));
    		//raises tower
    		addSequential(new AutoLift(true));
    		//raises arm			
    		addSequential(new AutoArmPosition(165));
    		//releases cube into tower 
    		addSequential(new AutoIntake(-.8, -.8));
    }
}
