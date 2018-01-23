package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestingAuto extends CommandGroup {

    public TestingAuto() {
    	/*addSequential(new DriveStraight(), 2); //drives straight for 2 sec
    	addSequential(new Stop(), 2); //stop robot for 2 sec
    	addSequential(new DriveLeft(), 3); //turn left and drive for 3 sec
    	addSequential(new ReverseUntilBumperButtonPress()); //reverses forever (until button is held down);
    	addSequential(new ReverseHalfPowerUntilBumperButtonDepress()); //reverses using half the power (until button is not pressed);
    	addSequential(new Stop()); //stops robot*/
    	addSequential(new AutoTurnRandyPid(30)); //woo randy pid
    	addSequential(new Stop());
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
