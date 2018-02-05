package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoResetGyro;
import team498.robot.commands.auto.common.AutoStop;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class StartLeftPlaceLeftSwitchStrategy extends CommandGroup {

    public StartLeftPlaceLeftSwitchStrategy() {
    	System.out.println("Start Left, Left Switch Strategy!");
    	//Drives with a motor power forward for 5 seconds
    	//if encoder works it can be driven to a distance instead
    	addSequential(new AutoDrive(.8), 5);
    	//Resets Gyro
    	addSequential(new AutoResetGyro());
    	//Turns a degree from the gyro to the left (negative angle)
    	addSequential(new AutoTurn(-45));
    	//Drive forward again
    	addSequential(new AutoDrive(.8), 5);
    	//Resets Gyro
    	addSequential(new AutoResetGyro());
    	//Turns to the right (positive angle)
    	addSequential(new AutoTurn(45));
    	//Drive forward, passes auto line (theoretically)
    	addSequential(new AutoDrive(.8), 5);
    	//Resets Gyro
    	addSequential(new AutoResetGyro());
    	//turns to the right to face switch side (assuming initial posiition was facing switch from driver station)
    	addSequential(new AutoTurn(45));
    	//Reverses Intake to drop cube into switch
    	addSequential(new AutoIntake(-.8));
    	//Stops robot
    	addSequential(new AutoStop());
    }
}
