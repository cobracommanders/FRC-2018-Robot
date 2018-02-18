package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoTurn;

public class StartCenterPlaceRightScaleStrategy extends CommandGroup {

    public StartCenterPlaceRightScaleStrategy() {
    	System.out.println("Start Center, Right Scale Strategy!");
    	
    	//Autonomous PID Turn
    	//addSequential(new AutoTurn(90));
    	
    	//addSequential(new AutoArmTimed(1, 2));
    	
    	
    	//Autonomous encoder drive forward
    	/*addSequential(new AutoDrive(.9, 57));
    	addSequential (new AutoDrive(.6, 6));
    	addSequential(new AutoDrive(.2, 7));*/
    	
    	addSequential(new AutoDrive(.7,72));
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
