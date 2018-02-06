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
public class StartCenterPlaceLeftSwitchStrategy extends CommandGroup {

    public StartCenterPlaceLeftSwitchStrategy() {
    	System.out.println("Start Center, Left Switch Strategy!");
    	
    	// TODO make changes according to needs!
    	// Drives forward
    	addSequential(new AutoDrive(.8, 1));
    	// reset gyro
    	addSequential(new AutoResetGyro());
    	// turn right
    	addSequential(new AutoTurn(45));
    	// drive forward
    	addSequential(new AutoDrive(.8, 2));
    	// reset gyro
    	addSequential(new AutoResetGyro());
    	// turn left from right
    	addSequential(new AutoTurn(-45));
    	// drive forward
    	addSequential(new AutoDrive(.8, 1));
    	// reverse intake
    	addSequential(new AutoIntake(-.8));
    	// stop robot
    	addSequential(new AutoStop());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
